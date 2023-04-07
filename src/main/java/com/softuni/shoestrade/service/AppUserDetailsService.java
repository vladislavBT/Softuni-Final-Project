package com.softuni.shoestrade.service;

import com.softuni.shoestrade.model.Role;
import com.softuni.shoestrade.model.UserEntity;
import com.softuni.shoestrade.repository.UserRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

public class AppUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;


    public AppUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByUsername(username);

        return new User(
                user.get().getUsername(),
                user.get().getPassword(),
                user.get().getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
                        .collect(Collectors.toList())
        );
    }
//    private UserDetails map(UserEntity userEntity) {
//        return new User(
//                userEntity.getUsername(),
//                userEntity.getPassword(),
//                extractAuthorities(userEntity)
//        );
//    }
//
//    private List<GrantedAuthority> extractAuthorities(UserEntity userEntity) {
//        return userEntity.
//                getRoles().
//                stream().
//                map(this::mapRole).
//                toList();
//    }
//
//    private GrantedAuthority mapRole(Role role) {
//        return new SimpleGrantedAuthority("ROLE_" + role.getName());
//    }
}
