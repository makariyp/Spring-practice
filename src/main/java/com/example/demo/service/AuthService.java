package com.example.demo.service;

import com.example.demo.model.request.JwtRequest;
import com.example.demo.model.request.RegistrationRequest;
import com.example.demo.model.response.JwtResponse;
import lombok.NonNull;

public interface AuthService {
    JwtResponse register(@NonNull RegistrationRequest regRequest);

    JwtResponse login(@NonNull JwtRequest authRequest);

}
