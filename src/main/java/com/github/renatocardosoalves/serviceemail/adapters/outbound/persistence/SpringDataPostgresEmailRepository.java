package com.github.renatocardosoalves.serviceemail.adapters.outbound.persistence;

import com.github.renatocardosoalves.serviceemail.adapters.outbound.persistence.entities.EmailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataPostgresEmailRepository extends JpaRepository<EmailEntity, UUID> {
}
