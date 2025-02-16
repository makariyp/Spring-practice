package com.example.demo.controller;


import com.example.demo.api.Endpoints;
import com.example.demo.model.request.JwtRequest;
import com.example.demo.model.request.RegistrationRequest;
import com.example.demo.model.response.JwtResponse;
import com.example.demo.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Endpoints.AUTH)
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<JwtResponse> registration(@RequestBody RegistrationRequest regRequest) {

        final JwtResponse token = authService.register(regRequest);
        return ResponseEntity.ok(token);

    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest authRequest) {
        final JwtResponse token = authService.login(authRequest);
        return ResponseEntity.ok(token);
    }
}
