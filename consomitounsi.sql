-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : sam. 20 fév. 2021 à 15:19
-- Version du serveur :  8.0.21
-- Version de PHP : 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `consomitounsi`
--

-- --------------------------------------------------------

--
-- Structure de la table `advertisement`
--

DROP TABLE IF EXISTS `advertisement`;
CREATE TABLE IF NOT EXISTS `advertisement` (
  `id_ad` int NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `ending_date` date DEFAULT NULL,
  `startingdate` date DEFAULT NULL,
  `adve` int DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `picture_ad` varchar(255) DEFAULT NULL,
  `viewcont` int NOT NULL,
  `event_idevent` int DEFAULT NULL,
  PRIMARY KEY (`id_ad`),
  KEY `FKl9h9gv3vfxfpw9d2kvuk2puj5` (`event_idevent`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `bill`
--

DROP TABLE IF EXISTS `bill`;
CREATE TABLE IF NOT EXISTS `bill` (
  `id_bill` int NOT NULL AUTO_INCREMENT,
  `date_bill` date DEFAULT NULL,
  `datereglement` datetime DEFAULT NULL,
  `num_bill` int NOT NULL,
  `totalfinal` float NOT NULL,
  `tupefac` int DEFAULT NULL,
  `command_idcommand` int DEFAULT NULL,
  PRIMARY KEY (`id_bill`),
  KEY `FKea68ycsb7na39b63konkubtk0` (`command_idcommand`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `cart`
--

DROP TABLE IF EXISTS `cart`;
CREATE TABLE IF NOT EXISTS `cart` (
  `idcart` int NOT NULL AUTO_INCREMENT,
  `currency` varchar(255) DEFAULT NULL,
  `etat` bit(1) NOT NULL,
  `total` double NOT NULL,
  PRIMARY KEY (`idcart`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `cart_products`
--

DROP TABLE IF EXISTS `cart_products`;
CREATE TABLE IF NOT EXISTS `cart_products` (
  `carts_idcart` int NOT NULL,
  `products_idproduct` int NOT NULL,
  KEY `FKojoen2817phrla2qmi533ep53` (`products_idproduct`),
  KEY `FKhqrw68otn5p0w3mih39k69g4u` (`carts_idcart`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `cart_users`
--

DROP TABLE IF EXISTS `cart_users`;
CREATE TABLE IF NOT EXISTS `cart_users` (
  `carts_idcart` int NOT NULL,
  `users_iduser` int NOT NULL,
  KEY `FK4dkc7kw39spevdxp2o254wpit` (`users_iduser`),
  KEY `FKadeuye2ih5rqwxpvqrhqhuqm` (`carts_idcart`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `category`
--

DROP TABLE IF EXISTS `category`;
CREATE TABLE IF NOT EXISTS `category` (
  `idcategory` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idcategory`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `claim`
--

DROP TABLE IF EXISTS `claim`;
CREATE TABLE IF NOT EXISTS `claim` (
  `idclaim` int NOT NULL AUTO_INCREMENT,
  `appointement` date DEFAULT NULL,
  `etat` varchar(255) DEFAULT NULL,
  `dateclaim` date DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `reference` int NOT NULL,
  `bill_id_bill` int DEFAULT NULL,
  `exchange_idexchange` int DEFAULT NULL,
  `refund_idrefund` int DEFAULT NULL,
  `repair_idrepair` int DEFAULT NULL,
  `user_iduser` int DEFAULT NULL,
  PRIMARY KEY (`idclaim`),
  KEY `FKhmcckgououf389ci16p27h9f2` (`bill_id_bill`),
  KEY `FK9dhlv5hwy67yaymxddy5n12ir` (`exchange_idexchange`),
  KEY `FK4v6yooa5fny8kietdt5f5f5ki` (`refund_idrefund`),
  KEY `FKs0kxd5pmcof81xa9oakb22pyx` (`repair_idrepair`),
  KEY `FKte93nhx0yo0qtx1flmhto1slj` (`user_iduser`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `command`
--

DROP TABLE IF EXISTS `command`;
CREATE TABLE IF NOT EXISTS `command` (
  `idcommand` int NOT NULL AUTO_INCREMENT,
  `amount_command` double NOT NULL,
  `date_command` date DEFAULT NULL,
  `date_creation` date DEFAULT NULL,
  `date_send` date DEFAULT NULL,
  `numcommand` int NOT NULL,
  `numsend` int NOT NULL,
  `payement` varchar(255) DEFAULT NULL,
  `tva` int NOT NULL,
  `validpayement` bit(1) DEFAULT NULL,
  `cart_idcart` int DEFAULT NULL,
  `transaction_idtrans` int DEFAULT NULL,
  PRIMARY KEY (`idcommand`),
  KEY `FK4gnfsn7objel03j00q5uc1l92` (`cart_idcart`),
  KEY `FK2ebdvdurhb5n1tcbs197l5kds` (`transaction_idtrans`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `command_product`
--

DROP TABLE IF EXISTS `command_product`;
CREATE TABLE IF NOT EXISTS `command_product` (
  `idcomm` int NOT NULL AUTO_INCREMENT,
  `date_delivery` date DEFAULT NULL,
  `price` float NOT NULL,
  PRIMARY KEY (`idcomm`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `command_product_products`
--

DROP TABLE IF EXISTS `command_product_products`;
CREATE TABLE IF NOT EXISTS `command_product_products` (
  `commandproducts_idcomm` int NOT NULL,
  `products_idproduct` int NOT NULL,
  KEY `FKpoa3pb6uu95a3uh79guovpy14` (`products_idproduct`),
  KEY `FKcmmx4u7mrtokn65iomtwclg24` (`commandproducts_idcomm`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `contrat`
--

DROP TABLE IF EXISTS `contrat`;
CREATE TABLE IF NOT EXISTS `contrat` (
  `idcontrat` int NOT NULL AUTO_INCREMENT,
  `date_debut` datetime DEFAULT NULL,
  `date_fin` datetime DEFAULT NULL,
  `salary` float NOT NULL,
  `typecontrat` int DEFAULT NULL,
  PRIMARY KEY (`idcontrat`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `delivery`
--

DROP TABLE IF EXISTS `delivery`;
CREATE TABLE IF NOT EXISTS `delivery` (
  `iddelivery` int NOT NULL AUTO_INCREMENT,
  `adress` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `reference` int NOT NULL,
  `region` varchar(255) DEFAULT NULL,
  `etat` varchar(255) DEFAULT NULL,
  `weight` float NOT NULL,
  `deliveryman_iddeliveryman` int DEFAULT NULL,
  `deliverynote_numorder` int DEFAULT NULL,
  PRIMARY KEY (`iddelivery`),
  KEY `FKgbvcxf77plgcrq6h76wed3h6u` (`deliveryman_iddeliveryman`),
  KEY `FKpgysejsqls6ebb4g1mc2skvtb` (`deliverynote_numorder`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `delivery_man`
--

DROP TABLE IF EXISTS `delivery_man`;
CREATE TABLE IF NOT EXISTS `delivery_man` (
  `iddeliveryman` int NOT NULL AUTO_INCREMENT,
  `totaldelivery` float NOT NULL,
  `totworkhour` varchar(255) DEFAULT NULL,
  `availability` bit(1) DEFAULT NULL,
  `premium` float NOT NULL,
  PRIMARY KEY (`iddeliveryman`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `delivery_note`
--

DROP TABLE IF EXISTS `delivery_note`;
CREATE TABLE IF NOT EXISTS `delivery_note` (
  `numorder` int NOT NULL AUTO_INCREMENT,
  `total` float NOT NULL,
  `shippingcost` float NOT NULL,
  `command_idcommand` int DEFAULT NULL,
  PRIMARY KEY (`numorder`),
  KEY `FKc2u8gq8mv2art90k13ldjnemt` (`command_idcommand`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `donation`
--

DROP TABLE IF EXISTS `donation`;
CREATE TABLE IF NOT EXISTS `donation` (
  `iddonation` int NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  `persocat` int DEFAULT NULL,
  `quantity` double NOT NULL,
  `event_idevent` int DEFAULT NULL,
  PRIMARY KEY (`iddonation`),
  KEY `FK8pewhtwgvsuyfl0ahpockha27` (`event_idevent`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `event`
--

DROP TABLE IF EXISTS `event`;
CREATE TABLE IF NOT EXISTS `event` (
  `idevent` int NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `ending_date` date DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `startingdate` date DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `pictureevent` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idevent`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `event_users`
--

DROP TABLE IF EXISTS `event_users`;
CREATE TABLE IF NOT EXISTS `event_users` (
  `events_idevent` int NOT NULL,
  `users_iduser` int NOT NULL,
  KEY `FK81q3siae9e3ffi34xemgm6tic` (`users_iduser`),
  KEY `FKsoujyo3hst60t65nhkmtkk8v` (`events_idevent`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `exchange`
--

DROP TABLE IF EXISTS `exchange`;
CREATE TABLE IF NOT EXISTS `exchange` (
  `idexchange` int NOT NULL AUTO_INCREMENT,
  `type` varchar(255) DEFAULT NULL,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`idexchange`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `extra_time`
--

DROP TABLE IF EXISTS `extra_time`;
CREATE TABLE IF NOT EXISTS `extra_time` (
  `idextra` int NOT NULL AUTO_INCREMENT,
  `nbr_hour` int NOT NULL,
  `dateextra` datetime DEFAULT NULL,
  `deliveryman_iddeliveryman` int DEFAULT NULL,
  PRIMARY KEY (`idextra`),
  KEY `FK1pp9pws7vdentw9matwgpa7l2` (`deliveryman_iddeliveryman`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `jackpot`
--

DROP TABLE IF EXISTS `jackpot`;
CREATE TABLE IF NOT EXISTS `jackpot` (
  `idjack` int NOT NULL AUTO_INCREMENT,
  `beneficary` int DEFAULT NULL,
  `duration` date DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `total` double NOT NULL,
  PRIMARY KEY (`idjack`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `jackpot_users`
--

DROP TABLE IF EXISTS `jackpot_users`;
CREATE TABLE IF NOT EXISTS `jackpot_users` (
  `jackpots_idjack` int NOT NULL,
  `users_iduser` int NOT NULL,
  KEY `FKm1cmimihynbkr6431e4t9w0sx` (`users_iduser`),
  KEY `FK186s6cy3k91b6j43bt5tfbi56` (`jackpots_idjack`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `product`
--

DROP TABLE IF EXISTS `product`;
CREATE TABLE IF NOT EXISTS `product` (
  `idproduct` int NOT NULL AUTO_INCREMENT,
  `dateaddproduct` date DEFAULT NULL,
  `datededline` date DEFAULT NULL,
  `barecode` varchar(255) DEFAULT NULL,
  `buyprice` float NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `devise` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `newproduct` bit(1) NOT NULL,
  `productpicture` varchar(255) DEFAULT NULL,
  `sellprice` float NOT NULL,
  `tva` int NOT NULL,
  `weight` int NOT NULL,
  `category_idcategory` int DEFAULT NULL,
  `radius_id_radius` int DEFAULT NULL,
  `user_iduser` int DEFAULT NULL,
  PRIMARY KEY (`idproduct`),
  KEY `FK5y2tva1dbl08vbc1anys3btdl` (`category_idcategory`),
  KEY `FKerm2lbelleek8rgy1oqoy3sbn` (`radius_id_radius`),
  KEY `FKib72qcfafbf1tgnx40v30s1gd` (`user_iduser`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `promotion`
--

DROP TABLE IF EXISTS `promotion`;
CREATE TABLE IF NOT EXISTS `promotion` (
  `idpromotion` int NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `ending_date` date DEFAULT NULL,
  `startingdate` date DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `percentage` bigint NOT NULL,
  `product_idproduct` int DEFAULT NULL,
  PRIMARY KEY (`idpromotion`),
  KEY `FKgwr2hiendc9cbqicox2utb2r3` (`product_idproduct`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `radius`
--

DROP TABLE IF EXISTS `radius`;
CREATE TABLE IF NOT EXISTS `radius` (
  `id_radius` int NOT NULL AUTO_INCREMENT,
  `capacitymax` int NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_radius`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `refund`
--

DROP TABLE IF EXISTS `refund`;
CREATE TABLE IF NOT EXISTS `refund` (
  `idrefund` int NOT NULL AUTO_INCREMENT,
  `amount` float NOT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idrefund`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `repair`
--

DROP TABLE IF EXISTS `repair`;
CREATE TABLE IF NOT EXISTS `repair` (
  `idrepair` int NOT NULL AUTO_INCREMENT,
  `coast` float NOT NULL,
  `date` date DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idrepair`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `request_leave`
--

DROP TABLE IF EXISTS `request_leave`;
CREATE TABLE IF NOT EXISTS `request_leave` (
  `idrequest` int NOT NULL AUTO_INCREMENT,
  `ending_date` date DEFAULT NULL,
  `startingdate` date DEFAULT NULL,
  `deliveryman_iddeliveryman` int DEFAULT NULL,
  PRIMARY KEY (`idrequest`),
  KEY `FK30s3242ob50ehd4km359v94so` (`deliveryman_iddeliveryman`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `role`
--

DROP TABLE IF EXISTS `role`;
CREATE TABLE IF NOT EXISTS `role` (
  `id_role` int NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_role`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `stock`
--

DROP TABLE IF EXISTS `stock`;
CREATE TABLE IF NOT EXISTS `stock` (
  `idstock` int NOT NULL AUTO_INCREMENT,
  `date_creation` date DEFAULT NULL,
  `quantity` float NOT NULL,
  `user_iduser` int DEFAULT NULL,
  PRIMARY KEY (`idstock`),
  KEY `FKptx6v9d7cufqmidcnhqwu9xbk` (`user_iduser`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `stock_products`
--

DROP TABLE IF EXISTS `stock_products`;
CREATE TABLE IF NOT EXISTS `stock_products` (
  `stocks_idstock` int NOT NULL,
  `products_idproduct` int NOT NULL,
  KEY `FKdc9f19exljn0m9dfei0dqgpqf` (`products_idproduct`),
  KEY `FKkfhauen2j03vee9lckf75v6pj` (`stocks_idstock`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
CREATE TABLE IF NOT EXISTS `transaction` (
  `idtrans` int NOT NULL AUTO_INCREMENT,
  `amountcommution` float NOT NULL,
  `datetransaction` date DEFAULT NULL,
  PRIMARY KEY (`idtrans`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `iduser` int NOT NULL AUTO_INCREMENT,
  `adress` varchar(255) DEFAULT NULL,
  `date_creation` date DEFAULT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `login` varchar(255) DEFAULT NULL,
  `tel` int NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `contrat_idcontrat` int DEFAULT NULL,
  `role_id_role` int DEFAULT NULL,
  PRIMARY KEY (`iduser`),
  KEY `FK4t605atjlej95y4ddtm33rseu` (`contrat_idcontrat`),
  KEY `FK8rtnkiy5mue6ixixnp7j9ykr8` (`role_id_role`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
