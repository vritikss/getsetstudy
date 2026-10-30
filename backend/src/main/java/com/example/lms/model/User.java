package com.example.lms.model;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity @Table(name = "users")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) public Long id;
    public String name;
    public LocalDate dob;
    public String mobile;
    @Column(unique = true, nullable = false) public String email;
    @Column(nullable = false) public String password; // BCrypt hash
}
