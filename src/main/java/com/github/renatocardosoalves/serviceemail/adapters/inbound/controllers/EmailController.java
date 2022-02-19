package com.github.renatocardosoalves.serviceemail.adapters.inbound.controllers;

import com.github.renatocardosoalves.serviceemail.adapters.inbound.dtos.EmailDto;
import com.github.renatocardosoalves.serviceemail.application.domain.Email;
import com.github.renatocardosoalves.serviceemail.application.domain.PageInfo;
import com.github.renatocardosoalves.serviceemail.application.ports.EmailServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/emails")
public class EmailController {

    private final EmailServicePort emailServicePort;

    @PostMapping
    public ResponseEntity<Email> sendEmail(@RequestBody @Valid EmailDto emailDto) {
        var email = new Email();
        BeanUtils.copyProperties(emailDto, email);
        return new ResponseEntity<>(this.emailServicePort.sendEmail(email), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<Email>> getAllEmails(
            @PageableDefault(size = 5, sort = "emailId", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        var pageInfo = new PageInfo();
        BeanUtils.copyProperties(pageable, pageInfo);
        var emails = emailServicePort.findAll(pageInfo);
        return new ResponseEntity<>(new PageImpl<>(emails, pageable, emails.size()), HttpStatus.OK);
    }

    @GetMapping("/{emailId}")
    public ResponseEntity<Object> getEmailById(@PathVariable UUID emailId) {
        return  this.emailServicePort.findById(emailId)
                .<ResponseEntity<Object>>map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>("Email not found", HttpStatus.NOT_FOUND));
    }

}
