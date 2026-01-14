package edu.kpi.lms_system.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data

public class EnrollmentResponseDto {
    private Long id;
    private LocalDateTime enrollmentAt;
    private Long userId;
    private Long lessonId;
}
