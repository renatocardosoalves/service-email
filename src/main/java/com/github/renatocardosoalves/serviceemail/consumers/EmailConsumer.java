package com.github.renatocardosoalves.serviceemail.consumers;

import com.github.renatocardosoalves.serviceemail.dtos.EmailDto;
import com.github.renatocardosoalves.serviceemail.models.EmailModel;
import com.github.renatocardosoalves.serviceemail.services.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class EmailConsumer {

    private final EmailService emailService;

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void listen(@Payload EmailDto emailDto) {
        var emailModel = new EmailModel();
        BeanUtils.copyProperties(emailDto, emailModel);
        this.emailService.sendEmail(emailModel);
        log.info("E-mail status: {}", emailModel.getStatus());
    }

}
