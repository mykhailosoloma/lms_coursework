package edu.kpi.lms_system.service;

import edu.kpi.lms_system.dto.mapping.ModuleMapper;
import edu.kpi.lms_system.dto.request.ModuleRequestDto;
import edu.kpi.lms_system.dto.response.ModuleResponseDto;
import edu.kpi.lms_system.model.Course;
import edu.kpi.lms_system.model.Module;
import edu.kpi.lms_system.repository.CourseRepository;
import edu.kpi.lms_system.repository.ModuleRepository;
import edu.kpi.lms_system.dto.mapping.ModuleMapper; // Перевір імпорт
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ModuleService {

    private final ModuleRepository moduleRepository;
    private final CourseRepository courseRepository;
    private final ModuleMapper moduleMapper;

    @Transactional
    public ModuleResponseDto addModuleToCourse(ModuleRequestDto request) {
        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        List<Module> existingModules = moduleRepository.findAllByCourseIdOrderByOrderIndexAsc(course.getId());
        int newOrderIndex = existingModules.isEmpty() ? 1 : existingModules.get(existingModules.size() - 1).getOrderIndex() + 1;

        Module module = moduleMapper.toEntity(request);
        module.setCourse(course);
        module.setOrderIndex(newOrderIndex);

        return moduleMapper.toDto(moduleRepository.save(module));
    }

    @Transactional
    public ModuleResponseDto updateModule(Long moduleId, String newTitle) {
        Module module = moduleRepository.findById(moduleId)
                .orElseThrow(() -> new RuntimeException("Module not found"));
        module.setTitle(newTitle);

        return moduleMapper.toDto(moduleRepository.save(module));
    }
    public List<ModuleResponseDto> findModulesByCourseTitle(String courseTitle) {
        List<Module> modules = moduleRepository.findModulesByCourseTitleNative(courseTitle);
        return modules.stream()
                .map(moduleMapper::toDto)
                .toList();
    }

}