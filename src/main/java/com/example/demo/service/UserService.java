package com.example.demo.service;


import com.example.demo.model.UserEntity;
import com.example.demo.repository.UserRepository;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {


    private final UserRepository userRepository;



    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }


    @Transactional(readOnly = true)
    public Optional<UserEntity> getByUsername(@NonNull String username) {
        UserEntity user = userRepository.findByUsername(username).orElse(null);
        return Optional.ofNullable(user);

    }




    @Transactional
    public void save(UserEntity newUser) {
        userRepository.save(newUser);
    }









}