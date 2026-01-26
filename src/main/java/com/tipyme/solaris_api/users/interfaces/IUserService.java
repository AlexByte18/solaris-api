package com.tipyme.solaris_api.users.interfaces;

import java.util.List;

import com.tipyme.solaris_api.users.User;

public interface IUserService {
    List<User> findAll();
    User save(User user);
    User findById(Long id);
    void deleteById(Long id);
}