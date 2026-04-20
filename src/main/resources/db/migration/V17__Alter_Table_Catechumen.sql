-- 1. renomeia tabela
ALTER TABLE catequizando RENAME TO catechumens;

-- 2. remove fk
ALTER TABLE catechumens
DROP FOREIGN KEY FK8hmsttop6jmk8dcy8aly8fls1;

-- 3. remove index antigo
ALTER TABLE catechumens
DROP INDEX FK8hmsttop6jmk8dcy8aly8fls1;

-- 4. renomeia coluna da fk
ALTER TABLE catechumens
RENAME COLUMN etapa_id TO step_id;

-- 5. cria novo index (nome padrão)
ALTER TABLE catechumens
ADD INDEX idx_catechumens_step_id (step_id);

-- 6. recria fk
ALTER TABLE catechumens
ADD CONSTRAINT fk_catechumens_steps
FOREIGN KEY (step_id) REFERENCES steps(id);