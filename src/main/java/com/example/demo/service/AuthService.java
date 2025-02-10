package com.example.demo.service;


import com.example.demo.api.JwtRequest;
import com.example.demo.api.JwtResponse;
import com.example.demo.api.Role;
import com.example.demo.config.JwtProvider;
import com.example.demo.exception.AuthException;
import com.example.demo.exception.UserAlreadyExistException;
import com.example.demo.model.UserEntity;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    public JwtResponse register(@NonNull JwtRequest authRequest) throws UserAlreadyExistException { // todo throws излишне

        if (userService.getByUsername(authRequest.getUsername()).isPresent()) {
            throw new UserAlreadyExistException("User already exist");
        }

        String hashedPassword = passwordEncoder.encode(authRequest.getPassword());


        UserEntity newUser = new UserEntity();
        newUser.setUsername(authRequest.getUsername());
        newUser.setPassword(hashedPassword);


        Set<Role> roles = new HashSet<>();
        roles.add(Role.USER);
        newUser.setRoles(roles);
        userService.save(newUser);


        final String accessToken = jwtProvider.generateAccessToken(newUser);


        return new JwtResponse(accessToken);
    }

    public JwtResponse login(@NonNull JwtRequest authRequest) {
        final UserEntity user = userService.getByUsername(authRequest.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        String hashedPassword = passwordEncoder.encode(authRequest.getPassword()); // каждый вызов encode(...) создаёт новый хэш (включая соль).
        // То есть строка всегда будет отличаться от того, что хранится в базе, даже при одинаковом «сыром» пароле.

        if (passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) { // Правильный способ
            final String accessToken = jwtProvider.generateAccessToken(user);

            return new JwtResponse(accessToken);
        } else {
            throw new AuthException("Wrong password");
        }
    }


}