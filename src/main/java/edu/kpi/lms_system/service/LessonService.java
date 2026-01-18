package edu.kpi.lms_system.service;

import edu.kpi.lms_system.dto.mapping.LessonMapper;
import edu.kpi.lms_system.dto.response.LessonResponseDto;
import edu.kpi.lms_system.repository.LessonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonService {

    private final LessonRepository lessonRepository;
    private final LessonMapper lessonMapper;

    public List<LessonResponseDto> findUnfinishedLessons() {
        return lessonRepository.findUnfinishedLessons().stream()
                .map(lessonMapper::lessonDto)
                .toList();
    }
}