package edu.kpi.lms_system.dto.mapping;

import edu.kpi.lms_system.dto.request.EnrollmentRequestDto;
import edu.kpi.lms_system.dto.response.EnrollmentResponseDto;
import edu.kpi.lms_system.model.Course;
import edu.kpi.lms_system.model.Enrollment;
import edu.kpi.lms_system.model.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-16T12:33:19+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.9 (Microsoft)"
)
@Component
public class EnrollmentMapperImpl implements EnrollmentMapper {

    @Override
    public EnrollmentResponseDto toDto(Enrollment enrollment) {
        if ( enrollment == null ) {
            return null;
        }

        EnrollmentResponseDto enrollmentResponseDto = new EnrollmentResponseDto();

        enrollmentResponseDto.setUserId( enrollmentUserId( enrollment ) );
        enrollmentResponseDto.setCourseId( enrollmentCourseId( enrollment ) );
        enrollmentResponseDto.setId( enrollment.getId() );
        enrollmentResponseDto.setEnrollmentAt( enrollment.getEnrollmentAt() );

        return enrollmentResponseDto;
    }

    @Override
    public Enrollment toEntity(EnrollmentRequestDto requestDto) {
        if ( requestDto == null ) {
            return null;
        }

        Enrollment enrollment = new Enrollment();

        return enrollment;
    }

    private Long enrollmentUserId(Enrollment enrollment) {
        if ( enrollment == null ) {
            return null;
        }
        User user = enrollment.getUser();
        if ( user == null ) {
            return null;
        }
        Long id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long enrollmentCourseId(Enrollment enrollment) {
        if ( enrollment == null ) {
            return null;
        }
        Course course = enrollment.getCourse();
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
