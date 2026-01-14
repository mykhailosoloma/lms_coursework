package edu.kpi.lms_system.dto.response;


import edu.kpi.lms_system.model.Progress;
import lombok.Data;

import java.time.LocalDateTime;

@Data

public class ProgressResponseDto {

    private Long id;
    private Long enrollmentId;
    private Long lessonId;
    private Progress.Status status;
    private LocalDateTime lastAccessed;

}
