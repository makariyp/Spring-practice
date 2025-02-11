package com.example.demo.controller;


import com.example.demo.api.Endpoints;
import com.example.demo.model.request.JwtRequest;
import com.example.demo.model.response.JwtResponse;
import com.example.demo.service.AuthServiceImpl;
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

    private final AuthServiceImpl authService;

    @PostMapping("/register")
    public ResponseEntity<JwtResponse> registration(@RequestBody JwtRequest authRequest) {

        final JwtResponse token = authService.register(authRequest);
        return ResponseEntity.ok(token);

    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest authRequest) {
        final JwtResponse token = authService.login(authRequest);
        return ResponseEntity.ok(token);
    }
}
