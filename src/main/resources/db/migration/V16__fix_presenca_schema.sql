-- 1. Garantir que a coluna existe (caso já exista, ignora erro manualmente)
ALTER TABLE presenca ADD COLUMN catequista_id BIGINT;

-- 2. Popular valores (ajuste conforme sua regra)
UPDATE presenca SET catequista_id = 1 WHERE catequista_id IS NULL;

-- 3. Tornar NOT NULL
ALTER TABLE presenca MODIFY catequista_id BIGINT NOT NULL;

-- 4. Criar FK
ALTER TABLE presenca
ADD CONSTRAINT fk_presenca_catequista
FOREIGN KEY (catequista_id) REFERENCES catequista(id);

-- 5. Atualizar UNIQUE
ALTER TABLE presenca DROP INDEX UK6o6rkje37ru0h2ih7xq1ndgia;

ALTER TABLE presenca
ADD UNIQUE KEY uk_presenca_all (catequizando_id, missa_id, catequista_id);