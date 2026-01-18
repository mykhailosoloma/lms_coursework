package edu.kpi.lms_system.dto.mapping;

import edu.kpi.lms_system.dto.response.ProgressResponseDto;
import edu.kpi.lms_system.model.Enrollment;
import edu.kpi.lms_system.model.Lesson;
import edu.kpi.lms_system.model.Progress;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-16T12:33:19+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.9 (Microsoft)"
)
@Component
public class ProgressMapperImpl implements ProgressMapper {

    @Override
    public ProgressResponseDto toDto(Progress progress) {
        if ( progress == null ) {
            return null;
        }

        ProgressResponseDto progressResponseDto = new ProgressResponseDto();

        progressResponseDto.setLessonId( progressLessonId( progress ) );
        progressResponseDto.setEnrollmentId( progressEnrollmentId( progress ) );
        progressResponseDto.setId( progress.getId() );
        progressResponseDto.setStatus( progress.getStatus() );
        progressResponseDto.setLastAccessed( progress.getLastAccessed() );

        return progressResponseDto;
    }

    @Override
    public Progress toEntity(ProgressResponseDto dto) {
        if ( dto == null ) {
            return null;
        }

        Progress progress = new Progress();

        progress.setStatus( dto.getStatus() );

        return progress;
    }

    private Long progressLessonId(Progress progress) {
        if ( progress == null ) {
            return null;
        }
        Lesson lesson = progress.getLesson();
        if ( lesson == null ) {
            return null;
        }
        Long id = lesson.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long progressEnrollmentId(Progress progress) {
        if ( progress == null ) {
            return null;
        }
        Enrollment enrollment = progress.getEnrollment();
        if ( enrollment == null ) {
            return null;
        }
        Long id = enrollment.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
