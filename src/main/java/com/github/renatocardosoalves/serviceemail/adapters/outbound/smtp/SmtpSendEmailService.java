package com.github.renatocardosoalves.serviceemail.adapters.outbound.smtp;

import com.github.renatocardosoalves.serviceemail.application.domain.Email;
import com.github.renatocardosoalves.serviceemail.application.ports.SendServiceEmailPort;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SmtpSendEmailService implements SendServiceEmailPort {

    private final JavaMailSender mailSender;

    @Override
    public void sendEmailSmtp(Email email) {
        var message = new SimpleMailMessage();
        message.setFrom(email.getFrom());
        message.setTo(email.getTo());
        message.setSubject(email.getSubject());
        message.setText(email.getText());
        this.mailSender.send(message);
    }

}
