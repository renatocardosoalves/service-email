package com.github.renatocardosoalves.serviceemail.adapters.inbound.consumers;

import com.github.renatocardosoalves.serviceemail.adapters.inbound.dtos.EmailDto;
import com.github.renatocardosoalves.serviceemail.application.domain.Email;
import com.github.renatocardosoalves.serviceemail.application.ports.EmailServicePort;
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

    private final EmailServicePort emailServicePort;

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void listen(@Payload EmailDto emailDto) {
        var email = new Email();
        BeanUtils.copyProperties(emailDto, email);
        this.emailServicePort.sendEmail(email);
    }

}
