package com.tipyme.solaris_api.users.services;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.anyLong;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tipyme.solaris_api.roles.Role;
import com.tipyme.solaris_api.shared.exceptions.ResourceNotFoundException;
import com.tipyme.solaris_api.users.User;
import com.tipyme.solaris_api.users.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private Role adminRole;
    private User user;

    @BeforeEach
    void setUp() {
        adminRole = new Role();
        adminRole.setId(1L);
        adminRole.setName("Admin");

        user = new User();
        user.setId(123L);
        user.setName("Admin");
        user.setUsername("Adminˀ");
        user.setEmail("admin@test.com");
        user.setPassword("password123");
    }

    @Test
    @DisplayName("Should return user when ID exists")
    void shouldReturnUserWheIdExists() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        User foundUser = userService.findById(123L);

        assertNotNull(foundUser);
        assertEquals(user, foundUser);
        verify(userRepository, times(1)).findById(123L);
    }

    @Test
    @DisplayName("Should return ResourceNotFoundException when ID does not exist")
    void shouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
        when(userRepository.findById(anyLong())).thenReturn((Optional.empty()));

        ResourceNotFoundException thrown = assertThrows(
            ResourceNotFoundException.class, () -> {
                userService.findById(123L);
        });

        assertEquals("User with id 123 not found", thrown.getMessage());
        verify(userRepository, times(1)).findById(123L);
    }
}
