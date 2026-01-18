package edu.kpi.lms_system.repository;

import edu.kpi.lms_system.model.Progress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProgressRepository extends JpaRepository<Progress, Long> {

    Optional<Progress> findByEnrollmentIdAndLessonId(Long enrollmentId, Long lessonId);
    void deleteAllByEnrollmentId(Long enrollmentId);
}