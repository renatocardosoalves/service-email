package com.github.renatocardosoalves.serviceemail.application.services;

import com.github.renatocardosoalves.serviceemail.application.domain.Email;
import com.github.renatocardosoalves.serviceemail.application.domain.PageInfo;
import com.github.renatocardosoalves.serviceemail.application.domain.enums.StatusEmail;
import com.github.renatocardosoalves.serviceemail.application.ports.EmailRepositoryPort;
import com.github.renatocardosoalves.serviceemail.application.ports.EmailServicePort;
import com.github.renatocardosoalves.serviceemail.application.ports.SendServiceEmailPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailServicePort {

    private final EmailRepositoryPort emailRepositoryPort;

    private final SendServiceEmailPort sendServiceEmailPort;

    @Override
    public Email sendEmail(Email email) {
        try {
            this.sendServiceEmailPort.sendEmailSmtp(email);
            email.setStatus(StatusEmail.SENT);
            log.info("E-mail status: {}", email.getStatus());
        } catch (Exception e) {
            email.setStatus(StatusEmail.ERROR);
        }
        return this.emailRepositoryPort.save(email);
    }

    @Override
    public List<Email> findAll(PageInfo pageInfo) {
        return this.emailRepositoryPort.findAll(pageInfo);
    }

    @Override
    public Optional<Email> findById(UUID emailId) {
        return this.emailRepositoryPort.findById(emailId);
    }

}
