package com.strangely.backend.Model.Auth.Dtos;

import java.time.LocalDateTime;
import java.util.Date;

public record ResetPasswordDTO(int userid, String email, String phone_number, String firstName, String lastName, long joiningDate, int areaId, String username, char[] password, String resetToken) {
}
