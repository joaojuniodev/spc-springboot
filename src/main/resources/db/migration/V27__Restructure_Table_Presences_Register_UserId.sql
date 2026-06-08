-- ============================================================
-- V27__Restructure_Table_Presences_Register_UserId.sql
-- ============================================================

-- ============================================================
-- PASSO 1: Inserir usuários apenas se ainda não existirem
-- ============================================================
INSERT INTO `users` (`username`, `password`, `full_name`, `account_non_expired`, `account_non_locked`, `credentials_non_expired`, `enabled`)
SELECT * FROM (SELECT 'luciana.morelato' AS username, '{pbkdf2}fd8bf96b9a48d9bdb54499365a99f483ce5bce095eaecbf6237862e16e9e8bea313a1fc382eec529' AS password, 'Luciana Ap. Morelato Coutinho' AS full_name, 1, 1, 1, 1) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM `users` WHERE username = 'luciana.morelato');

INSERT INTO `users` (`username`, `password`, `full_name`, `account_non_expired`, `account_non_locked`, `credentials_non_expired`, `enabled`)
SELECT * FROM (SELECT 'luciana.cordeiro', '{pbkdf2}3cb3747eafda6da2d9c880d2c50561aeaeb33b138f34c9d08977c4328822dc3b56278ec8550cc19d', 'Luciana Ap. Cordeiro Chiquineli', 1, 1, 1, 1) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM `users` WHERE username = 'luciana.cordeiro');

INSERT INTO `users` (`username`, `password`, `full_name`, `account_non_expired`, `account_non_locked`, `credentials_non_expired`, `enabled`)
SELECT * FROM (SELECT 'lilian.tucci', '{pbkdf2}f3646a8d110ac791b54fd81ae4151ca1eff2e321336bfe932107bdcb9af3dcd05c3859dec042df4e', 'Lilian Cristina Tucci Salvione', 1, 1, 1, 1) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM `users` WHERE username = 'lilian.tucci');

INSERT INTO `users` (`username`, `password`, `full_name`, `account_non_expired`, `account_non_locked`, `credentials_non_expired`, `enabled`)
SELECT * FROM (SELECT 'lionete.azarias', '{pbkdf2}50c62242b8ba1c6936a7af90a92b7c4d4f396a9996fd0ee3495f1dc069480cf5b3cea39db0bb6a44', 'Lionete Juliana Azarias', 1, 1, 1, 1) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM `users` WHERE username = 'lionete.azarias');

INSERT INTO `users` (`username`, `password`, `full_name`, `account_non_expired`, `account_non_locked`, `credentials_non_expired`, `enabled`)
SELECT * FROM (SELECT 'irene.aquino', '{pbkdf2}a0b63a866f1de679feada336ed90dfce8cdaba816b428254837127d3064155eb1a852c60ba5c3596', 'Irene Thomaz de Aquino', 1, 1, 1, 1) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM `users` WHERE username = 'irene.aquino');

INSERT INTO `users` (`username`, `password`, `full_name`, `account_non_expired`, `account_non_locked`, `credentials_non_expired`, `enabled`)
SELECT * FROM (SELECT 'tabita.rodrigues', '{pbkdf2}56dbe74deea57b356ecec80b797a141a8a8cb2a684162ed76da8c055c9df58d244b9f2832d7c7d67', 'Tabita Neto da Silva Rodrigues', 1, 1, 1, 1) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM `users` WHERE username = 'tabita.rodrigues');

INSERT INTO `users` (`username`, `password`, `full_name`, `account_non_expired`, `account_non_locked`, `credentials_non_expired`, `enabled`)
SELECT * FROM (SELECT 'fernanda.pereira', '{pbkdf2}1b5b5dc927c1eb688e0601c2eb83c3248e418a5bbdde511cdf7b6f9cbbbfccf7fd2e604e15d6da88', 'Fernanda Cristina de Moura Pereira', 1, 1, 1, 1) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM `users` WHERE username = 'fernanda.pereira');

INSERT INTO `users` (`username`, `password`, `full_name`, `account_non_expired`, `account_non_locked`, `credentials_non_expired`, `enabled`)
SELECT * FROM (SELECT 'maria.oliveira', '{pbkdf2}f6d83a35e81688cdca8775f268f814674e50fe723d8f6f9b38960573ba010efe1da5200d379f2e71', 'Maria Edivânia Lima de Oliveira', 1, 1, 1, 1) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM `users` WHERE username = 'maria.oliveira');

