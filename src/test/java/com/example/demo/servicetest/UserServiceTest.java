package com.example.demo.servicetest;

import com.example.demo.configuration.security.UserPrincipal;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.service.impl.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Before
    public void init() {
        this.userService = new UserServiceImpl(this.userRepository);
    }

    @Test
    public void createUser_shouldCreateAndReturnUser() {
        User user = this.createMockUser();

        when(userRepository.save(user)).thenReturn(user);
        User expectedValue = userService.create(user);

        assertEquals(expectedValue, user);
    }

    @Test
    public void loadByUserId_withExistingId_shouldFindAndReturnUserDetails() {
        UserDetails userPrincipal = this.createUserPrincipal();
        User user = this.createMockUser();

        when(userRepository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(user));
        UserDetails expectedValue = userService.loadUserById(anyLong());

        assertEquals(expectedValue, userPrincipal);
    }

    @Test(expected = EntityNotFoundException.class)
    public void loadByUserId_withNotExistingId_shouldThrowEntityNotFoundException() {
        when(userRepository.findById(1L)).thenThrow(EntityNotFoundException.class);
        UserDetails expectedValue = userService.loadUserById(anyLong());
    }

    @Test
    public void loadByUserName_withExistingName_shouldFindAndReturnUserDetails() {
        UserDetails userPrincipal = this.createUserPrincipal();
        User user = this.createMockUser();

        when(userRepository.findByUsername("admin")).thenReturn(java.util.Optional.ofNullable(user));
        UserDetails expectedValue = userService.loadUserByUsername("admin");

        assertEquals(expectedValue, userPrincipal);
    }

    @Test(expected = UsernameNotFoundException.class)
    public void loadByUserName_withNotExistingUserName_shouldThrowUserNotFoundException() {
        when(userRepository.findByUsername("adminka")).thenThrow(UsernameNotFoundException.class);
        UserDetails expectedValue = userService.loadUserByUsername("adminka");
    }

    private User createMockUser() {
        User user = new User();
        user.setId(1L);
        user.setPassword("admin");
        user.setUsername("admin");

        return user;
    }

    private UserPrincipal createUserPrincipal() {
        UserPrincipal userPrincipal = new UserPrincipal(
                1L,
                "admin",
                "admin",
                "admin",
                "admin",
                new ArrayList<>()
        );

        return userPrincipal;
    }
}
