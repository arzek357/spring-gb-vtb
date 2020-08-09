BEGIN;

DROP TABLE IF EXISTS users CASCADE;
CREATE TABLE users
(
    id bigserial NOT NULL,
    name character varying(255) NOT NULL,
    age smallint NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO users (name,age) VALUES
('Vasya',15),
('Misha',19),
('Nastya',22),
('Sasha',21),
('Lera',38),
('Lena',53),
('Yura',43),
('Masha',23);

DROP TABLE IF EXISTS products CASCADE;
CREATE TABLE products
(
    id bigserial NOT NULL,
    title character varying(255) NOT NULL,
    price bigint NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO products (title, price) VALUES
('Sword',300),
('Bow',500),
('Helm',450),
('Shield',400);

COMMIT;