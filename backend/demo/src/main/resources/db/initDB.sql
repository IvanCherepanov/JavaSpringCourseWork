--DROP TABLE IF EXISTS item;
CREATE TABLE IF NOT EXISTS item
(
    id    SERIAL NOT NULL PRIMARY KEY ,
    cost INTEGER ,
    item_name  VARCHAR(256) NOT NULL,
    description VARCHAR(256) NOT NULL,
    item_type_id INTEGER,
    pet_type_id INTEGER
    );
--DROP TABLE IF EXISTS pet_type;
CREATE TABLE IF NOT EXISTS pet_type
(
    id    SERIAL NOT NULL PRIMARY KEY ,
    pet_type_name  VARCHAR(256) NOT NULL
    );
--DROP TABLE IF EXISTS item_type;
CREATE TABLE IF NOT EXISTS item_type
(
    id    SERIAL NOT NULL PRIMARY KEY ,
    item_type_name  VARCHAR(256) NOT NULL
    );
--DROP TABLE IF EXISTS users;
CREATE TABLE IF NOT EXISTS users
(
    id SERIAL PRIMARY KEY ,
    username VARCHAR(256) NOT NULL,
    password VARCHAR(256) NOT NULL,
    email VARCHAR(256) NOT NULL,
    role VARCHAR(256) NOT NULL
    );
--DROP TABLE IF EXISTS shopping_basket;
CREATE TABLE IF NOT EXISTS shopping_basket
(
    id SERIAL PRIMARY KEY ,
    user_id INTEGER,
    item_id INTEGER,
    amount INTEGER
    );