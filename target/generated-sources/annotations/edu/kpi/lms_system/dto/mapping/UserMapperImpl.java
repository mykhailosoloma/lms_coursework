package edu.kpi.lms_system.dto.mapping;

import edu.kpi.lms_system.dto.request.UserRequestDto;
import edu.kpi.lms_system.dto.response.UserResponseDto;
import edu.kpi.lms_system.model.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-16T12:33:18+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.9 (Microsoft)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserResponseDto toDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponseDto userResponseDto = new UserResponseDto();

        userResponseDto.setId( user.getId() );
        userResponseDto.setEmail( user.getEmail() );
        userResponseDto.setFirstName( user.getFirstName() );
        userResponseDto.setLastName( user.getLastName() );
        userResponseDto.setRole( user.getRole() );
        userResponseDto.setCreatedAt( user.getCreatedAt() );

        return userResponseDto;
    }

    @Override
    public User toEntity(UserRequestDto requestDto) {
        if ( requestDto == null ) {
            return null;
        }

        User user = new User();

        user.setEmail( requestDto.getEmail() );
        user.setFirstName( requestDto.getFirstName() );
        user.setLastName( requestDto.getLastName() );
        user.setRole( requestDto.getRole() );

        return user;
    }
}
