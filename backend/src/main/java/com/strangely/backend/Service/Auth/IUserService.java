package com.strangely.backend.Service.Auth;


import com.strangely.backend.Model.Auth.Dtos.ResetPasswordDTO;
import com.strangely.backend.Model.Auth.Dtos.SignInDTO;
import com.strangely.backend.Model.Auth.Dtos.loginDetailsDTO;
import com.strangely.backend.Model.User.DTOs.UserDTO;

import java.util.List;

public interface IUserService {
    List<UserDTO> findAll();
    UserDTO Insert(UserDTO ud);
    UserDTO login(loginDetailsDTO ld);
    UserDTO register(SignInDTO sd);
    UserDTO reRegister(ResetPasswordDTO RP);
    UserDTO findByUsername(String username);
    UserDTO findByResetToken(String rt);
}
