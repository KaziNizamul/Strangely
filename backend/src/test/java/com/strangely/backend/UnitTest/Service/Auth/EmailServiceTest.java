package com.strangely.backend.UnitTest.Service.Auth;

import com.strangely.backend.Service.User.Implementation.EmailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import static org.mockito.Mockito.*;

public class EmailServiceTest {

    @Mock
    private JavaMailSender mailSenderMock;

    @InjectMocks
    private EmailService emailService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSendEmail() throws InterruptedException {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo("recipient@example.com");
        email.setSubject("Test Subject");
        email.setText("This is a test email message.");

        emailService.sendEmail(email);

        Thread.sleep(2000);

        verify(mailSenderMock, times(1)).send(email);
    }
}
