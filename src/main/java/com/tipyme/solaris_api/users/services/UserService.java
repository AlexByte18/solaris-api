package com.tipyme.solaris_api.users.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tipyme.solaris_api.shared.exceptions.ResourceNotFoundException;
import com.tipyme.solaris_api.users.User;
import com.tipyme.solaris_api.users.interfaces.IUserService;
import com.tipyme.solaris_api.users.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("User with id " + id + " not found")
        );
    }

    @Override
    public void deleteById(Long id) {
        User userToDelete = this.findById(id);
        userRepository.delete(userToDelete);
    }
}
