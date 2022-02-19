package com.github.renatocardosoalves.serviceemail.application.ports;

import com.github.renatocardosoalves.serviceemail.application.domain.Email;

public interface SendServiceEmailPort {

    void sendEmailSmtp(Email email);

}
