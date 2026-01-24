package com.tipyme.solaris_api.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tipyme.solaris_api.users.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
}
