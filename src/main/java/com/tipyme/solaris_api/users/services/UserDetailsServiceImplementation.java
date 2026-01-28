package com.tipyme.solaris_api.users.services;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tipyme.solaris_api.roles.Role;
import com.tipyme.solaris_api.users.User;
import com.tipyme.solaris_api.users.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImplementation implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        User user = userRepository.getByUsername(username).orElseThrow(
            () -> new UsernameNotFoundException("User not found")
        );

        return new org.springframework.security.core.userdetails.User(
            user.getUsername(),
            user.getPassword(),
            mapRolesToAuthorities(user.getRoles())
        );
    }

    private Collection<? extends  GrantedAuthority> mapRolesToAuthorities(Set<Role> roles) {
        return roles.stream().map(
            role -> new SimpleGrantedAuthority(role.getName())
        ).collect(Collectors.toList());
    }   

}
