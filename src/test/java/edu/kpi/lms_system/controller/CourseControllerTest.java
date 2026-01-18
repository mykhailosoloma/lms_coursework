package edu.kpi.lms_system.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.kpi.lms_system.dto.request.CourseRequestDto;
import edu.kpi.lms_system.dto.response.CourseResponseDto;
import edu.kpi.lms_system.service.CourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CourseControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CourseService courseService;

    @InjectMocks
    private CourseController courseController;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(courseController).build();
    }

    @Test
    public void testCreateCourse() throws Exception {
        CourseRequestDto requestDto = new CourseRequestDto();
        requestDto.setTitle("Java Programming");
        requestDto.setDescription("Learn Java from scratch");
        requestDto.setCategory("Programming");
        requestDto.setInstructorId(1L);
        requestDto.setModulesTitles(Arrays.asList("Модуль 1", "Модуль 2"));

        CourseResponseDto responseDto = new CourseResponseDto();
        responseDto.setId(1L);
        responseDto.setTitle("Java Programming");
        responseDto.setDescription("Learn Java from scratch");
        responseDto.setCategory("Programming");
        responseDto.setInstructorId(1L);
        responseDto.setInstructorFullName("Петро Петренко");
        responseDto.setCreatedAt(LocalDateTime.now());

        when(courseService.createCourseWithModules(any(CourseRequestDto.class)))
                .thenReturn(responseDto);

        mockMvc.perform(post("/api/courses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Java Programming")))
                .andExpect(jsonPath("$.category", is("Programming")))
                .andExpect(jsonPath("$.instructorFullName", is("Петро Петренко")));

        verify(courseService).createCourseWithModules(any(CourseRequestDto.class));
    }
}