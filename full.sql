BEGIN;

DROP TABLE IF EXISTS consumers CASCADE;
CREATE TABLE consumers
(
    id bigserial NOT NULL,
    name character varying(255) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT name_unique UNIQUE (name)
);

INSERT INTO consumers (name) VALUES
('Vasya'),
('Misha'),
('Nastya'),
('Masha');

DROP TABLE IF EXISTS products CASCADE;
CREATE TABLE products
(
    id bigserial NOT NULL,
    title character varying(255) NOT NULL,
    price bigint NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT unique_title UNIQUE (title)
);

INSERT INTO products (title,price) VALUES
('water',20),
('apple',30),
('cheese',200),
('pie',70);


DROP TABLE IF EXISTS orders CASCADE;
CREATE TABLE orders
(
    id bigserial NOT NULL,
    consumer_id bigint NOT NULL,
    product_id bigint NOT NULL,
    actual_product_price bigint NOT NULL,
    "number" integer NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_consumer_id_consumers FOREIGN KEY (consumer_id)
        REFERENCES consumers (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT fk_product_id_products FOREIGN KEY (product_id)
        REFERENCES products (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);



COMMIT;