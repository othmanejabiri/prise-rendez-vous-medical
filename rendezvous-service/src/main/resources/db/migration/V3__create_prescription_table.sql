CREATE TABLE prescription (
                              id BIGINT AUTO_INCREMENT PRIMARY KEY,
                              medication VARCHAR(255) NOT NULL,
                              dosage VARCHAR(100),
                              frequency VARCHAR(100),
                              duration INT,
                              prescription_date DATE NOT NULL,
                              instructions TEXT,
                              consultation_id BIGINT NOT NULL,
                              FOREIGN KEY (consultation_id) REFERENCES consultation(id)
);