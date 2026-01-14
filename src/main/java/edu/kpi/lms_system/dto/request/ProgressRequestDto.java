package edu.kpi.lms_system.dto.request;

import edu.kpi.lms_system.model.Progress;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProgressRequestDto {

    @NotNull(message = "Enrollment id is required")
    private Long enrollmentId;

    @NotNull(message = "Lesson id is required")
    private Long lessonId;

    @NotNull(message = "Status is required")
    private Progress.Status status;

}
