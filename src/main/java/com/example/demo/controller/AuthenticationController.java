package com.example.demo.controller;

import com.example.demo.dto.LoginResponseDto;
import com.example.demo.dto.LoginrequestDto;
import com.example.demo.service.AuthenticationService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping
    public LoginResponseDto signIn(@Validated @RequestBody LoginrequestDto loginrequestDto) {
        return authenticationService.getToken(loginrequestDto);
    }
}
