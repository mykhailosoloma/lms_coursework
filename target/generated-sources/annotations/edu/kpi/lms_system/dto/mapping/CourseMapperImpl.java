package edu.kpi.lms_system.dto.mapping;

import edu.kpi.lms_system.dto.request.CourseRequestDto;
import edu.kpi.lms_system.dto.response.CourseResponseDto;
import edu.kpi.lms_system.model.Course;
import edu.kpi.lms_system.model.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-18T02:54:02+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.9 (Microsoft)"
)
@Component
public class CourseMapperImpl implements CourseMapper {

    @Override
    public CourseResponseDto toDto(Course course) {
        if ( course == null ) {
            return null;
        }

        CourseResponseDto courseResponseDto = new CourseResponseDto();

        courseResponseDto.setInstructorId( courseInstructorId( course ) );
        courseResponseDto.setId( course.getId() );
        courseResponseDto.setTitle( course.getTitle() );
        courseResponseDto.setDescription( course.getDescription() );
        courseResponseDto.setCategory( course.getCategory() );
        courseResponseDto.setCreatedAt( course.getCreatedAt() );

        courseResponseDto.setInstructorFullName( course.getInstructor().getFirstName() + " " + course.getInstructor().getLastName() );

        return courseResponseDto;
    }

    @Override
    public Course toEntity(CourseRequestDto requestDto) {
        if ( requestDto == null ) {
            return null;
        }

        Course course = new Course();

        course.setTitle( requestDto.getTitle() );
        course.setDescription( requestDto.getDescription() );
        course.setCategory( requestDto.getCategory() );

        return course;
    }

    private Long courseInstructorId(Course course) {
        if ( course == null ) {
            return null;
        }
        User instructor = course.getInstructor();
        if ( instructor == null ) {
            return null;
        }
        Long id = instructor.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
