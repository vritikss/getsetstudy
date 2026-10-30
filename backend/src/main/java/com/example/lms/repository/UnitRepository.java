package com.example.lms.repository;
import com.example.lms.model.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UnitRepository extends JpaRepository<Unit, Long> {
    List<Unit> findByCourseIdOrderByNumber(Long courseId);
}
