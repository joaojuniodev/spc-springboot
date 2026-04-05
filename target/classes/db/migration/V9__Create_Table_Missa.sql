CREATE TABLE `missa` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `date_time` datetime(6) NOT NULL,
  `title` varchar(255) NOT NULL,
  `registered_attendance` bit(1) DEFAULT NULL,
  `name_community_or_parish` enum('SAO_SEBASTIAO', 'DIVINO_ESPIRITO_SANTO') NOT NULL DEFAULT 'SAO_SEBASTIAO',
  `location` enum('MATRIZ', 'CAPELA_DO_DIVINO') NOT NULL DEFAULT 'MATRIZ',
  `mass_of_liturgical_calendar_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT fk_liturgical_calendar_id
  FOREIGN KEY (`mass_of_liturgical_calendar_id`)
  REFERENCES `liturgical_calendar`(`id`)
);