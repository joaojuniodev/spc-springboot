-- 1. Adiciona coluna user_id
ALTER TABLE presences ADD COLUMN user_id BIGINT NULL;

-- 2. Preenche user_id baseado no catechist vinculado
UPDATE presences p
JOIN catechists c ON c.id = p.catechist_id
SET p.user_id = c.user_id;

-- 3. Torna obrigatório
ALTER TABLE presences MODIFY COLUMN user_id BIGINT NOT NULL;

-- 4. Remove UNIQUE antiga (que incluía catechist_id)
ALTER TABLE presences DROP INDEX uk_presences_catechumen_mass_catechist;

-- 5. Remove INDEX e FK do catechist_id
ALTER TABLE presences DROP INDEX idx_presences_catechist_id;
ALTER TABLE presences DROP FOREIGN KEY fk_presences_catechists;

-- 6. Remove a coluna catechist_id
ALTER TABLE presences DROP COLUMN catechist_id;

-- 7. Adiciona INDEX e FK para user_id
ALTER TABLE presences ADD INDEX idx_presences_user_id (user_id);
ALTER TABLE presences ADD CONSTRAINT fk_presences_user
    FOREIGN KEY (user_id) REFERENCES users(id);

-- 8. Recria UNIQUE com user_id no lugar de catechist_id
ALTER TABLE presences
    ADD CONSTRAINT uk_presences_catechumen_mass_user
    UNIQUE (catechumen_id, mass_id, user_id);