CREATE TABLE IF NOT EXISTS item
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    type        VARCHAR(255),
    name        VARCHAR(255),
    float_value VARCHAR(255),
    price       INT

);