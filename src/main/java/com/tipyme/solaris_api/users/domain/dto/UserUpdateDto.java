package com.tipyme.solaris_api.users.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserUpdateDto {
    private String name;

    @Email(message="Invalid email format")
    private String email; 

    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password; 
}
