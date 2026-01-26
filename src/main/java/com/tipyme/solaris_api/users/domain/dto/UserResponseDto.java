package com.tipyme.solaris_api.users.domain.dto;

import lombok.Data;

@Data
public class UserResponseDto {
    private Long id;
    private String name;
    private String email;
}
