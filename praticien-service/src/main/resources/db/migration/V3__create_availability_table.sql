CREATE TABLE availability (
                              id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                              date DATE NOT NULL,
                              start_time TIME NOT NULL,
                              end_time TIME NOT NULL,
                              available BOOLEAN DEFAULT TRUE,
                              doctor_id BIGINT NOT NULL,
                              CONSTRAINT fk_availability_doctor FOREIGN KEY (doctor_id) REFERENCES doctor(id)
);
