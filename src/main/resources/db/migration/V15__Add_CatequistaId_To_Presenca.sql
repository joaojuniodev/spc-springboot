ALTER TABLE presenca ADD COLUMN catequista_id BIGINT;

ALTER TABLE presenca
    ADD CONSTRAINT fk_presenca_catequista
    FOREIGN KEY (catequista_id) REFERENCES catequista(id);