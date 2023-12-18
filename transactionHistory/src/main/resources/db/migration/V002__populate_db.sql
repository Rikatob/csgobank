insert into transaction (transaction_id, state, from_vault_id, to_vault_id, from_account_id, to_account_id, price, type, item_id)
values  (1, 'COMPLETE', 1, 3, 1, 2, 1500, 'SELL', 1),
        (2, 'COMPLETE', 3, 4, 2, 3, 2500, 'SELL', 1),
        (3, 'COMPLETE', 4, 1, 3, 1, 3000, 'BUY', 1);