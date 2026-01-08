CREATE TABLE consultation (
                              id BIGINT AUTO_INCREMENT PRIMARY KEY,
                              consultation_date TIMESTAMP NOT NULL,
                              notes TEXT,
                              diagnosis VARCHAR(500),
                              fee DECIMAL(10, 2),
                              appointment_id BIGINT NOT NULL UNIQUE,
                              FOREIGN KEY (appointment_id) REFERENCES appointment(id)
);