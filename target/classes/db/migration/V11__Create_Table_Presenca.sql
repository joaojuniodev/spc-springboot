CREATE TABLE `presenca` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `justification` varchar(255) DEFAULT NULL,
  `status` tinyint NOT NULL,
  `catequizando_id` BIGINT NOT NULL,
  `missa_id` BIGINT NOT NULL,
  `catequista_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK6o6rkje37ru0h2ih7xq1ndgia` (`catequizando_id`,`missa_id`, `catequista_id`),
  KEY `FK3ws0xs5c61lhclrn4p3bap7g0` (`missa_id`),
  CONSTRAINT `FK3ws0xs5c61lhclrn4p3bap7g0` FOREIGN KEY (`missa_id`) REFERENCES `missa` (`id`),
  CONSTRAINT `FKq73qctmgo2ayguey8p9d5ecst` FOREIGN KEY (`catequizando_id`) REFERENCES `catequizando` (`id`),
  CONSTRAINT `fk_catequista_id` FOREIGN KEY (`catequista_id`) REFERENCES `catequista` (`id`),
  CONSTRAINT `presenca_chk_1` CHECK ((`status` between 0 and 2))
);