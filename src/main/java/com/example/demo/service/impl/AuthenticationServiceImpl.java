package com.example.demo.service.impl;

import com.example.demo.configuration.security.JwtTokenProvider;
import com.example.demo.dto.LoginResponseDto;
import com.example.demo.dto.LoginrequestDto;
import com.example.demo.service.AuthenticationService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthenticationServiceImpl(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public LoginResponseDto getToken(LoginrequestDto loginrequestDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginrequestDto.getUsername(),
                        loginrequestDto.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        LoginResponseDto dto = new LoginResponseDto();
        dto.setToken(jwtTokenProvider.generateToken(authentication));

        return dto;
    }
}
