package edu.kpi.lms_system.dto.mapping;

import edu.kpi.lms_system.dto.request.ModuleRequestDto;
import edu.kpi.lms_system.dto.response.ModuleResponseDto;
import edu.kpi.lms_system.model.Course;
import edu.kpi.lms_system.model.Module;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-16T12:33:19+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.9 (Microsoft)"
)
@Component
public class ModuleMapperImpl implements ModuleMapper {

    @Override
    public ModuleResponseDto toDto(Module module) {
        if ( module == null ) {
            return null;
        }

        ModuleResponseDto moduleResponseDto = new ModuleResponseDto();

        moduleResponseDto.setCourse( moduleCourseId( module ) );
        moduleResponseDto.setId( module.getId() );
        moduleResponseDto.setTitle( module.getTitle() );
        moduleResponseDto.setOrderIndex( module.getOrderIndex() );

        return moduleResponseDto;
    }

    @Override
    public Module toEntity(ModuleRequestDto moduleRequestDto) {
        if ( moduleRequestDto == null ) {
            return null;
        }

        Module module = new Module();

        module.setTitle( moduleRequestDto.getTitle() );
        module.setOrderIndex( moduleRequestDto.getOrderIndex() );

        return module;
    }

    private Long moduleCourseId(Module module) {
        if ( module == null ) {
            return null;
        }
        Course course = module.getCourse();
        if ( course == null ) {
            return null;
        }
        Long id = course.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
