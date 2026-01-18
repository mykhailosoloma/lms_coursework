package edu.kpi.lms_system.dto.mapping;

import edu.kpi.lms_system.dto.request.CourseRequestDto;
import edu.kpi.lms_system.dto.response.CourseResponseDto;
import edu.kpi.lms_system.model.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    @Mapping(target = "instructorId", source = "instructor.id")
    @Mapping(target = "instructorFullName", expression = "java(course.getInstructor().getFirstName() + \" \" + course.getInstructor().getLastName())")
    CourseResponseDto toDto(Course course);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "instructor", ignore = true)
    Course toEntity(CourseRequestDto requestDto);
}