package com.tipyme.solaris_api.users;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tipyme.solaris_api.users.domain.dto.UserRequestDto;
import com.tipyme.solaris_api.users.domain.dto.UserResponseDto;
import com.tipyme.solaris_api.users.domain.dto.UserUpdateDto;
import com.tipyme.solaris_api.users.domain.mapper.UserMapper;
import com.tipyme.solaris_api.users.interfaces.IUserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;
    private final UserMapper userMapper;
    
    @GetMapping
    public List<UserResponseDto> index() {
        List<User> users = userService.findAll();

        return userMapper.toUserResponseDtoList(users);   
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> create(@Valid @RequestBody UserRequestDto requestDto) {
        User userToSave = userMapper.toEntity(requestDto);
        User savedUser = userService.save(userToSave);
        UserResponseDto responseDto = userMapper.toResponseDto(savedUser);
        
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> get(@PathVariable Long id) {
        User user = userService.findById(id);
        UserResponseDto responseDto = userMapper.toResponseDto(user);

        return ResponseEntity.ok(responseDto);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> update(
        @PathVariable Long id, 
        @Valid @RequestBody UserUpdateDto updateDto
    ) {
        User userToUpdate = userService.findById(id);
        userMapper.updateUserFromDto(updateDto, userToUpdate);
        User updatedUser = userService.save(userToUpdate);

        return ResponseEntity.ok(userMapper.toResponseDto(updatedUser));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