INSERT INTO `users` (`username`, `password`, `full_name`, `account_non_expired`, `account_non_locked`, `credentials_non_expired`, `enabled`)
SELECT * FROM (SELECT 'anamaria.beraldo', '{pbkdf2}7f0b4d6b69861faa9a54c354ec1ca5cacb8706b4de72c184ab5a519151ee7f959141610825ba267c', 'Ana Maria F. de Souza Beraldo', 1, 1, 1, 1) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM `users` WHERE username = 'anamaria.beraldo');

INSERT INTO `users` (`username`, `password`, `full_name`, `account_non_expired`, `account_non_locked`, `credentials_non_expired`, `enabled`)
SELECT * FROM (SELECT 'joao.castro', '{pbkdf2}fd8bf96b9a48d9bdb54499365a99f483ce5bce095eaecbf6237862e16e9e8bea313a1fc382eec529', 'João Junio Trindade Castro', 1, 1, 1, 1) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM `users` WHERE username = 'joao.castro');

INSERT INTO `users` (`username`, `password`, `full_name`, `account_non_expired`, `account_non_locked`, `credentials_non_expired`, `enabled`)
SELECT * FROM (SELECT 'mariaines.santos', '{pbkdf2}c59b5bfec585699173808d5626c2676b5ef20d4b2f7b5ef453fc8f6ee159df301e0f73ae95137d02', 'Maria Inês Fuzzari dos Santos', 1, 1, 1, 1) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM `users` WHERE username = 'mariaines.santos');

INSERT INTO `users` (`username`, `password`, `full_name`, `account_non_expired`, `account_non_locked`, `credentials_non_expired`, `enabled`)
SELECT * FROM (SELECT 'ligia.souza', '{pbkdf2}46e61ba7d04c5f22907ff48e93ef8b7fd7d409d3f56b3ad7cbfd1821ce797787c26696b2465c3fb9', 'Lígia de Souza', 1, 1, 1, 1) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM `users` WHERE username = 'ligia.souza');

INSERT INTO `users` (`username`, `password`, `full_name`, `account_non_expired`, `account_non_locked`, `credentials_non_expired`, `enabled`)
SELECT * FROM (SELECT 'stella.souza', '{pbkdf2}eb760e098283840151563c9b480fea44d8e02756eac6041a67f0b1efd03f318051b23c0be44c14b0', 'Stella de Souza', 1, 1, 1, 1) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM `users` WHERE username = 'stella.souza');

INSERT INTO `users` (`username`, `password`, `full_name`, `account_non_expired`, `account_non_locked`, `credentials_non_expired`, `enabled`)
SELECT * FROM (SELECT 'daniele.souza', '{pbkdf2}5d714dd3a8342f0384e46ce617ec756722c9336d27e5a59f4b9498da862535cb4d317740880d335b', 'Daniele B.N. de Souza', 1, 1, 1, 1) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM `users` WHERE username = 'daniele.souza');

INSERT INTO `users` (`username`, `password`, `full_name`, `account_non_expired`, `account_non_locked`, `credentials_non_expired`, `enabled`)
SELECT * FROM (SELECT 'eloisy', '{pbkdf2}68c493829cd0996b464a3499b8bfe2a6462a06a5a52a981a99f16bfb33fe434633a12051d1121c23', 'Eloisy', 1, 1, 1, 1) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM `users` WHERE username = 'eloisy');

-- ============================================================
-- PASSO 2: Atribuir ROLE_CATECHIST apenas se ainda não tiver
-- ============================================================
INSERT INTO `user_role` (`user_id`, `role_id`)
SELECT u.id, r.id FROM `users` u JOIN `roles` r ON r.name = 'ROLE_CATECHIST'
WHERE u.username = 'luciana.morelato'
AND NOT EXISTS (SELECT 1 FROM `user_role` WHERE user_id = u.id AND role_id = r.id);

