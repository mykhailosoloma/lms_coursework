package edu.kpi.lms_system.repository;

import edu.kpi.lms_system.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {

    @Query(value = """
        SELECT l.* FROM lessons l
        LEFT JOIN progress p ON l.lesson_id = p.lesson_id
        WHERE p.progress_id IS NULL
    """, nativeQuery = true)
    List<Lesson> findUnfinishedLessons();
}