package edu.kpi.lms_system.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EnrollmentRequestDto {

    @NotNull
    private Long userId;
    @NotNull
    private Long courseId;
}
