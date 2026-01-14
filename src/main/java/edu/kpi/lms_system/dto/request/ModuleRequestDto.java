package edu.kpi.lms_system.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ModuleRequestDto {

     @NotBlank(message = "Module title is required")
     private String title;

     @NotNull(message = "Order index is required")
     private Integer orderIndex;

     @NotNull(message = "Course id is required")
     private Long courseId;

}
