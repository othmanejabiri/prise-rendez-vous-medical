CREATE TABLE insurance (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           provider_name VARCHAR(150) NOT NULL,
                           policy_number VARCHAR(100) NOT NULL UNIQUE,
                           expiration_date DATE,
                           patient_id BIGINT NOT NULL,
                           FOREIGN KEY (patient_id) REFERENCES patient(id)
);