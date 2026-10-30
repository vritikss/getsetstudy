package com.example.lms.model;
import jakarta.persistence.*;

@Entity @Table(name = "units")
public class Unit {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) public Long id;
    public Long courseId;
    public int number;               // 1..5
    public String title;
    @Column(length = 500) public String topics;
}
