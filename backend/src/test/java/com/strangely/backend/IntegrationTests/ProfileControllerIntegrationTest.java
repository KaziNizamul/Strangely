package com.strangely.backend.IntegrationTests;

import com.strangely.backend.Controller.User.ProfileController;
import com.strangely.backend.Mapper.User.UserMP;
import com.strangely.backend.Model.User.DTOs.UserDTO;
import com.strangely.backend.Model.User.Entities.User;
import com.strangely.backend.Service.Auth.IUserService;
import com.strangely.backend.Service.Auth.IUserServiceJPA;
import com.strangely.backend.Service.User.Implementation.UserProfile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class ProfileControllerIntegrationTest {

    @Mock
    private UserProfile userProfile;

    @Mock
    private IUserService userService;

    @Mock
    private IUserServiceJPA userServiceJPA;

    @Mock
    private UserMP userMapper;

    @InjectMocks
    private ProfileController profileController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testGetUserProfile_ValidUsername_ReturnsUserProfile() {
        // Arrange
        String username = "testUser";
        User mockUser = new User(); // You may want to use a mocking library to create a mock user
        UserDTO mockUserDTO = new UserDTO();// You may want to use a mocking library to create a mock user DTO
        mockUser.setJoiningDate(LocalDateTime.now());
        mockUser.setUsername(username);
        mockUser.setArea_id(1);
        mockUser.setPassword("12234423");
        mockUser.setId(10);
        mockUserDTO = userMapper.toUserDTO(mockUser);

        when(userServiceJPA.findByUsernameU(anyString())).thenReturn(mockUser);

        // Act
        ResponseEntity<UserDTO> responseEntity = profileController.getUserProfile(username);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockUserDTO, responseEntity.getBody());
    }

    @Test
    public void testGetUserProfile_InvalidUsername_ReturnsBadRequest() {
        // Arrange
        String username = "nonexistentUser";

        when(userServiceJPA.findByUsernameU(anyString())).thenReturn(null);

        // Act
        ResponseEntity<UserDTO> responseEntity = profileController.getUserProfile(username);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("User Not Found", responseEntity.getBody());
    }
    @Test
    void testUpdateUserProfile_Success() {
        // Arrange
        UserDTO mockUserDTO = new UserDTO(); // You may want to use a mocking library to create a mock user DTO
        when(userProfile.updateUserProfile(anyString(), any())).thenReturn(mockUserDTO);

        // Act
        ResponseEntity<UserDTO> responseEntity = profileController.updateUserProfile("testUser", new User());

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockUserDTO, responseEntity.getBody());
    }

    @Test
    void testUpdateUserProfile_Failure() {
        // Arrange
        doThrow(new RuntimeException("Update failed")).when(userProfile).updateUserProfile(anyString(), any());

        // Act
        ResponseEntity<UserDTO> responseEntity = profileController.updateUserProfile("testUser", new User());

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("java.lang.RuntimeException: Update failed", responseEntity.getBody());
    }

    @Test
    void testFindAll_WithUsers() {
        List<UserDTO> users = Collections.singletonList(new UserDTO());
        when(userService.findAll()).thenReturn(users);
        ResponseEntity<List<UserDTO>> response = profileController.findAll();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(users, response.getBody());
    }

    @Test
    void testFindAll_NoUsers() {
        when(userService.findAll()).thenThrow(new RuntimeException("No users found"));
        ResponseEntity<List<UserDTO>> response = profileController.findAll();
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}
