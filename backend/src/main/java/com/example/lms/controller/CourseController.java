package com.example.lms.controller;

import com.example.lms.model.*;
import com.example.lms.repository.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/courses")
@CrossOrigin(origins = "http://localhost:5173")
public class CourseController {
    private final CourseRepository courses;
    private final EnrollmentRepository enr;

    public CourseController(CourseRepository courses, EnrollmentRepository enr) {
        this.courses = courses; this.enr = enr;
    }

    @GetMapping
    public List<Map<String, Object>> list(@RequestParam Long userId) {
        Set<Long> mine = enr.findByUserId(userId).stream().map(e -> e.courseId).collect(Collectors.toSet());
        return courses.findAll().stream().map(c -> Map.<String, Object>of(
                "id", c.id, "code", c.code, "name", c.name,
                "description", c.description == null ? "" : c.description,
                "enrolled", mine.contains(c.id))).toList();
    }

    @PostMapping("/{id}/enroll")
    public Map<String, Object> enroll(@PathVariable Long id, @RequestParam Long userId) {
        if (enr.findByUserIdAndCourseId(userId, id).isEmpty()) {
            Enrollment e = new Enrollment(); e.userId = userId; e.courseId = id; enr.save(e);
        }
        return Map.of("ok", true);
    }

    @DeleteMapping("/{id}/enroll")
    @Transactional
    public Map<String, Object> unenroll(@PathVariable Long id, @RequestParam Long userId) {
        enr.deleteByUserIdAndCourseId(userId, id);
        return Map.of("ok", true);
    }
}
