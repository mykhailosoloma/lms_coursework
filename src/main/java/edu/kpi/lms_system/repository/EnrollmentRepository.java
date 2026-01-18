package edu.kpi.lms_system.repository;

import edu.kpi.lms_system.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    Optional<Enrollment> findByUserIdAndCourseId(Long userId, Long courseId);
    @Query(value = """
        SELECT u.email, c.title, e.enrolled_at 
        FROM enrollments e
        JOIN users u ON e.user_id = u.user_id
        JOIN courses c ON e.course_id = c.course_id
        ORDER BY e.enrolled_at DESC 
        LIMIT 5
    """, nativeQuery = true)
    List<Map<String, Object>> findLatestEnrollments();

}