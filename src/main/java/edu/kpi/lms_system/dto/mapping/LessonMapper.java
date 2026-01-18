package edu.kpi.lms_system.dto.mapping;

import edu.kpi.lms_system.dto.request.LessonRequestDto;
import edu.kpi.lms_system.dto.response.LessonResponseDto;
import edu.kpi.lms_system.model.Lesson;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LessonMapper {

    @Mapping(target = "moduleId", source = "module.id")
    LessonResponseDto lessonDto(Lesson lesson);

    @Mapping(target = "module", ignore = true)
    @Mapping(target = "id", ignore = true)
    Lesson toEntity(LessonRequestDto requestDto);
}
