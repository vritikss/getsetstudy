package com.example.lms.controller;

import com.example.lms.model.*;
import com.example.lms.repository.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "http://localhost:5173")
public class DashboardController {
    private final UserRepository users;
    private final CourseRepository courses;
    private final UnitRepository units;
    private final EnrollmentRepository enr;
    private final AttemptRepository attempts;

    public DashboardController(UserRepository users, CourseRepository courses, UnitRepository units,
                               EnrollmentRepository enr, AttemptRepository attempts) {
        this.users = users; this.courses = courses; this.units = units; this.enr = enr; this.attempts = attempts;
    }

    @GetMapping("/{userId}")
    public Map<String, Object> dashboard(@PathVariable Long userId) {
        User u = users.findById(userId).orElseThrow();
        Map<Long, Course> cm = courses.findAll().stream().collect(Collectors.toMap(c -> c.id, c -> c));
        Map<Long, Unit> um = units.findAll().stream().collect(Collectors.toMap(x -> x.id, x -> x));

        List<String> enrolled = enr.findByUserId(userId).stream().map(e -> cm.get(e.courseId).name).toList();
        // newest first, so the first row for each unit is its most recent score
        List<Map<String, Object>> att = attempts.findByUserIdOrderByTakenAtDesc(userId).stream().map(a -> {
            Unit un = um.get(a.unitId);
            return Map.<String, Object>of("course", cm.get(un.courseId).name,
                    "unit", "Unit " + un.number + ": " + un.title,
                    "score", a.score, "total", a.total, "takenAt", a.takenAt.toString());
        }).toList();

        return Map.of("email", u.email, "enrolled", enrolled, "attempts", att);
    }
}
