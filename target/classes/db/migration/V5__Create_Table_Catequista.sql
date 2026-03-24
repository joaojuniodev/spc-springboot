CREATE TABLE `catequista` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `name_community_or_parish` enum('SAO_SEBASTIAO', 'DIVINO_ESPIRITO_SANTO') NOT NULL DEFAULT 'SAO_SEBASTIAO',
  `etapa_id` BIGINT,
  PRIMARY KEY (`id`),
  CONSTRAINT fk_etapa_id
  FOREIGN KEY (`etapa_id`)
  REFERENCES `etapa`(`id`)
);