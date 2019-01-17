package com.example.demo.dtoservice;

import com.example.demo.dto.CreateUserDto;
import com.example.demo.dto.UserDto;

public interface UserDtoService {
    public UserDto create(CreateUserDto userDto);
}
