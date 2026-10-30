package com.example.lms.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity @Table(name = "questions")
public class Question {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) public Long id;
    public Long unitId;
    @Column(length = 500) public String question;
    public String o1, o2, o3, o4;
    @JsonIgnore public int correct;  // 0=A,1=B,2=C,3=D (sent only after submit)
}
