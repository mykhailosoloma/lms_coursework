package edu.kpi.lms_system.dto.mapping;

import edu.kpi.lms_system.dto.response.ProgressResponseDto;
import edu.kpi.lms_system.model.Progress;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProgressMapper {

    @Mapping(target = "lessonId", source = "lesson.id")
    @Mapping(target = "enrollmentId",source = "enrollment.id")
    ProgressResponseDto toDto(Progress progress);

    @Mapping(target = "id",ignore = true)
    @Mapping(target = "enrollment", ignore = true)
    @Mapping(target = "lesson", ignore = true)
    @Mapping(target = "lastAccessed", ignore = true)
    Progress toEntity(ProgressResponseDto dto);
}