INSERT INTO `user_role` (`user_id`, `role_id`)
SELECT u.id, r.id FROM `users` u JOIN `roles` r ON r.name = 'ROLE_CATECHIST'
WHERE u.username = 'luciana.cordeiro'
AND NOT EXISTS (SELECT 1 FROM `user_role` WHERE user_id = u.id AND role_id = r.id);

INSERT INTO `user_role` (`user_id`, `role_id`)
SELECT u.id, r.id FROM `users` u JOIN `roles` r ON r.name = 'ROLE_CATECHIST'
WHERE u.username = 'lilian.tucci'
AND NOT EXISTS (SELECT 1 FROM `user_role` WHERE user_id = u.id AND role_id = r.id);

INSERT INTO `user_role` (`user_id`, `role_id`)
SELECT u.id, r.id FROM `users` u JOIN `roles` r ON r.name = 'ROLE_CATECHIST'
WHERE u.username = 'lionete.azarias'
AND NOT EXISTS (SELECT 1 FROM `user_role` WHERE user_id = u.id AND role_id = r.id);

INSERT INTO `user_role` (`user_id`, `role_id`)
SELECT u.id, r.id FROM `users` u JOIN `roles` r ON r.name = 'ROLE_CATECHIST'
WHERE u.username = 'irene.aquino'
AND NOT EXISTS (SELECT 1 FROM `user_role` WHERE user_id = u.id AND role_id = r.id);

INSERT INTO `user_role` (`user_id`, `role_id`)
SELECT u.id, r.id FROM `users` u JOIN `roles` r ON r.name = 'ROLE_CATECHIST'
WHERE u.username = 'tabita.rodrigues'
AND NOT EXISTS (SELECT 1 FROM `user_role` WHERE user_id = u.id AND role_id = r.id);

INSERT INTO `user_role` (`user_id`, `role_id`)
SELECT u.id, r.id FROM `users` u JOIN `roles` r ON r.name = 'ROLE_CATECHIST'
WHERE u.username = 'fernanda.pereira'
AND NOT EXISTS (SELECT 1 FROM `user_role` WHERE user_id = u.id AND role_id = r.id);

INSERT INTO `user_role` (`user_id`, `role_id`)
SELECT u.id, r.id FROM `users` u JOIN `roles` r ON r.name = 'ROLE_CATECHIST'
WHERE u.username = 'maria.oliveira'
AND NOT EXISTS (SELECT 1 FROM `user_role` WHERE user_id = u.id AND role_id = r.id);

INSERT INTO `user_role` (`user_id`, `role_id`)
SELECT u.id, r.id FROM `users` u JOIN `roles` r ON r.name = 'ROLE_CATECHIST'
WHERE u.username = 'anamaria.beraldo'
AND NOT EXISTS (SELECT 1 FROM `user_role` WHERE user_id = u.id AND role_id = r.id);

INSERT INTO `user_role` (`user_id`, `role_id`)
SELECT u.id, r.id FROM `users` u JOIN `roles` r ON r.name = 'ROLE_CATECHIST'
WHERE u.username = 'joao.castro'
AND NOT EXISTS (SELECT 1 FROM `user_role` WHERE user_id = u.id AND role_id = r.id);

INSERT INTO `user_role` (`user_id`, `role_id`)
SELECT u.id, r.id FROM `users` u JOIN `roles` r ON r.name = 'ROLE_CATECHIST'
WHERE u.username = 'mariaines.santos'
AND NOT EXISTS (SELECT 1 FROM `user_role` WHERE user_id = u.id AND role_id = r.id);

INSERT INTO `user_role` (`user_id`, `role_id`)
SELECT u.id, r.id FROM `users` u JOIN `roles` r ON r.name = 'ROLE_CATECHIST'
WHERE u.username = 'ligia.souza'
AND NOT EXISTS (SELECT 1 FROM `user_role` WHERE user_id = u.id AND role_id = r.id);

INSERT INTO `user_role` (`user_id`, `role_id`)
SELECT u.id, r.id FROM `users` u JOIN `roles` r ON r.name = 'ROLE_CATECHIST'
WHERE u.username = 'stella.souza'
AND NOT EXISTS (SELECT 1 FROM `user_role` WHERE user_id = u.id AND role_id = r.id);

