package com.strangely.backend.UnitTest.Service.Auth;

import com.strangely.backend.Model.User.Entities.User;
import com.strangely.backend.Repository.User.UserRepository;
import com.strangely.backend.Service.Auth.IUserServiceJPA;
import com.strangely.backend.Service.User.Implementation.UserProfile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserProfileTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private IUserServiceJPA userServiceJPA;

    @InjectMocks
    private UserProfile userProfile;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testUpdateUserProfile() {
        String username = "testUser";
        LocalDateTime joiningDate = LocalDateTime.now();
        User user = new User();
        user.setEmail("test@example.com");
        user.setJoiningDate(joiningDate);

        User existingUser = new User();
        existingUser.setUsername(username);
        existingUser.setEmail("old@example.com");
        existingUser.setJoiningDate(joiningDate);

        when(userServiceJPA.findByUsernameU(username)).thenReturn(existingUser);
        when(userRepository.save(any(User.class))).thenReturn(existingUser);

        userProfile.updateUserProfile(username, user);

        verify(userServiceJPA, times(1)).findByUsernameU(username);
        verify(userRepository, times(1)).save(existingUser);

        // Verify that the fields have been updated
        assertEquals("test@example.com", existingUser.getEmail());
    }

    @Test
    public void testUpdateUserProfileUserNotFound() {
        String username = "nonExistingUser";
        User user = new User();
        user.setEmail("test@example.com");

        when(userServiceJPA.findByUsernameU(username)).thenReturn(null);

        userProfile.updateUserProfile(username, user);

        verify(userServiceJPA, times(1)).findByUsernameU(username);
        verify(userRepository, never()).save(any(User.class));
    }
}
