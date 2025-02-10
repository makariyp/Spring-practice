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
    public UserDetailsService userDetailsService() { // todo Бины обычно создаются в классе конфигурации, отлично подойдет SecurityConfig. А затем автоинжектятся
        return username -> userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
    // todo Либо даже лучше будет реализовать интерфейс UserDetailsService и тут же определить метод loadUserByUsername


    @Transactional(readOnly = true)
    public Optional<UserEntity> getByUsername(@NonNull String username) {
        UserEntity user = userRepository.findByUsername(username).orElse(null);
        return Optional.ofNullable(user); // todo Можно просто return userRepository.findByUsername(username); возвращается и так Optional

    }




    @Transactional
    public void save(UserEntity newUser) {
        userRepository.save(newUser);
    }









}