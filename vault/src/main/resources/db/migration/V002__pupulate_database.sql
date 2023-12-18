INSERT INTO vault_account(id)
VALUES (1),
       (2),
       (3);

INSERT INTO vault(vault_id, account_id)
VALUES (1, 1),
       (2, 1),
       (3, 2),
       (4, 3);

INSERT INTO item(id, type, name, float_value, price, vault_id)
VALUES (1, 'AK-47', 'FireSerpent', '0.06008401885629', 4032272, 1),
       (15, 'DesertEagle', 'Sputnik', '0.18000268936157', 142, 1),
       (13, 'FlipKnife', 'Slaughter', '0.11035592854023', 542952, 2),
       (12, 'GutKnife', 'Stained', '0.41015723347664', 163071, 2),
       (3, 'M4A4', 'Howl', '0.00999074894935 ', 10328163, 3),
       (7, 'AWP', 'DragonLore', '0.57256782054901', 4892245, 3),
       (8, 'AWP', 'Medusa', '0.08585689216852', 3044065, 4),
       (5, 'M4A4', 'Poseidon', '0.11244633048773', 1177822, 4),
       (10, 'ButterflyKnife', 'Lore', '0.02475998364389', 5886355, 4);




