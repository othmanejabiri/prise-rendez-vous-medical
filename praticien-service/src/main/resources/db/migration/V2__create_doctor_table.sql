CREATE TABLE doctor (
                        id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                        first_name VARCHAR(100) NOT NULL,
                        last_name VARCHAR(100) NOT NULL,
                        email VARCHAR(150) NOT NULL UNIQUE,
                        phone VARCHAR(20),
                        specialty_id BIGINT,
                        CONSTRAINT fk_doctor_specialty FOREIGN KEY (specialty_id) REFERENCES specialty(id)
);
