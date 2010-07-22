delimiter $$

CREATE TABLE `microurl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(10240) NOT NULL,
  `created` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8$$

delimiter $$

CREATE TABLE `microurl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(10240) NOT NULL,
  `created` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8$$

