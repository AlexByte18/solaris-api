package com.tipyme.solaris_api.users.domain.mapper;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.tipyme.solaris_api.auth.domain.dto.RegisterDto;
import com.tipyme.solaris_api.users.User;
import com.tipyme.solaris_api.users.domain.dto.UserRequestDto;
import com.tipyme.solaris_api.users.domain.dto.UserResponseDto;
import com.tipyme.solaris_api.users.domain.dto.UserUpdateDto;

@Mapper(componentModel="spring")
public interface UserMapper {
    @Mapping(target="id", ignore=true)
    User toEntity(UserRequestDto userRequestDto);
    
    UserResponseDto toResponseDto(User user);

    List<UserResponseDto> toUserResponseDtoList(List<User> users);

    @BeanMapping(nullValuePropertyMappingStrategy=NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target="id", ignore=true)
    void updateUserFromDto(UserUpdateDto dto, @MappingTarget User user);

    @Mapping(target="password", ignore=true)
    @Mapping(target="id", ignore=true)
    User registerDtoToUser(RegisterDto registerDto);
}
