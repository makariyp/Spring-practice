package com.example.demo.api;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JwtRequest { // todo Этот класс должен находиться в слое model

    private String username;
    private String password;

}