package com.strangely.backend.UnitTest.Mapper;

import static org.junit.Assert.*;

import com.strangely.backend.Mapper.User.UserMP;
import com.strangely.backend.Model.Auth.Dtos.ResetPasswordDTO;
import com.strangely.backend.Model.Auth.Dtos.SignInDTO;
import com.strangely.backend.Model.User.DTOs.UserDTO;
import com.strangely.backend.Model.User.Entities.User;
import org.junit.Test;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

public class UserMPtest {

    @Test
    public void testToUserDtos() {
        // Sample User objects
        User user1 = new User( 1,"john", "john", "john@example.com", "john@example.com", "1234556738", LocalDateTime.now(), "password1", 1, null);
        User user2 = new User(2, "joey", "joey", "doe@example.com", "doe@example.com", "234353534534", LocalDateTime.now(),"password2", 2, null);

        // List of User objects
        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);

        // Convert to UserDTOs
        List<UserDTO> userDTOs = UserMP.toUserDtos(users);

        // Assertions
        assertNotNull(userDTOs);
        assertEquals(users.size(), userDTOs.size());

        // Check individual mappings
        for (int i = 0; i < users.size(); i++) {
            UserDTO userDTO = userDTOs.get(i);
            User user = users.get(i);

            assertEquals(user.getId(), userDTO.getUserID());
            assertEquals(user.getUsername(), userDTO.getUserName());
            assertEquals(user.getPassword(), userDTO.getPassword());
            assertEquals(user.getEmail(), userDTO.getEmail());
            assertEquals(user.getFirst_name(), userDTO.getFirstName());
            assertEquals(user.getLast_name(), userDTO.getLastName());
            assertEquals(user.getPhone_number(), userDTO.getPhone_number());

            long expectedTimestamp = user.getJoiningDate().toEpochSecond(ZoneOffset.UTC);
            assertEquals(expectedTimestamp, userDTO.getJoiningDate());

            assertEquals(user.getArea_id(), userDTO.getAreaId());
            assertEquals(user.getResetToken(), userDTO.getResetToken());
        }
    }

    @Test
    public void testToUserDTO() {
        // Sample User object
        User user = new User( 1,"john", "john", "john@example.com", "john@example.com", "1234556738", LocalDateTime.now(), "password1", 1, null);
        // Convert to UserDTO
        UserDTO userDTO = UserMP.toUserDTO(user);

        // Assertions
        assertNotNull(userDTO);

        // Check individual mappings
        assertEquals(user.getId(), userDTO.getUserID());
        assertEquals(user.getUsername(), userDTO.getUserName());
        assertEquals(user.getPassword(), userDTO.getPassword());
        assertEquals(user.getEmail(), userDTO.getEmail());
        assertEquals(user.getFirst_name(), userDTO.getFirstName());
        assertEquals(user.getLast_name(), userDTO.getLastName());
        assertEquals(user.getPhone_number(), userDTO.getPhone_number());

        long expectedTimestamp = user.getJoiningDate().toEpochSecond(ZoneOffset.UTC);
        assertEquals(expectedTimestamp, userDTO.getJoiningDate());

        assertEquals(user.getArea_id(), userDTO.getAreaId());
        assertEquals(user.getResetToken(), userDTO.getResetToken());
    }

    @Test
    public void testToUser() {
        // Sample UserDTO objects
        UserDTO userDTO1 = new UserDTO(1, "john","1234567890", "John", "Doe",1637270400L, 1,"password1", "john@example.com", null ,  null);
        UserDTO userDTO2 = new UserDTO(2, "joey","1234567890", "Joey", "Tribbiani",1237270400L, 2,"password2", "joey@example.com", null ,  null);

        // List of UserDTO objects
        List<UserDTO> userDTOs = new ArrayList<>();
        userDTOs.add(userDTO1);
        userDTOs.add(userDTO2);

        // Convert to Users
        List<User> users = UserMP.toUser(userDTOs);

        // Assertions
        assertNotNull(users);
        assertEquals(userDTOs.size(), users.size());

        // Check individual mappings
        for (int i = 0; i < userDTOs.size(); i++) {
            UserDTO userDTO = userDTOs.get(i);
            User user = users.get(i);

            assertEquals(userDTO.getUserID(), user.getId());
            assertEquals(userDTO.getUserName(), user.getUsername());
            assertEquals(userDTO.getPassword(), user.getPassword());
            assertEquals(userDTO.getEmail(), user.getEmail());
            assertEquals(userDTO.getFirstName(), user.getFirst_name());
            assertEquals(userDTO.getLastName(), user.getLast_name());
            assertEquals(userDTO.getPhone_number(), user.getPhone_number());

            LocalDateTime expectedTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(userDTO.getJoiningDate()), ZoneId.systemDefault());
            assertEquals(expectedTime, user.getJoiningDate());

            assertEquals(userDTO.getAreaId(), user.getArea_id());
            assertEquals(userDTO.getResetToken(), user.getResetToken());
        }
    }

    @Test
    public void testToUserFromUserDTO() {
        // Sample UserDTO object
        UserDTO userDTO = UserDTO.builder()
                .userID(1)
                .userName("john_doe")
                .phone_number("1234567890")
                .firstName("John")
                .lastName("Doe")
                .joiningDate(1637270400L)
                .areaId(1)
                .password("password1")
                .email("john@example.com")
                .Token("token1")
                .resetToken("resetToken1")
                .build();

        // Convert to User
        User user = UserMP.toUser(userDTO);

        // Assertions
        assertNotNull(user);

        // Check individual mappings
        assertEquals(userDTO.getUserID(), user.getId());
        assertEquals(userDTO.getUserName(), user.getUsername());
        assertEquals(userDTO.getPassword(), user.getPassword());
        assertEquals(userDTO.getEmail(), user.getEmail());
        assertEquals(userDTO.getFirstName(), user.getFirst_name());
        assertEquals(userDTO.getLastName(), user.getLast_name());
        assertEquals(userDTO.getPhone_number(), user.getPhone_number());

        LocalDateTime expectedTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(userDTO.getJoiningDate()), ZoneId.systemDefault());
        assertEquals(expectedTime, user.getJoiningDate());

        assertEquals(userDTO.getAreaId(), user.getArea_id());
        assertEquals(userDTO.getResetToken(), user.getResetToken());
    }
    @Test
    public void testSignInToUser() {
        // Sample SignInDTO object
        SignInDTO signInDTO = SignInDTO.builder()
                .username("john_doe")
                .password("password1")
                .phone("1234567890")
                .email("john@example.com")
                .joiningdate(LocalDateTime.now())
                .firstName("John")
                .lastName("Doe")
                .areaId(1)
                .build();

        // Convert to User
        User user = UserMP.signInToUser(signInDTO);

        // Assertions
        assertNotNull(user);

        // Check individual mappings
        assertEquals(signInDTO.getUsername(), user.getUsername());
        assertEquals(signInDTO.getPassword(), user.getPassword());
        assertEquals(signInDTO.getEmail(), user.getEmail());
        assertEquals(signInDTO.getFirstName(), user.getFirst_name());
        assertEquals(signInDTO.getLastName(), user.getLast_name());
        assertEquals(signInDTO.getPhone(), user.getPhone_number());
        assertEquals(signInDTO.getJoiningdate(), user.getJoiningDate());
        assertEquals(signInDTO.getAreaId(), user.getArea_id());
    }

    @Test
    public void testResetPasswordToUser() {
        // Sample ResetPasswordDTO object
        char[] password = "password1".toCharArray();
        ResetPasswordDTO resetPasswordDTO = new ResetPasswordDTO(1, "john@example.com", "1234567890", "John", "Doe", 1637270400L, 1, "john_doe",password, "token1");

        // Convert to User
        User user = UserMP.resetPasswordToUser(resetPasswordDTO);

        // Assertions
        assertNotNull(user);

        // Check individual mappings
        assertEquals(resetPasswordDTO.userid(), user.getId());
        assertEquals(resetPasswordDTO.email(), user.getEmail());
        assertEquals(resetPasswordDTO.phone_number(), user.getPhone_number());
        assertEquals(resetPasswordDTO.firstName(), user.getFirst_name());
        assertEquals(resetPasswordDTO.lastName(), user.getLast_name());

        LocalDateTime expectedTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(resetPasswordDTO.joiningDate()), ZoneId.systemDefault());
        assertEquals(expectedTime, user.getJoiningDate());

        assertEquals(resetPasswordDTO.areaId(), user.getArea_id());
        assertEquals(resetPasswordDTO.username(), user.getUsername());
        assertEquals(resetPasswordDTO.resetToken(), user.getResetToken());
    }
}
