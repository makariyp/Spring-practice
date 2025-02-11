package com.example.demo.service;

import com.example.demo.model.request.JwtRequest;
import com.example.demo.model.response.JwtResponse;
import lombok.NonNull;

public interface AuthService {
    JwtResponse register(@NonNull JwtRequest authRequest);

    JwtResponse login(@NonNull JwtRequest authRequest);

}
