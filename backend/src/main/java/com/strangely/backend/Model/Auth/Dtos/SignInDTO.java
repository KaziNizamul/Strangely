package com.strangely.backend.Model.Auth.Dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SignInDTO {
    private String username;
    private String password;
    private String phone;
    private String email;
    private LocalDateTime joiningdate;
    private String firstName;
    private String lastName;
    private int areaId;
}
