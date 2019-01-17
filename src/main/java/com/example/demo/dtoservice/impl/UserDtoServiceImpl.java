package com.example.demo.dtoservice.impl;

import com.example.demo.dto.CreateUserDto;
import com.example.demo.dto.UserDto;
import com.example.demo.dtoservice.UserDtoService;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDtoServiceImpl implements UserDtoService {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserDtoServiceImpl(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @Override
    @Transactional
    public UserDto create(CreateUserDto createUserDto) {
        User user = userService.create(convertToUser(createUserDto));


        return userMapper.convertToDto(user);
    }

    private User convertToUser(CreateUserDto createUserDto) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        User user = new User();
        user.setPassword(encoder.encode(createUserDto.getPassword()));
        user.setUsername(createUserDto.getUsername());

        return user;
    }
}
