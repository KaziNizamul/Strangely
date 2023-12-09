package com.strangely.backend.Service.User.Implementation;

import com.strangely.backend.Service.User.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service("emailService")
public class EmailService implements IEmailService {
    @Autowired
    private JavaMailSender mailSender;

    //Send Email to user. Can be used for various purpose, like reset password, etc.
    @Async
    public void sendEmail(SimpleMailMessage email) {
        mailSender.send(email);
    }
}
