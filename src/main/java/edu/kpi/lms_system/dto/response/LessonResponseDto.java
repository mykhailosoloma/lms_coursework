package edu.kpi.lms_system.dto.response;

import edu.kpi.lms_system.model.Lesson;
import lombok.Data;

@Data

public class LessonResponseDto {
    private Long id;
    private String title;
    private Lesson.ContentType contentType;
    private Long moduleId;
}
