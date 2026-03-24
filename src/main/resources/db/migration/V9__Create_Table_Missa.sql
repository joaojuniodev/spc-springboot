CREATE TABLE `missa` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `date_time` datetime(6) NOT NULL,
  `title` varchar(255) NOT NULL,
  `registered_attendance` bit(1) DEFAULT NULL,
  `mass_of_liturgical_calendar_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT fk_liturgical_calendar_id
  FOREIGN KEY (`mass_of_liturgical_calendar_id`)
  REFERENCES `liturgical_calendar`(`id`)
);