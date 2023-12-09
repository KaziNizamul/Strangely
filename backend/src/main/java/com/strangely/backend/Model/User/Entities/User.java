package com.strangely.backend.Model.User.Entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    @Column(name ="userID")
    private int id;

    @Column(name="username")
    private String username;

    @Column(name="first_name")
    private String first_name;

    @Column(name="last_name")
    private String last_name;

    @Column(name="email")
    private String email;

    @Column(name="phone_number")
    private String phone_number;

    @Column(name="joining_date")
    private LocalDateTime joiningDate;

    @Column(name="password")
    private String password;

    @Column(name="area_id")
    private int area_id;

    @Column(name="reset_token")
    private String resetToken;

}
