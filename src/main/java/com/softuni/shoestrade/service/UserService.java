package com.softuni.shoestrade.service;

import com.softuni.shoestrade.model.Role;
import com.softuni.shoestrade.model.UserEntity;
import com.softuni.shoestrade.model.dto.UserRegistrationDTO;
import com.softuni.shoestrade.model.enums.UserRoles;
import com.softuni.shoestrade.repository.RoleRepository;
import com.softuni.shoestrade.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;

    private RoleRepository roleRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder,RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }


    public void signUp(UserRegistrationDTO userRegistrationDTO) {

        Optional<UserEntity> byEmail = this.userRepository.findByEmail(userRegistrationDTO.getEmail());

        if (byEmail.isPresent()) {
            throw new RuntimeException("email.used");
        }

        Optional<UserEntity> byUsername = this.userRepository.findByUsername(userRegistrationDTO.getUsername());

        if (byUsername.isPresent()) {
            throw new RuntimeException("username.used");
        }

        UserEntity userEntity = new UserEntity(
                userRegistrationDTO.getUsername(),
                passwordEncoder.encode(userRegistrationDTO.getPassword()),
                userRegistrationDTO.getEmail(),
                userRegistrationDTO.getFullName(),
                userRegistrationDTO.getAge(),
                userRegistrationDTO.getDescription(),
                userRegistrationDTO.getGender()

        );

        Role role = roleRepository.findRoleByName(UserRoles.USER);
        userEntity.getRoles().add(role);

        this.userRepository.save(userEntity);
    }

    public Optional<UserEntity> getUserByName(String name) {
        return this.userRepository.findByUsername(name);
    }
}
