CREATE TABLE transaction
(
    transaction_id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    completed BOOL,
    from_vault_id BIGINT,
    to_vault_id BIGINT,
    item_id BIGINT
);