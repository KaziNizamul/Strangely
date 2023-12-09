package com.strangely.backend.Service.Auth.Implementation;

import com.strangely.backend.Model.User.Entities.User;
import com.strangely.backend.Repository.User.UserRepository;
import com.strangely.backend.Service.Auth.IUserServiceJPA;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserServiceJPA implements IUserServiceJPA {


    private final UserRepository userRepository;

    public UserServiceJPA(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    // Retrieve a user by their username.
    @Override
    public User findByUsernameU(String username) {
        // Attempt to find a user in the userRepository by their username.
        Optional<User> fetchedUser = userRepository.findByUsername(username);
        User user = fetchedUser.orElse(null);
        return user;
    }

    // Retrieve a user by their reset token.
    @Override
    public Optional<User> findByResetTokenU(String reset_token) {
        // Attempt to find a user in the userRepository by their reset token.
        Optional<User> user = userRepository.findByResetToken(reset_token);
        return user;
    }

}
