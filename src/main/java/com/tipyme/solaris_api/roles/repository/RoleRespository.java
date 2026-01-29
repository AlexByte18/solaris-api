package com.tipyme.solaris_api.roles.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tipyme.solaris_api.roles.Role;

@Repository
public interface  RoleRepository extends JpaRepository<Role, Long> {
     Optional<Role> getByName(String name);
     Optional<Role> findByName(String name);
}
