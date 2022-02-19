package com.github.renatocardosoalves.serviceemail.application.ports;

import com.github.renatocardosoalves.serviceemail.application.domain.Email;
import com.github.renatocardosoalves.serviceemail.application.domain.PageInfo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EmailServicePort {

    Email sendEmail(Email email);

    List<Email> findAll(PageInfo pageInfo);

    Optional<Email> findById(UUID emailId);

}
