-- Adminer 4.8.1 MySQL 10.4.32-MariaDB dump

SET NAMES utf8;
SET time_zone = '+00:00';
SET foreign_key_checks = 0;
SET sql_mode = 'NO_AUTO_VALUE_ON_ZERO';

SET NAMES utf8mb4;

DROP TABLE IF EXISTS `adresse_postale`;
CREATE TABLE `adresse_postale` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `street` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `postal_code` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `adresse_postale` (`id`, `street`, `city`, `postal_code`) VALUES
(1,	'Rue de Gotham',	'Gotham',	'12345'),
(2,	'Daily Bugle',	'New York',	'54321'),
(3,	'Daily Planet',	'Metropolis',	'98765'),
(4,	'John Street',	'Harlem',	'55656'),
(5,	'Rue de Jack',	'JackCity',	'12345'),
(6,	'Rue du Large',	'Chicago',	'56434'),
(7,	'Rue du Manoir',	'Gotham City',	'12345'),
(8,	'Rue du Daily Planet',	'Metropolis',	'54321');

DROP TABLE IF EXISTS `agence_voyage`;
CREATE TABLE `agence_voyage` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `adresse_id` int(11) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `adresse_id` (`adresse_id`),
  CONSTRAINT `agence_voyage_ibfk_1` FOREIGN KEY (`adresse_id`) REFERENCES `adresse_postale` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


DROP TABLE IF EXISTS `bagage`;
CREATE TABLE `bagage` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `number` varchar(50) DEFAULT NULL,
  `weight` decimal(5,2) DEFAULT NULL,
  `color` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `bagage` (`id`, `number`, `weight`, `color`) VALUES
(1,	'1545',	64.00,	'bleu'),
(2,	'43',	4.70,	'rouge'),
(3,	'43',	4.70,	'rouge'),
(4,	'1',	10.00,	'bleu'),
(5,	'2',	20.00,	'rouge'),
(6,	'43',	4.70,	'rouge');

DROP TABLE IF EXISTS `voyage`;
CREATE TABLE `voyage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` varchar(255) NOT NULL,
  `duree` varchar(10) NOT NULL,
  `lieu_depart` varchar(255) NOT NULL,
  `lieu_arrivee` varchar(255) NOT NULL,
  `prix` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `voyage` (`id`, `date`, `duree`, `lieu_depart`, `lieu_arrivee`, `prix`) VALUES
(1,	'10/06/2024',	'3h30',	'Paris',	'New York',	100.00),
(2,	'30/06/2024',	'5h',	'Monaco',	'Australie',	300.00),
(3,	'04/10/2024',	'3h',	'Paris',	'Brésil',	200.00);

DROP TABLE IF EXISTS `voyageur`;
CREATE TABLE `voyageur` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `category` varchar(50) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `adresse_id` int(11) unsigned DEFAULT NULL,
  `bagage_id` int(10) unsigned DEFAULT NULL,
  `voyage_id` int(11) DEFAULT NULL,
  `agence_id` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `adresse_id` (`adresse_id`),
  KEY `bagage_id` (`bagage_id`),
  KEY `agence_id` (`agence_id`),
  KEY `voyage_id` (`voyage_id`),
  CONSTRAINT `voyageur_ibfk_1` FOREIGN KEY (`adresse_id`) REFERENCES `adresse_postale` (`id`),
  CONSTRAINT `voyageur_ibfk_2` FOREIGN KEY (`bagage_id`) REFERENCES `bagage` (`id`),
  CONSTRAINT `voyageur_ibfk_3` FOREIGN KEY (`agence_id`) REFERENCES `agence_voyage` (`id`),
  CONSTRAINT `voyageur_ibfk_4` FOREIGN KEY (`voyage_id`) REFERENCES `voyage` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `voyageur` (`id`, `name`, `category`, `age`, `adresse_id`, `bagage_id`, `voyage_id`, `agence_id`) VALUES
(1,	'Bruce Wayne',	'Adulte',	34,	1,	NULL,	1,	NULL),
(2,	'Peter Parker',	'Adulte',	23,	2,	NULL,	3,	NULL),
(3,	'Clark Kent',	'Adulte',	32,	3,	NULL,	1,	NULL),
(4,	'John Johnson',	'Adulte',	44,	4,	NULL,	1,	NULL),
(5,	'Jack Jack',	'Enfant',	15,	5,	4,	NULL,	NULL),
(6,	'Johnson',	'Adulte',	54,	6,	NULL,	NULL,	NULL);

-- 2024-02-27 13:08:47
