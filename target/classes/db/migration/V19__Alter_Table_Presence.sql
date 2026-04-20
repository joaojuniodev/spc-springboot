-- 1. renomeia tabela
ALTER TABLE presenca RENAME TO presences;

-- 2. REMOVE FK's
ALTER TABLE presences DROP FOREIGN KEY FK3ws0xs5c61lhclrn4p3bap7g0;
ALTER TABLE presences DROP FOREIGN KEY FKq73qctmgo2ayguey8p9d5ecst;
ALTER TABLE presences DROP FOREIGN KEY fk_catequista_id;

-- 3. REMOVE UNIQUE ANTIGA
ALTER TABLE presences DROP INDEX UK6o6rkje37ru0h2ih7xq1ndgia;

-- 4. RENOMEIA COLUNAS
ALTER TABLE presences RENAME COLUMN catequizando_id TO catechumen_id;
ALTER TABLE presences RENAME COLUMN missa_id TO mass_id;
ALTER TABLE presences RENAME COLUMN catequista_id TO catechist_id;

-- 5. CRIA INDEX's NOVOS
ALTER TABLE presences ADD INDEX idx_presences_mass_id (mass_id);
ALTER TABLE presences ADD INDEX idx_presences_catechumen_id (catechumen_id);
ALTER TABLE presences ADD INDEX idx_presences_catechist_id (catechist_id);

-- 6. ADICIONA FK's NOVAS
ALTER TABLE presences
ADD CONSTRAINT fk_presences_mass
FOREIGN KEY (mass_id) REFERENCES masses(id);

ALTER TABLE presences
ADD CONSTRAINT fk_presences_catechumens
FOREIGN KEY (catechumen_id) REFERENCES catechumens(id);

ALTER TABLE presences
ADD CONSTRAINT fk_presences_catechists
FOREIGN KEY (catechist_id) REFERENCES catechists(id);

-- 7. CRIA UNIQUE CORRETA
ALTER TABLE presences
ADD CONSTRAINT uk_presences_catechumen_mass_catechist
UNIQUE (catechumen_id, mass_id, catechist_id);