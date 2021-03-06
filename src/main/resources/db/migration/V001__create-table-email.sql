CREATE TABLE IF NOT EXISTS tb_email (
        id              BIGINT AUTO_INCREMENT PRIMARY KEY,
        owner_reference VARCHAR(100) NOT NULL,
        from_email      VARCHAR(50) NOT NULL,
        to_email        VARCHAR(50) NOT NULL,
        subject         VARCHAR(50) NOT NULL,
        text            MEDIUMTEXT NOT NULL,
        send_date       TIMESTAMP NOT NULL,
        status          VARCHAR(10) NOT NULL
);