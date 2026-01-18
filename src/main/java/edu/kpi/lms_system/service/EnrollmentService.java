package edu.kpi.lms_system.service;

import edu.kpi.lms_system.dto.mapping.EnrollmentMapper;
import edu.kpi.lms_system.dto.request.EnrollmentRequestDto;
import edu.kpi.lms_system.dto.response.EnrollmentResponseDto;
import edu.kpi.lms_system.model.Course;
import edu.kpi.lms_system.model.Enrollment;
import edu.kpi.lms_system.model.User;
import edu.kpi.lms_system.repository.CourseRepository;
import edu.kpi.lms_system.repository.EnrollmentRepository;
import edu.kpi.lms_system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final EnrollmentMapper enrollmentMapper;

    public List<Map<String, Object>> getLatestEnrollmentsReport() {
        return enrollmentRepository.findLatestEnrollments();
    }

    // Метод для відрахування (про всяк випадок, якщо знадобиться)
    @Transactional
    public void unenrollStudent(Long userId, Long courseId) {
        Enrollment enrollment = enrollmentRepository.findByUserIdAndCourseId(userId, courseId)
                .orElseThrow(() -> new RuntimeException("Enrollment not found"));
        enrollmentRepository.delete(enrollment);
    }
}