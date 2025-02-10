package com.example.demo.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JwtResponse { // todo Этот тоже. Можно делать в папке model 2 подпапки response и request

    private final String type = "Bearer";
    private String accessToken;


}