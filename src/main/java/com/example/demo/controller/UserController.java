package com.example.demo.controller;

import com.example.demo.dto.CreateUserDto;
import com.example.demo.dto.UserDto;
import com.example.demo.dtoservice.UserDtoService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserDtoService userDtoService;

    public UserController(UserDtoService userDtoService) {
        this.userDtoService = userDtoService;
    }

    @PostMapping
    public UserDto create(@Validated @RequestBody CreateUserDto createUserDto) {
        return userDtoService.create(createUserDto);
    }
}
