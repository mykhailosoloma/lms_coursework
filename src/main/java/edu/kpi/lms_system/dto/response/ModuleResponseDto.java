package edu.kpi.lms_system.dto.response;

import edu.kpi.lms_system.model.Lesson;
import lombok.Data;

@Data

public class ModuleResponseDto {
    private Long id;
    private String title;
    private Integer orderIndex;
    private Long course;
    private Lesson lesson;
}
