CREATE TABLE items
(
    id          INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    type        VARCHAR(20),
    name        VARCHAR(20),
    float_value INT,
    price       INT,
    wear_category varchar(20)
)