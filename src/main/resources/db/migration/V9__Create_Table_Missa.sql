CREATE TABLE `missa` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `date_time` datetime(6) NOT NULL,
  `title` varchar(255) NOT NULL,
  `registered_attendance` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
);