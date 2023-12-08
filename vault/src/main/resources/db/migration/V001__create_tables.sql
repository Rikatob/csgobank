CREATE TABLE vault_account
(
    id BIGINT PRIMARY KEY NOT NULL
);

CREATE TABLE vault
(
    vault_id   BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    account_id BIGINT                            NOT NULL,
    FOREIGN KEY (account_id) REFERENCES vault_account (id)
);

CREATE TABLE item
(
    id          BIGINT PRIMARY KEY NOT NULL,
    type        VARCHAR(255),
    name        VARCHAR(255),
    float_value VARCHAR(255),
    price       INT,
    vault_id    BIGINT             NOT NULL,
    FOREIGN KEY (vault_id) REFERENCES vault (vault_id)
);
