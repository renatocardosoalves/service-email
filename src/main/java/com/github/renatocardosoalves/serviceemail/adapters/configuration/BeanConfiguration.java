package com.github.renatocardosoalves.serviceemail.adapters.configuration;

import com.github.renatocardosoalves.serviceemail.application.ports.EmailRepositoryPort;
import com.github.renatocardosoalves.serviceemail.application.ports.SendServiceEmailPort;
import com.github.renatocardosoalves.serviceemail.application.services.EmailServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public EmailServiceImpl emailServiceImpl(EmailRepositoryPort emailRepositoryPort, SendServiceEmailPort sendServiceEmailPort) {
        return new EmailServiceImpl(emailRepositoryPort, sendServiceEmailPort);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
