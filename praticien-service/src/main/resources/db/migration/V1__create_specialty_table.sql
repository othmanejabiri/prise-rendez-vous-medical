CREATE TABLE specialty (
                           id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                           name VARCHAR(100) NOT NULL,
                           description VARCHAR(500)
);
