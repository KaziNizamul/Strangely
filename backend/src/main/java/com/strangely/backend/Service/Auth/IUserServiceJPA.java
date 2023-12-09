package com.strangely.backend.Service.Auth;

import com.strangely.backend.Model.User.Entities.User;

import java.util.Optional;

public interface IUserServiceJPA {
    public User findByUsernameU(String username);

    public Optional<User> findByResetTokenU(String reset_token);
}
