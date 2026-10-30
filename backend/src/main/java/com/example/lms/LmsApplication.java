package com.example.lms;

import com.example.lms.model.*;
import com.example.lms.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LmsApplication {
    public static void main(String[] args) { SpringApplication.run(LmsApplication.class, args); }

    // Loads courses, units and the Unit 1 tests on the first run (see SeedData.java)
    @Bean
    CommandLineRunner seed(CourseRepository cr, UnitRepository ur, QuestionRepository qr) {
        return args -> {
            if (cr.count() > 0) return;
            for (int c = 0; c < SeedData.COURSES.length; c++) {
                String[] cd = SeedData.COURSES[c];
                Course course = new Course();
                course.code = cd[0]; course.name = cd[1]; course.description = cd[2];
                cr.save(course);

                for (int u = 0; u < SeedData.UNITS[c].length; u++) {
                    Unit unit = new Unit();
                    unit.courseId = course.id; unit.number = u + 1;
                    unit.title = SeedData.UNITS[c][u][0];
                    unit.topics = SeedData.UNITS[c][u][1];
                    ur.save(unit);

                    if (u == 0) {   // only Unit 1 has a test for now
                        for (String line : SeedData.UNIT1_TEST[c]) {
                            String[] p = line.split("\\|");
                            Question q = new Question();
                            q.unitId = unit.id; q.question = p[0];
                            q.o1 = p[1]; q.o2 = p[2]; q.o3 = p[3]; q.o4 = p[4];
                            q.correct = "ABCD".indexOf(p[5]);
                            qr.save(q);
                        }
                    }
                }
            }
        };
    }
}
