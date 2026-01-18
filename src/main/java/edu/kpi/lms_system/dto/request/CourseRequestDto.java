package edu.kpi.lms_system.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class CourseRequestDto {

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "description is required")
    private String description;

    @NotBlank(message = "category is required")
    private String category;

    @NotNull(message = "Instructor id is required")
    private Long instructorId;

    private List<String> modulesTitles;

}
