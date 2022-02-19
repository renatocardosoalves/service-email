package com.github.renatocardosoalves.serviceemail.services;

import com.github.renatocardosoalves.serviceemail.enums.StatusEmail;
import com.github.renatocardosoalves.serviceemail.models.EmailModel;
import com.github.renatocardosoalves.serviceemail.repositories.EmailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class EmailService {

    private final EmailRepository emailRepository;

    private final JavaMailSender mailSender;

    @Transactional
    public EmailModel sendEmail(EmailModel emailModel) {
        try {
            var message = new SimpleMailMessage();
            message.setFrom(emailModel.getFrom());
            message.setTo(emailModel.getTo());
            message.setSubject(emailModel.getSubject());
            message.setText(emailModel.getText());

            this.mailSender.send(message);

            emailModel.setStatus(StatusEmail.SENT);
        } catch (MailException e) {
            emailModel.setStatus(StatusEmail.ERROR);
        }

        return this.emailRepository.save(emailModel);
    }

}
