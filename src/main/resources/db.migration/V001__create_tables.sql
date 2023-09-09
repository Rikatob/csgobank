CREATE TABLE item
(
    id          INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    type        VARCHAR(20),
    name        VARCHAR(20),
    float_value INT,
    price       INT,
    wear_category ENUM('FACTORY_NEW',
        'MINIMAL_WEAR',
        'FIELD_TESTED',
        'WELL_WORN',
        'BATTLE_SCARRED')
)