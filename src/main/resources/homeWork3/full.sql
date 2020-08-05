BEGIN;

DROP TABLE IF EXISTS users CASCADE;
CREATE TABLE users
(
    id bigserial NOT NULL,
    name character varying(255) NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO users (name) VALUES
('Vasya'),
('Misha'),
('Nastya'),
('Sasha'),
('Lera'),
('Lena'),
('Yura'),
('Masha');

DROP TABLE IF EXISTS lots CASCADE;
CREATE TABLE lots
(
    id bigserial NOT NULL,
    title character varying(255) NOT NULL,
    actual_rate bigint NOT NULL,
    last_rate_user_id bigint,
    version bigint,
    PRIMARY KEY (id),
    CONSTRAINT fk_last_rate_user_id_users FOREIGN KEY (last_rate_user_id)
        REFERENCES users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

INSERT INTO lots (title, actual_rate,version) VALUES
('Sword',0,0),
('Bow',0,0),
('Helm',0,0),
('Shield',0,0);

COMMIT;