INSERT INTO `user_role` (`user_id`, `role_id`)
SELECT u.id, r.id FROM `users` u JOIN `roles` r ON r.name = 'ROLE_CATECHIST'
WHERE u.username = 'daniele.souza'
AND NOT EXISTS (SELECT 1 FROM `user_role` WHERE user_id = u.id AND role_id = r.id);

INSERT INTO `user_role` (`user_id`, `role_id`)
SELECT u.id, r.id FROM `users` u JOIN `roles` r ON r.name = 'ROLE_CATECHIST'
WHERE u.username = 'eloisy'
AND NOT EXISTS (SELECT 1 FROM `user_role` WHERE user_id = u.id AND role_id = r.id);

-- ============================================================
-- PASSO 3: Vincular user_id em cada catequista (apenas se NULL)
-- ============================================================
UPDATE `catechists` SET `user_id` = (SELECT id FROM `users` WHERE username = 'luciana.morelato') WHERE id = 1  AND user_id IS NULL;
UPDATE `catechists` SET `user_id` = (SELECT id FROM `users` WHERE username = 'luciana.cordeiro') WHERE id = 2  AND user_id IS NULL;
UPDATE `catechists` SET `user_id` = (SELECT id FROM `users` WHERE username = 'lilian.tucci')     WHERE id = 3  AND user_id IS NULL;
UPDATE `catechists` SET `user_id` = (SELECT id FROM `users` WHERE username = 'lionete.azarias')  WHERE id = 4  AND user_id IS NULL;
UPDATE `catechists` SET `user_id` = (SELECT id FROM `users` WHERE username = 'irene.aquino')     WHERE id = 5  AND user_id IS NULL;
UPDATE `catechists` SET `user_id` = (SELECT id FROM `users` WHERE username = 'tabita.rodrigues') WHERE id = 6  AND user_id IS NULL;
UPDATE `catechists` SET `user_id` = (SELECT id FROM `users` WHERE username = 'fernanda.pereira') WHERE id = 7  AND user_id IS NULL;
UPDATE `catechists` SET `user_id` = (SELECT id FROM `users` WHERE username = 'maria.oliveira')   WHERE id = 8  AND user_id IS NULL;
UPDATE `catechists` SET `user_id` = (SELECT id FROM `users` WHERE username = 'anamaria.beraldo') WHERE id = 9  AND user_id IS NULL;
UPDATE `catechists` SET `user_id` = (SELECT id FROM `users` WHERE username = 'joao.castro')      WHERE id = 10 AND user_id IS NULL;
UPDATE `catechists` SET `user_id` = (SELECT id FROM `users` WHERE username = 'mariaines.santos') WHERE id = 11 AND user_id IS NULL;
UPDATE `catechists` SET `user_id` = (SELECT id FROM `users` WHERE username = 'ligia.souza')      WHERE id = 12 AND user_id IS NULL;
UPDATE `catechists` SET `user_id` = (SELECT id FROM `users` WHERE username = 'stella.souza')     WHERE id = 13 AND user_id IS NULL;
UPDATE `catechists` SET `user_id` = (SELECT id FROM `users` WHERE username = 'daniele.souza')    WHERE id = 14 AND user_id IS NULL;
UPDATE `catechists` SET `user_id` = (SELECT id FROM `users` WHERE username = 'eloisy')           WHERE id = 15 AND user_id IS NULL;

-- ============================================================
-- PASSO 4: Popular user_id em presences via catequista (apenas onde ainda NULL)
-- ============================================================
UPDATE presences p
JOIN catechists c ON c.id = p.catechist_id
SET p.user_id = c.user_id
WHERE p.user_id IS NULL;

-- ============================================================
-- PASSO 5: Tornar user_id obrigatório
-- ============================================================
ALTER TABLE presences MODIFY COLUMN user_id BIGINT NOT NULL;

-- ============================================================
-- PASSO 6: Remover UNIQUE antiga apenas se existir
-- ============================================================
SET @exists_unique = (
  SELECT COUNT(*) FROM information_schema.TABLE_CONSTRAINTS
  WHERE TABLE_SCHEMA = DATABASE()
  AND TABLE_NAME = 'presences'
  AND CONSTRAINT_NAME = 'uk_presences_catechumen_mass_catechist'
);
SET @sql_drop_unique = IF(@exists_unique > 0,
  'ALTER TABLE presences DROP INDEX uk_presences_catechumen_mass_catechist',
  'SELECT 1'
);
PREPARE stmt FROM @sql_drop_unique;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;

