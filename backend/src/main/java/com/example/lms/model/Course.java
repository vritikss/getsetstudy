package com.example.lms.model;
import jakarta.persistence.*;

@Entity @Table(name = "courses")
public class Course {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) public Long id;
    public String code;
    public String name;
    @Column(length = 500) public String description;
}
