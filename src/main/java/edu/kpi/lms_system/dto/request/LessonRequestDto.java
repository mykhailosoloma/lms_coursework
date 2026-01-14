package edu.kpi.lms_system.dto.request;

import edu.kpi.lms_system.model.Lesson;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LessonRequestDto {

    @NotBlank(message = "Lesson title is required")
    private String title;

    @NotNull(message = "Content type is required")
    private Lesson.ContentType contentType;

    @NotNull(message = "Order index is required")
    private Integer orderIndex;

    @NotNull(message = "Module id is required")
    private Long moduleId;
}
