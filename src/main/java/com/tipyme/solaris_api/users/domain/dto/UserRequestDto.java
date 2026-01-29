package com.tipyme.solaris_api.users.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "User request data transfer object")
public class UserRequestDto {
    @Schema(description = "Name of the user")
    @NotBlank(message = "Name is required")
    private String name;

    @Schema(description = "Email address of the user")
    @NotBlank(message = "Email is required")
    @Email(message="Invalid email format")
    private String email; 

    @Schema(description = "Password of the user")
    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password; 
}
