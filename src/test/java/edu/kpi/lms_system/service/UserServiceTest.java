package edu.kpi.lms_system.service;

import edu.kpi.lms_system.dto.mapping.UserMapper;
import edu.kpi.lms_system.dto.request.UserRequestDto;
import edu.kpi.lms_system.dto.response.UserResponseDto;
import edu.kpi.lms_system.model.User;
import edu.kpi.lms_system.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    @Test
    public void testRegisterUser_Success() {
        UserRequestDto requestDto = new UserRequestDto();
        requestDto.setEmail("test@example.com");
        requestDto.setFirstName("Іван");
        requestDto.setLastName("Java");
        requestDto.setPassword("password123");
        requestDto.setRole(User.Role.STUDENT);

        User user = new User();
        user.setId(1L);
        user.setEmail("test@example.com");
        user.setFirstName("Іван");
        user.setLastName("Java");
        user.setPasswordHash("hashedPassword");
        user.setRole(User.Role.STUDENT);
        user.setCreatedAt(LocalDateTime.now());

        UserResponseDto responseDto = new UserResponseDto();
        responseDto.setId(1L);
        responseDto.setEmail("test@example.com");
        responseDto.setFirstName("Іван");
        responseDto.setLastName("Java");
        responseDto.setRole(User.Role.STUDENT);
        when(userRepository.existsByEmail(anyString())).thenReturn(false);
        when(userMapper.toEntity(any(UserRequestDto.class))).thenReturn(user);
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(userMapper.toDto(any(User.class))).thenReturn(responseDto);

        UserResponseDto result = userService.registerUser(requestDto);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("test@example.com", result.getEmail());
        assertEquals("Іван", result.getFirstName());
        assertEquals("Java", result.getLastName());
        assertEquals(User.Role.STUDENT, result.getRole());

        verify(userRepository).existsByEmail("test@example.com");
        verify(userMapper).toEntity(requestDto);
        verify(userRepository).save(user);
        verify(userMapper).toDto(user);
    }

    @Test
    public void testRegisterUser_EmailExists() {
        UserRequestDto requestDto = new UserRequestDto();
        requestDto.setEmail("existing@example.com");
        requestDto.setFirstName("Іван");
        requestDto.setLastName("Java");
        requestDto.setPassword("password123");
        requestDto.setRole(User.Role.STUDENT);
        when(userRepository.existsByEmail("existing@example.com")).thenReturn(true);
        Exception exception = assertThrows(RuntimeException.class, () -> {
            userService.registerUser(requestDto);
        });
        assertEquals("Email already taken", exception.getMessage());

        verify(userRepository).existsByEmail("existing@example.com");
        verify(userMapper, never()).toEntity(any(UserRequestDto.class));
        verify(userRepository, never()).save(any(User.class));
    }
}