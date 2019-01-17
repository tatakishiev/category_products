package com.example.demo.mapper;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;

public interface UserMapper {
    public UserDto convertToDto(User user);
}
