package com.example.demo.dtoservicetest;

import com.example.demo.dto.CreateUserDto;
import com.example.demo.dto.UserDto;
import com.example.demo.dtoservice.UserDtoService;
import com.example.demo.dtoservice.impl.UserDtoServiceImpl;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserDtoServiceTest {
    private UserDtoService userDtoService;

    @Mock
    private UserService userService;

    @Mock
    private UserMapper userMapper;

    @Before
    public void init() {
        userDtoService = new UserDtoServiceImpl(userService, userMapper);
    }

    @Test
    public void crete_withValidCreateUserDto_shouldCreteUserAndReturnUserDto() {
        User user = createMockUser();
        CreateUserDto userDto = generateCreateUserDto();
        UserDto dto = createUserDto();

        when(userService.create(any())).thenReturn(user);
        when(userMapper.convertToDto(user)).thenReturn(dto);

        UserDto expectedValue = userDtoService.create(userDto);

        assertEquals(expectedValue, dto);
    }


    private User createMockUser() {
        User user = new User();
        user.setId(1L);
        user.setPassword("admin");
        user.setUsername("admin");

        return user;
    }

    private UserDto createUserDto() {
        UserDto userDto = new UserDto();
        userDto.setId(1L);
        userDto.setUsername("admin");

        return userDto;
    }

    private CreateUserDto generateCreateUserDto() {
        CreateUserDto createUserDto = new CreateUserDto();
        createUserDto.setPassword("admin");
        createUserDto.setUsername("admin");

        return createUserDto;
    }
}