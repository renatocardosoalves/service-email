package com.github.renatocardosoalves.serviceemail.application.domain;

import com.github.renatocardosoalves.serviceemail.application.domain.enums.StatusEmail;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class Email {

    private UUID id;

    private String ownerReference;

    private String from;

    private String to;

    private String subject;

    private String text;

    private LocalDateTime sendDate;

    private StatusEmail status;

}
