ALTER TABLE catechists
    ADD COLUMN user_id BIGINT NULL;

ALTER TABLE catechists
    ADD CONSTRAINT uk_catechists_user_id UNIQUE (user_id);

ALTER TABLE catechists
    ADD CONSTRAINT fk_catechists_user
        FOREIGN KEY (user_id) REFERENCES users(id);