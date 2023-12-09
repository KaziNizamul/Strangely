package com.strangely.backend.Service.Auth.Implementation;

import com.strangely.backend.DataAccess.User.Implementation.UserDAO;
import com.strangely.backend.Mapper.User.UserMP;
import com.strangely.backend.Model.Auth.Dtos.ResetPasswordDTO;
import com.strangely.backend.Model.User.DTOs.UserDTO;
import com.strangely.backend.Model.Auth.Dtos.SignInDTO;
import com.strangely.backend.Model.Auth.Dtos.loginDetailsDTO;
import com.strangely.backend.Model.Auth.Entities.ID;
import com.strangely.backend.Model.User.Entities.User;
import com.strangely.backend.Service.Auth.IUserService;
import com.strangely.backend.Service.Auth.IUserServiceJPA;
import com.strangely.backend.Service.Exception.Restexception;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.CharBuffer;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService implements IUserService {
    private UserDAO userDAO;
    private PasswordEncoder passwordEncoder;

    private IUserServiceJPA userServiceJPA;

    @Autowired
    public UserService(UserDAO userDAO, IUserServiceJPA userServiceJPA) {

        this.userDAO = userDAO;
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.userServiceJPA = userServiceJPA;
    }

    @Override
    public List<UserDTO> findAll() {
        return UserMP.toUserDtos(userDAO.findAll());
    }

    // Insert a new user into the database.
    @Override
    @Transactional
    public UserDTO Insert(UserDTO userDTO) {
        // Convert the UserDTO to a User entity using the UserMP utility class.
        User user = userDAO.Insert(UserMP.toUser(userDTO));
        return UserMP.toUserDTO(user);
    }


    // Perform user login authentication.
    @Override
    public UserDTO login(loginDetailsDTO loginDetailsDTO) {
        // Attempt to find a user in the userServiceJPA by their username.
        User user = userServiceJPA.findByUsernameU(loginDetailsDTO.username());
        if (user != null) {
            // Check if the provided password matches the stored and encoded password.
            if (passwordEncoder.matches(CharBuffer.wrap(loginDetailsDTO.password()), user.getPassword())) {
                return UserMP.toUserDTO(user);
            } else {
                throw new Restexception("Wrong password. Please try again", HttpStatus.BAD_REQUEST);
            }
        } else {
            throw new Restexception("User not identified", HttpStatus.NOT_FOUND);
        }
    }


    // Register a new user.
    @Override
    public UserDTO register(SignInDTO signInDTO) {
        // Attempt to find a user in the userServiceJPA by their username.
        Optional<User> existUser = Optional.ofNullable(userServiceJPA.findByUsernameU(signInDTO.getUsername()));

        // Check if the user with the provided username already exists.
        if (existUser.isPresent()) {
            throw new Restexception("Username already exists in the server. Please try a new one", HttpStatus.BAD_REQUEST);
        }
        // Convert the SignInDTO to a User entity using the UserMP utility class.
        User user = UserMP.signInToUser(signInDTO);
        // Encode the password using the passwordEncoder.
        user.setPassword(passwordEncoder.encode(CharBuffer.wrap(signInDTO.getPassword())));
        // Insert the user into the database using the userDAO.
        User insertedUser = userDAO.Insert(user);
        // Convert the inserted user to a UserDTO using the UserMP utility class and return it.
        return UserMP.toUserDTO(insertedUser);
    }


    // Re-register a user with a reset password.
    @Override
    public UserDTO reRegister(ResetPasswordDTO resetPasswordDTO) {
        // Convert the resetPasswordDTO to a User entity using the UserMP utility class.
        User user = UserMP.resetPasswordToUser(resetPasswordDTO);
        user.setPassword(passwordEncoder.encode(CharBuffer.wrap(resetPasswordDTO.password())));
        User updatedUser = userDAO.Insert(user);
        return UserMP.toUserDTO(updatedUser);
    }

    // Retrieve a user DTO by their username.
    @Override
    public UserDTO findByUsername(String username) {
        // Attempt to find a user in the userDAO by their username.
        Optional<User> userOptional = userDAO.findBy(username, ID.Username);
        if (userOptional.isPresent()) {
            return UserMP.toUserDTO(userOptional.get());
        } else {
            return null;
        }
    }



    // Retrieve a user DTO by their reset token.
    @Override
    public UserDTO findByResetToken(String resetToken) {
        // Attempt to find a user in the userServiceJPA by their reset token.
        Optional<User> userOptional = userServiceJPA.findByResetTokenU(resetToken);
        if (userOptional.isPresent()) {
            return UserMP.toUserDTO(userOptional.get());
        } else {
            return null;
        }
    }

}
