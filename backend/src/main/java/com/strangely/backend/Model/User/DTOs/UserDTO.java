package com.strangely.backend.Model.User.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDTO {
    private int userID;
    private String userName;
    private String phone_number;
    private String firstName;
    private String lastName;
    private long joiningDate;
    private int areaId;
    private String password;
    private String email;
    private String Token;
    private String resetToken;

}