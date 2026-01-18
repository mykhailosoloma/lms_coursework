package edu.kpi.lms_system.dto.mapping;

import edu.kpi.lms_system.dto.request.ModuleRequestDto;
import edu.kpi.lms_system.dto.response.ModuleResponseDto;
import edu.kpi.lms_system.model.Module;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ModuleMapper {
   @Mapping(target = "course", source = "course.id")
   @Mapping(target = "lessons", ignore = true)
    ModuleResponseDto toDto(Module module);

   @Mapping(target = "id", ignore = true)
    @Mapping(target = "course", ignore = true)
    @Mapping(target = "lessons", ignore = true)
    Module toEntity(ModuleRequestDto moduleRequestDto);
}
