package edu.kpi.lms_system.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CourseResponseDto {
    private Long id;
    private String title;
    private String description;
    private String category;
    private LocalDateTime createdAt;
    private Long instructorId;
    private String instructorFullName;
}
