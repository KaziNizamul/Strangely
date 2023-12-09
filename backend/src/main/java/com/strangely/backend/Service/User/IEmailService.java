package com.strangely.backend.Service.User;

import org.springframework.mail.SimpleMailMessage;

public interface IEmailService {
    public void sendEmail( SimpleMailMessage email);
}
