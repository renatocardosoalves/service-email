package com.github.renatocardosoalves.serviceemail.controllers;

import com.github.renatocardosoalves.serviceemail.dtos.EmailDto;
import com.github.renatocardosoalves.serviceemail.models.EmailModel;
import com.github.renatocardosoalves.serviceemail.services.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/emails")
public class EmailController {

    private final EmailService emailService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmailModel sendEmail(@RequestBody @Valid EmailDto emailDto) {
        var emailModel = new EmailModel();
        BeanUtils.copyProperties(emailDto, emailModel);
        return this.emailService.sendEmail(emailModel);
    }

}
