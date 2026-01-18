package edu.kpi.lms_system.dto.mapping;

import edu.kpi.lms_system.dto.request.LessonRequestDto;
import edu.kpi.lms_system.dto.response.LessonResponseDto;
import edu.kpi.lms_system.model.Lesson;
import edu.kpi.lms_system.model.Module;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-16T12:33:19+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.9 (Microsoft)"
)
@Component
public class LessonMapperImpl implements LessonMapper {

    @Override
    public LessonResponseDto lessonDto(Lesson lesson) {
        if ( lesson == null ) {
            return null;
        }

        LessonResponseDto lessonResponseDto = new LessonResponseDto();

        lessonResponseDto.setModuleId( lessonModuleId( lesson ) );
        lessonResponseDto.setId( lesson.getId() );
        lessonResponseDto.setTitle( lesson.getTitle() );
        lessonResponseDto.setContentType( lesson.getContentType() );

        return lessonResponseDto;
    }

    @Override
    public Lesson toEntity(LessonRequestDto requestDto) {
        if ( requestDto == null ) {
            return null;
        }

        Lesson lesson = new Lesson();

        lesson.setTitle( requestDto.getTitle() );
        lesson.setContentType( requestDto.getContentType() );

        return lesson;
    }

    private Long lessonModuleId(Lesson lesson) {
        if ( lesson == null ) {
            return null;
        }
        Module module = lesson.getModule();
        if ( module == null ) {
            return null;
        }
        Long id = module.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
