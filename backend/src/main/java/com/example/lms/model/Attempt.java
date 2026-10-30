package com.example.lms.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity @Table(name = "attempts")
public class Attempt {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) public Long id;
    public Long userId;
    public Long unitId;
    public int score;
    public int total;
    public LocalDateTime takenAt;
}
