package com.strangely.backend.Service.User;

import com.strangely.backend.Model.User.DTOs.UserDTO;
import com.strangely.backend.Model.User.Entities.User;

public interface IUserProfile {
    UserDTO updateUserProfile(String username, User user);
}
