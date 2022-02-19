package com.github.renatocardosoalves.serviceemail.adapters.outbound.persistence;

import com.github.renatocardosoalves.serviceemail.adapters.outbound.persistence.entities.EmailEntity;
import com.github.renatocardosoalves.serviceemail.application.domain.Email;
import com.github.renatocardosoalves.serviceemail.application.domain.PageInfo;
import com.github.renatocardosoalves.serviceemail.application.ports.EmailRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class PostgresEmailRepository implements EmailRepositoryPort {

    private final ModelMapper modelMapper;

    private final SpringDataPostgresEmailRepository emailRepository;

    @Transactional
    @Override
    public Email save(Email email) {
        var emailEntity = this.modelMapper.map(email, EmailEntity.class);
        return this.modelMapper.map(this.emailRepository.save(emailEntity), Email.class);
    }

    @Override
    public List<Email> findAll(PageInfo pageInfo) {
        Pageable pageable = PageRequest.of(pageInfo.getPageNumber(), pageInfo.getPageSize());
        return this.emailRepository.findAll(pageable)
                .stream()
                .map(entity -> this.modelMapper.map(entity, Email.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Email> findById(UUID emailId) {
        return this.emailRepository.findById(emailId)
                .map(entity -> this.modelMapper.map(entity, Email.class));
    }

}
