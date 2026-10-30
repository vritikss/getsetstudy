package com.example.lms.repository;
import com.example.lms.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    List<Enrollment> findByUserId(Long userId);
    Optional<Enrollment> findByUserIdAndCourseId(Long userId, Long courseId);
    void deleteByUserIdAndCourseId(Long userId, Long courseId);
}
