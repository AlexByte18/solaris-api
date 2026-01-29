package com.tipyme.solaris_api.auth;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tipyme.solaris_api.auth.domain.dto.JwtAuthResponseDto;
import com.tipyme.solaris_api.auth.domain.dto.LoginDto;
import com.tipyme.solaris_api.auth.domain.dto.RegisterDto;
import com.tipyme.solaris_api.roles.Role;
import com.tipyme.solaris_api.roles.repository.RoleRepository;
import com.tipyme.solaris_api.security.jwt.JwtGenerator;
import com.tipyme.solaris_api.users.User;
import com.tipyme.solaris_api.users.domain.mapper.UserMapper;
import com.tipyme.solaris_api.users.repository.UserRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@Tag(name = "Authentication", description = "Endpoints for user authentication and registration")
public class AuthController {
    
    private final AuthenticationManager authenticationManager;
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final JwtGenerator jwtGenerator;

    @PostMapping("/login")
    @Operation(summary = "Authenticate user and generate JWT token")
    @ApiResponses(
        value = {
            @ApiResponse(responseCode = "202", description = "Logging succeeded"),
            @ApiResponse(responseCode = "404", description = "User not found")
        }
    )
    public ResponseEntity<JwtAuthResponseDto> authenticateUser(
        @Valid @RequestBody LoginDto loginDto
    ) {
        logger.info("login attempt for user: {}", loginDto.getUsername());
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword())
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = jwtGenerator.generateToken(authentication);

            return new ResponseEntity<>(
                new JwtAuthResponseDto(token), 
                HttpStatus.OK
            );
        } catch (Exception e) {
            logger.error("Authentication failed for user: {}", loginDto.getUsername(), e);
            throw e;
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(
        @Valid @RequestBody RegisterDto registerDto
    ) {
        if (userRepository.existsByUsername(registerDto.getUsername())) {
            return new ResponseEntity<>(
                "User with username " + registerDto.getUsername() + " already exists", 
                HttpStatus.CONFLICT
            );
        }

        if (userRepository.existsByEmail(registerDto.getEmail())) {
            return new ResponseEntity<>(
                "User with email " + registerDto.getEmail() + " already exists", 
                HttpStatus.CONFLICT
            );
        }

        User user = userMapper.registerDtoToUser(registerDto);
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Role roles = roleRepository.findByName("USER").orElse(null);
        if (roles == null) {
            logger.error("Required role 'USER' not found during registration");
            return new ResponseEntity<>("An internal server error occurred. Please contact support.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        user.setRoles(Collections.singleton(roles));

        userRepository.save(user);

        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }
    
}
