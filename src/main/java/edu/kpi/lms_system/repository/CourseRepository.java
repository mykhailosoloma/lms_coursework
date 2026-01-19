package edu.kpi.lms_system.repository;

import edu.kpi.lms_system.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findAllByCategory(String category);

    @Query(value = """
        SELECT c.* FROM courses c
        LEFT JOIN enrollments e ON c.course_id = e.course_id
        WHERE e.enrollment_id IS NULL
    """, nativeQuery = true)
    List<Course> findCoursesWithoutStudents();

    @Query(value = """
        SELECT
            u.email AS instructor_email,
            c.title AS course_title,
            COUNT(DISTINCT m.module_id) AS content_volume,
            COUNT(DISTINCT e.enrollment_id) AS popularity
        FROM courses c
        JOIN users u ON c.instructor_id = u.user_id
        JOIN modules m ON c.course_id = m.course_id
        LEFT JOIN enrollments e ON c.course_id = e.course_id
        GROUP BY c.course_id, u.email, c.title
        ORDER BY popularity DESC, content_volume DESC
    """, nativeQuery = true)
    List<Map<String, Object>> getInstructorPerformanceAnalytics();

    @Query(value = """
        SELECT
            u.last_name AS instructor_last_name,
            c.title AS course_title,
            m.title AS module_title,
            l.title AS lesson_title,
            l.content_type
        FROM courses c
        JOIN users u ON c.instructor_id = u.user_id
        JOIN modules m ON c.course_id = m.course_id
        JOIN lessons l ON m.module_id = l.module_id
        ORDER BY u.last_name, c.title, m.order_index, l.order_index
    """, nativeQuery = true)
    List<Map<String, Object>> getFullContentReport();
}