-- ============================================================
-- PASSO 7: Remover INDEX do catechist_id apenas se existir
-- ============================================================
SET @exists_idx = (
  SELECT COUNT(*) FROM information_schema.STATISTICS
  WHERE TABLE_SCHEMA = DATABASE()
  AND TABLE_NAME = 'presences'
  AND INDEX_NAME = 'idx_presences_catechist_id'
);
SET @sql_drop_idx = IF(@exists_idx > 0,
  'ALTER TABLE presences DROP INDEX idx_presences_catechist_id',
  'SELECT 1'
);
PREPARE stmt FROM @sql_drop_idx;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;

-- ============================================================
-- PASSO 8: Remover FK do catechist_id apenas se existir
-- ============================================================
SET @exists_fk = (
  SELECT COUNT(*) FROM information_schema.TABLE_CONSTRAINTS
  WHERE TABLE_SCHEMA = DATABASE()
  AND TABLE_NAME = 'presences'
  AND CONSTRAINT_NAME = 'fk_presences_catechists'
  AND CONSTRAINT_TYPE = 'FOREIGN KEY'
);
SET @sql_drop_fk = IF(@exists_fk > 0,
  'ALTER TABLE presences DROP FOREIGN KEY fk_presences_catechists',
  'SELECT 1'
);
PREPARE stmt FROM @sql_drop_fk;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;

-- ============================================================
-- PASSO 9: Remover coluna catechist_id apenas se existir
-- ============================================================
SET @exists_col = (
  SELECT COUNT(*) FROM information_schema.COLUMNS
  WHERE TABLE_SCHEMA = DATABASE()
  AND TABLE_NAME = 'presences'
  AND COLUMN_NAME = 'catechist_id'
);
SET @sql_drop_col = IF(@exists_col > 0,
  'ALTER TABLE presences DROP COLUMN catechist_id',
  'SELECT 1'
);
PREPARE stmt FROM @sql_drop_col;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;

-- ============================================================
-- PASSO 10: Adicionar INDEX para user_id apenas se não existir
-- ============================================================
SET @exists_idx_user = (
  SELECT COUNT(*) FROM information_schema.STATISTICS
  WHERE TABLE_SCHEMA = DATABASE()
  AND TABLE_NAME = 'presences'
  AND INDEX_NAME = 'idx_presences_user_id'
);
SET @sql_add_idx = IF(@exists_idx_user = 0,
  'ALTER TABLE presences ADD INDEX idx_presences_user_id (user_id)',
  'SELECT 1'
);
PREPARE stmt FROM @sql_add_idx;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;

-- ============================================================
-- PASSO 11: Adicionar FK para user_id apenas se não existir
-- ============================================================
SET @exists_fk_user = (
  SELECT COUNT(*) FROM information_schema.TABLE_CONSTRAINTS
  WHERE TABLE_SCHEMA = DATABASE()
  AND TABLE_NAME = 'presences'
  AND CONSTRAINT_NAME = 'fk_presences_user'
  AND CONSTRAINT_TYPE = 'FOREIGN KEY'
);
SET @sql_add_fk = IF(@exists_fk_user = 0,
  'ALTER TABLE presences ADD CONSTRAINT fk_presences_user FOREIGN KEY (user_id) REFERENCES users(id)',
  'SELECT 1'
);
PREPARE stmt FROM @sql_add_fk;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;

-- ============================================================
-- PASSO 12: Recriar UNIQUE com user_id apenas se não existir
-- ============================================================
SET @exists_unique_user = (
  SELECT COUNT(*) FROM information_schema.TABLE_CONSTRAINTS
  WHERE TABLE_SCHEMA = DATABASE()
  AND TABLE_NAME = 'presences'
  AND CONSTRAINT_NAME = 'uk_presences_catechumen_mass_user'
);
SET @sql_add_unique = IF(@exists_unique_user = 0,
  'ALTER TABLE presences ADD CONSTRAINT uk_presences_catechumen_mass_user UNIQUE (catechumen_id, mass_id, user_id)',
  'SELECT 1'
);
PREPARE stmt FROM @sql_add_unique;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;