package com.strangely.backend.Mapper.User;

import com.strangely.backend.Model.Auth.Dtos.ResetPasswordDTO;
import com.strangely.backend.Model.Auth.Dtos.SignInDTO;
import com.strangely.backend.Model.User.DTOs.UserDTO;
import com.strangely.backend.Model.User.Entities.User;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

public class UserMP {

    public static List<UserDTO> toUserDtos(List<User> u){
        List<UserDTO> ud = new ArrayList<>();

        for (var user: u)
        {
            ud.add(toUserDTO(user));
        }
        return ud;
    }

    public static UserDTO toUserDTO(User user){
        UserDTO u = new UserDTO();
        u.setUserID(user.getId());
        u.setUserName(user.getUsername());
        u.setPassword(user.getPassword());
        u.setEmail(user.getEmail());
        u.setFirstName(user.getFirst_name());
        u.setLastName(user.getLast_name());
        u.setPhone_number(user.getPhone_number());
        if(user.getJoiningDate() != null)
        {
            long timestamp = user.getJoiningDate().toEpochSecond(ZoneOffset.UTC);
            u.setJoiningDate(timestamp);
        }
        u.setAreaId(user.getArea_id());
        u.setResetToken(user.getResetToken());
        return u;
    }


    public static List<User> toUser(List<UserDTO> ud){
        List<User> u = new ArrayList<>();

        for (var userDto: ud)
            u.add(toUser(userDto));

        return u;
    }

    public static User toUser(UserDTO ud){
        User u = new User();
        u.setId(ud.getUserID());
        u.setUsername(ud.getUserName());
        u.setPassword(ud.getPassword());
        u.setEmail(ud.getEmail());
        u.setFirst_name(ud.getFirstName());
        u.setLast_name(ud.getLastName());
        u.setPhone_number(ud.getPhone_number());
        LocalDateTime time = LocalDateTime.ofInstant(Instant.ofEpochSecond(ud.getJoiningDate()), ZoneId.systemDefault());
        u.setJoiningDate(time);
        u.setArea_id(ud.getAreaId());
        u.setResetToken(ud.getResetToken());

        return u;
    }


    public static User signInToUser(SignInDTO sd){
        User u = new User();

        u.setUsername(sd.getUsername());
        u.setPassword(sd.getPassword());
        u.setEmail(sd.getEmail());
        u.setFirst_name(sd.getFirstName());
        u.setLast_name(sd.getLastName());
        u.setPhone_number(sd.getPhone());
        u.setJoiningDate(sd.getJoiningdate());
        u.setArea_id(sd.getAreaId());

        return u;
    }


    public static User resetPasswordToUser(ResetPasswordDTO RP){
        User u = new User();

        u.setId(RP.userid());
        u.setUsername(RP.username());
        u.setPassword(RP.password().toString());
        u.setEmail(RP.email());
        u.setFirst_name(RP.firstName());
        u.setLast_name(RP.lastName());
        u.setPhone_number(RP.phone_number());
        LocalDateTime time = LocalDateTime.ofInstant(Instant.ofEpochSecond(RP.joiningDate()), ZoneId.systemDefault());
        u.setJoiningDate(time);
        u.setArea_id(RP.areaId());
        u.setResetToken(RP.resetToken());
        return u;
    }
}
