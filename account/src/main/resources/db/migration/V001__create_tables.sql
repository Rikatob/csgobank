CREATE TABLE account
(
    account_id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    email      VARCHAR(255),
    first_name VARCHAR(255),
    last_name  VARCHAR(255),
    user_name  VARCHAR(255)

);