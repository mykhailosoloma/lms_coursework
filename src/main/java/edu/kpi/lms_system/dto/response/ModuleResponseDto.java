package edu.kpi.lms_system.dto.response;

import edu.kpi.lms_system.model.Lesson;
import lombok.Data;

import java.util.List;

@Data

public class ModuleResponseDto {
    private Long id;
    private String title;
    private Integer orderIndex;
    private Long course;
    private List<Lesson> lessons;
}
