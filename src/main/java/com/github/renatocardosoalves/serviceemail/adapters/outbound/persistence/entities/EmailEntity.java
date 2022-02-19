package com.github.renatocardosoalves.serviceemail.adapters.outbound.persistence.entities;

import com.github.renatocardosoalves.serviceemail.application.domain.enums.StatusEmail;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "tb_email")
public class EmailEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String ownerReference;

    @Column(name = "from_email", nullable = false)
    private String from;

    @Column(name = "to_email", nullable = false)
    private String to;

    @Column(nullable = false)
    private String subject;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String text;

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime sendDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusEmail status;

}
