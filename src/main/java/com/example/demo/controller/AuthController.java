package com.example.demo.controller;


import com.example.demo.api.JwtRequest;
import com.example.demo.api.JwtResponse;
import com.example.demo.exception.UserAlreadyExistException;
import com.example.demo.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<JwtResponse> registration(@RequestBody JwtRequest authRequest ){
        try {
            final JwtResponse token = authService.register(authRequest);
            return ResponseEntity.ok(token);
        }catch (UserAlreadyExistException e){
            return ResponseEntity.status(409).build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest authRequest) {
        final JwtResponse token = authService.login(authRequest);
        return ResponseEntity.ok(token);
    }
}
