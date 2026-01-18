package edu.kpi.lms_system.dto.mapping;

import edu.kpi.lms_system.dto.request.EnrollmentRequestDto;
import edu.kpi.lms_system.dto.response.EnrollmentResponseDto;
import edu.kpi.lms_system.model.Enrollment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface EnrollmentMapper {

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "courseId", source = "course.id")
    EnrollmentResponseDto toDto(Enrollment enrollment);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "enrollmentAt", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "course", ignore = true)
    Enrollment toEntity(EnrollmentRequestDto requestDto);
}