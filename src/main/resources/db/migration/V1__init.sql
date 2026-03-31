CREATE TABLE user_entity
(
    user_id       BIGSERIAL PRIMARY KEY,
    username      VARCHAR(50)  NOT NULL UNIQUE,
    password      VARCHAR(255) NOT NULL,
    gender        VARCHAR(20),
    date_of_birth DATE,
    role          VARCHAR(20),
    created_on    TIMESTAMP    NOT NULL,
    updated_on    TIMESTAMP
);