package com.example.lms.model;
import jakarta.persistence.*;

@Entity @Table(name = "enrollments", uniqueConstraints = @UniqueConstraint(columnNames = {"userId", "courseId"}))
public class Enrollment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) public Long id;
    public Long userId;
    public Long courseId;
}
