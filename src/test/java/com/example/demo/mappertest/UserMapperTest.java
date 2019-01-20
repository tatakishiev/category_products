package com.example.demo.mappertest;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.mapper.MapperImpl.UserMapperImpl;
import com.example.demo.mapper.UserMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class UserMapperTest {
    private UserMapper userMapper;

    @Before
    public void init() {
        userMapper = new UserMapperImpl();
    }

    @Test
    public void convertToDto_validUser_shouldConvertAndReturnUserDto() {
        UserDto userDto = createUserDto();
        User user = createMockUser();

        UserDto expectedValue = userMapper.convertToDto(user);
        assertEquals(expectedValue, userDto);
    }

    private UserDto createUserDto() {
        UserDto userDto = new UserDto();
        userDto.setId(1L);
        userDto.setUsername("admin");

        return userDto;
    }

    private User createMockUser() {
        User user = new User();
        user.setId(1L);
        user.setPassword("admin");
        user.setUsername("admin");

        return user;
    }

}
