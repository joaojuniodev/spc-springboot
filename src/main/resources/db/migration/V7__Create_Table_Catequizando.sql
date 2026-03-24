CREATE TABLE `catequizando` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `birth_date` date DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `etapa_id` bigint NOT NULL,
  `name_community_or_parish` enum('SAO_SEBASTIAO', 'DIVINO_ESPIRITO_SANTO') NOT NULL DEFAULT 'SAO_SEBASTIAO',
  PRIMARY KEY (`id`),
  KEY `FK8hmsttop6jmk8dcy8aly8fls1` (`etapa_id`),
  CONSTRAINT `FK8hmsttop6jmk8dcy8aly8fls1` FOREIGN KEY (`etapa_id`) REFERENCES `etapa` (`id`)
);