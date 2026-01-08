CREATE TABLE patient (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         first_name VARCHAR(100) NOT NULL,
                         last_name VARCHAR(100) NOT NULL,
                         email VARCHAR(150) NOT NULL UNIQUE,
                         phone VARCHAR(20),
                         date_of_birth DATE,
                         address VARCHAR(255)
);