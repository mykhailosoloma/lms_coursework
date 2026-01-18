package edu.kpi.lms_system.dto.mapping;

import edu.kpi.lms_system.dto.request.UserRequestDto;
import edu.kpi.lms_system.dto.response.UserResponseDto;
import edu.kpi.lms_system.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponseDto toDto(User user);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "passwordHash",  ignore = true)
    @Mapping(target = "id", ignore = true)
    User toEntity(UserRequestDto requestDto);
}
