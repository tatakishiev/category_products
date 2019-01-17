package com.example.demo.service;

import com.example.demo.dto.LoginResponseDto;
import com.example.demo.dto.LoginrequestDto;

public interface AuthenticationService {
    public LoginResponseDto getToken(LoginrequestDto loginrequestDto);
}
