package com.strangely.backend.UnitTest.Service.Auth;

import com.strangely.backend.Model.User.Entities.User;
import com.strangely.backend.Repository.User.UserRepository;
import com.strangely.backend.Service.Auth.Implementation.UserServiceJPA;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceJPATest {

    @Mock
    private UserRepository userRepository;

    private UserServiceJPA userServiceJPA;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userServiceJPA = new UserServiceJPA(userRepository);
    }

    @Test
    void testFindByUsernameU_UserExists() {
        String username = "testUser";
        User user = new User();
        user.setUsername(username);

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));

        User result = userServiceJPA.findByUsernameU(username);

        assertNotNull(result);
        assertEquals(username, result.getUsername());
        verify(userRepository, times(1)).findByUsername(username);
    }

    @Test
    void testFindByUsernameU_UserDoesNotExist() {
        String username = "nonExistingUser";

        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        User result = userServiceJPA.findByUsernameU(username);

        assertNull(result);
        verify(userRepository, times(1)).findByUsername(username);
    }

    @Test
    void testFindByResetTokenU_TokenExists() {
        String resetToken = "testToken";
        User user = new User();
        user.setResetToken(resetToken);

        when(userRepository.findByResetToken(resetToken)).thenReturn(Optional.of(user));

        Optional<User> result = userServiceJPA.findByResetTokenU(resetToken);

        assertTrue(result.isPresent());
        assertEquals(resetToken, result.get().getResetToken());
        verify(userRepository, times(1)).findByResetToken(resetToken);
    }

    @Test
    void testFindByResetTokenU_TokenDoesNotExist() {
        String resetToken = "nonExistingToken";

        when(userRepository.findByResetToken(resetToken)).thenReturn(Optional.empty());

        Optional<User> result = userServiceJPA.findByResetTokenU(resetToken);

        assertFalse(result.isPresent());
        verify(userRepository, times(1)).findByResetToken(resetToken);
    }
}
