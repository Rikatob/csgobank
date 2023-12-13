CREATE TABLE transaction
(
    transaction_id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    state ENUM('PENDING', 'ACCEPTED', 'DECLINED','COMPLETE','VALIDATED', 'FAILED', 'CREATED'),
    from_vault_id BIGINT,
    to_vault_id BIGINT,
    from_account_id BIGINT,
    to_account_id BIGINT,
    price INT,
    type ENUM('TRANSFER', 'BUY', 'SELL'),
    item_id BIGINT
);
