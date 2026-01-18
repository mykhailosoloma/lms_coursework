package edu.kpi.lms_system.service;

import edu.kpi.lms_system.dto.mapping.CourseMapper;
import edu.kpi.lms_system.dto.request.CourseRequestDto;
import edu.kpi.lms_system.dto.response.CourseResponseDto;
import edu.kpi.lms_system.model.Course;
import edu.kpi.lms_system.model.User;
import edu.kpi.lms_system.repository.CourseRepository;
import edu.kpi.lms_system.repository.ModuleRepository;
import edu.kpi.lms_system.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CourseServiceTest {

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private ModuleRepository moduleRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CourseMapper courseMapper;

    @InjectMocks
    private CourseService courseService;

    @Test
    public void testCreateCourseWithModules_Success() {
        User instructor = new User();
        instructor.setId(1L);
        instructor.setEmail("instructor@example.com");
        instructor.setFirstName("Петро");
        instructor.setLastName("Петренко");
        instructor.setRole(User.Role.INSTRUCTOR);

        CourseRequestDto requestDto = new CourseRequestDto();
        requestDto.setTitle("Java Programming");
        requestDto.setDescription("Learn Java from scratch");
        requestDto.setCategory("Programming");
        requestDto.setInstructorId(1L);
        requestDto.setModulesTitles(Arrays.asList("Module 1", "Module 2"));

        Course course = new Course();
        course.setId(1L);
        course.setTitle("Java Programming");
        course.setDescription("Learn Java from scratch");
        course.setCategory("Programming");
        course.setInstructor(instructor);
        course.setCreatedAt(LocalDateTime.now());

        CourseResponseDto responseDto = new CourseResponseDto();
        responseDto.setId(1L);
        responseDto.setTitle("Java Programming");
        responseDto.setDescription("Learn Java from scratch");
        responseDto.setCategory("Programming");
        responseDto.setInstructorId(1L);
        responseDto.setInstructorFullName("Петро Петренко");
        when(userRepository.findById(1L)).thenReturn(Optional.of(instructor));
        when(courseMapper.toEntity(any(CourseRequestDto.class))).thenReturn(course);
        when(courseRepository.save(any(Course.class))).thenReturn(course);
        when(courseMapper.toDto(any(Course.class))).thenReturn(responseDto);

        CourseResponseDto result = courseService.createCourseWithModules(requestDto);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Java Programming", result.getTitle());
        assertEquals("Programming", result.getCategory());
        assertEquals(1L, result.getInstructorId());
        assertEquals("Петро Петренко", result.getInstructorFullName());
        verify(userRepository).findById(1L);
        verify(courseMapper).toEntity(requestDto);
        verify(courseRepository).save(course);
        verify(moduleRepository).saveAll(anyList());
        verify(courseMapper).toDto(course);
    }
}