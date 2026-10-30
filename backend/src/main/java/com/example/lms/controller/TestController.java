package com.example.lms.controller;

import com.example.lms.model.*;
import com.example.lms.repository.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class TestController {
    private final UnitRepository units;
    private final QuestionRepository questions;
    private final EnrollmentRepository enr;
    private final AttemptRepository attempts;

    public TestController(UnitRepository units, QuestionRepository questions,
                          EnrollmentRepository enr, AttemptRepository attempts) {
        this.units = units; this.questions = questions; this.enr = enr; this.attempts = attempts;
    }

    // the 5 units of a course (questionCount = 0 means the test is not added yet)
    @GetMapping("/courses/{id}/units")
    public List<Map<String, Object>> units(@PathVariable Long id) {
        return units.findByCourseIdOrderByNumber(id).stream().map(u -> Map.<String, Object>of(
                "id", u.id, "number", u.number, "title", u.title, "topics", u.topics,
                "questionCount", questions.findByUnitId(u.id).size())).toList();
    }

    // questions without the correct answers
    @GetMapping("/units/{id}/questions")
    public List<Question> questions(@PathVariable Long id) {
        return questions.findByUnitId(id);
    }

    // body: { "userId": 1, "answers": { "<questionId>": <optionIndex 0..3>, ... } }
    public record SubmitReq(Long userId, Map<Long, Integer> answers) {}

    @PostMapping("/units/{id}/submit")
    public ResponseEntity<?> submit(@PathVariable Long id, @RequestBody SubmitReq req) {
        Unit unit = units.findById(id).orElseThrow();
        if (enr.findByUserIdAndCourseId(req.userId(), unit.courseId).isEmpty())
            return ResponseEntity.status(403).body(Map.of("error", "Enroll in this course first"));

        Map<Long, Integer> given = req.answers() == null ? Map.of() : req.answers();
        List<Question> qs = questions.findByUnitId(id);
        Map<Long, Integer> correct = new HashMap<>();
        int score = 0;
        for (Question q : qs) {
            correct.put(q.id, q.correct);
            Integer a = given.get(q.id);
            if (a != null && a == q.correct) score++;
        }

        Attempt at = new Attempt();
        at.userId = req.userId(); at.unitId = id;
        at.score = score; at.total = qs.size(); at.takenAt = LocalDateTime.now();
        attempts.save(at);

        return ResponseEntity.ok(Map.<String, Object>of("score", score, "total", qs.size(), "correct", correct));
    }
}
