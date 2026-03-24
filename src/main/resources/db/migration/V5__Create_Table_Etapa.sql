CREATE TABLE `etapa` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `step` enum('CRISMA','EUCARISTIA_DOIS','EUCARISTIA_UM','PRE_CRISMA','PRIMEIRA_ETAPA','SEGUNDA_ETAPA') DEFAULT NULL,
  `catequista_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKkx6vlxwwlqe62aeogg3eue7tu` (`catequista_id`),
  CONSTRAINT `FKo7xj4kt7sfwmtvh3s8m6gomt6` FOREIGN KEY (`catequista_id`) REFERENCES `catequista` (`id`)
);