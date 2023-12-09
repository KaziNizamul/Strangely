package com.strangely.backend.UnitTest.Service.Auth;

import com.strangely.backend.DataAccess.User.Implementation.UserDAO;
import com.strangely.backend.Model.Auth.Dtos.ResetPasswordDTO;
import com.strangely.backend.Model.Auth.Dtos.SignInDTO;
import com.strangely.backend.Model.Auth.Dtos.loginDetailsDTO;
import com.strangely.backend.Model.User.Entities.User;
import com.strangely.backend.Model.User.DTOs.UserDTO;
import com.strangely.backend.Service.Auth.Implementation.UserService;
import com.strangely.backend.Service.Exception.Restexception;
import com.strangely.backend.Service.Auth.IUserServiceJPA;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserDAO userDAO;

    @Mock
    private IUserServiceJPA userServiceJPA;

    @InjectMocks
    private UserService userService;
    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindAll() {
        when(userDAO.findAll()).thenReturn(Collections.singletonList(new User()));

        List<UserDTO> result = userService.findAll();

        verify(userDAO).findAll();
        assertNotNull(result);
    }

    @Test
    void testInsert() {
        when(userDAO.Insert(any())).thenReturn(new User());
        when(userDAO.findAll()).thenReturn(Collections.singletonList(new User()));

        UserDTO result = userService.Insert(UserDTO.builder().build());

        verify(userDAO).Insert(any());
        assertNotNull(result);
    }


    @Test
    void testLogin_WrongPassword() {
        when(userServiceJPA.findByUsernameU(any())).thenReturn(new User());
        when(passwordEncoder.matches(any(), any())).thenReturn(false);

        assertThrows(Restexception.class, () ->
                userService.login(new loginDetailsDTO("username", "wrongpassword".toCharArray())));
    }


    @Test
    void testLogin_UserNotFound() {
        when(userServiceJPA.findByUsernameU(any())).thenReturn(null);

        assertThrows(Restexception.class, () ->
                userService.login(new loginDetailsDTO("nonexistentuser", "password".toCharArray())));
    }

    @Test
    void testRegister() {
        when(userServiceJPA.findByUsernameU(any())).thenReturn(null);
        when(userDAO.Insert(any())).thenReturn(new User());

        UserDTO result = userService.register(SignInDTO.builder().username("username").password("password").build());

        verify(userServiceJPA).findByUsernameU(any());
        verify(userDAO).Insert(any());
        assertNotNull(result);
    }

    @Test
    void testRegister_UsernameExists() {
        when(userServiceJPA.findByUsernameU(any())).thenReturn(new User());

        assertThrows(Restexception.class, () ->
                userService.register(SignInDTO.builder().username("existingusername").password("password").build()));
    }

    @Test
    void testReRegister() {
        when(userDAO.Insert(any())).thenReturn(new User());

        UserDTO result = userService.reRegister(new ResetPasswordDTO(1, "email", "phone", "firstName", "lastName",
                1234556L, 1, "username", "password".toCharArray(), "resetToken"));

        verify(userDAO).Insert(any());
        assertNotNull(result);
    }

    @Test
    void testFindByUsername() {
        when(userDAO.findBy(any(), any())).thenReturn(Optional.of(new User()));

        UserDTO result = userService.findByUsername("existingusername");

        verify(userDAO).findBy(any(), any());
        assertNotNull(result);
    }

    @Test
    void testFindByUsername_UserNotFound() {
        when(userDAO.findBy(any(), any())).thenReturn(Optional.empty());

        UserDTO result = userService.findByUsername("nonexistentuser");

        verify(userDAO).findBy(any(), any());
        assertNull(result);
    }

    @Test
    void testFindByResetToken() {
        when(userServiceJPA.findByResetTokenU(any())).thenReturn(Optional.of(new User()));

        UserDTO result = userService.findByResetToken("token");

        verify(userServiceJPA).findByResetTokenU(any());
        assertNotNull(result);
    }

    @Test
    void testFindByResetToken_UserNotFound() {
        when(userServiceJPA.findByResetTokenU(any())).thenReturn(Optional.empty());

        UserDTO result = userService.findByResetToken("nonexistenttoken");

        verify(userServiceJPA).findByResetTokenU(any());
        assertNull(result);
    }
}
