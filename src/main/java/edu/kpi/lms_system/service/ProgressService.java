package edu.kpi.lms_system.service;

import edu.kpi.lms_system.model.Enrollment;
import edu.kpi.lms_system.model.Lesson;
import edu.kpi.lms_system.model.Progress;
import edu.kpi.lms_system.repository.EnrollmentRepository;
import edu.kpi.lms_system.repository.LessonRepository;
import edu.kpi.lms_system.repository.ProgressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ProgressService {

    private final ProgressRepository progressRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final LessonRepository lessonRepository;
    @Transactional
    public void completeLesson(Long userId, Long lessonId) {
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new RuntimeException("Lesson not found"));

        Long courseId = lesson.getModule().getCourse().getId();

        Enrollment enrollment = enrollmentRepository.findByUserIdAndCourseId(userId, courseId)
                .orElseThrow(() -> new RuntimeException("Student not enrolled"));

        Progress progress = progressRepository.findByEnrollmentIdAndLessonId(enrollment.getId(), lessonId)
                .orElse(new Progress());

        if (progress.getStatus() == Progress.Status.COMPLETED) {
            return;
        }

        if (progress.getId() == null) {
            progress.setEnrollment(enrollment);
            progress.setLesson(lesson);
        }
        progress.setStatus(Progress.Status.COMPLETED);
        progress.setLastAccessed(LocalDateTime.now());

        progressRepository.save(progress);
    }

    @Transactional
    public void resetCourseProgress(Long userId, Long courseId) {
        Enrollment enrollment = enrollmentRepository.findByUserIdAndCourseId(userId, courseId)
                .orElseThrow(() -> new RuntimeException("Student not enrolled"));

        progressRepository.deleteAllByEnrollmentId(enrollment.getId());
    }
}