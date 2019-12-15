-- --------------------------------------------------------
-- Servidor:                     127.0.0.1
-- Versão do servidor:           10.3.11-MariaDB - mariadb.org binary distribution
-- OS do Servidor:               Win64
-- HeidiSQL Versão:              9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Copiando estrutura do banco de dados para sier
CREATE DATABASE IF NOT EXISTS `sier` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `sier`;

-- Copiando estrutura para tabela sier.aluno
CREATE TABLE IF NOT EXISTS `aluno` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `turma` varchar(255) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `usuario_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKsgpw6tb2kerkceshx1b10rhkg` (`usuario_id`)
) ENGINE=MyISAM AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela sier.aluno: 40 rows
/*!40000 ALTER TABLE `aluno` DISABLE KEYS */;
INSERT INTO `aluno` (`id`, `created_at`, `turma`, `updated_at`, `usuario_id`) VALUES
	(1, NULL, '2018', NULL, 3),
	(2, NULL, '2018', NULL, 4),
	(3, NULL, '2018', NULL, 5),
	(4, NULL, '2018', NULL, 6),
	(5, NULL, '2018', NULL, 7),
	(6, NULL, '2018', NULL, 8),
	(7, NULL, '2018', NULL, 9),
	(8, NULL, '2018', NULL, 10),
	(9, NULL, '2018', NULL, 11),
	(10, NULL, '2018', NULL, 12),
	(11, NULL, '2018', NULL, 13),
	(12, NULL, '2018', NULL, 14),
	(13, NULL, '2018', NULL, 15),
	(14, NULL, '2018', NULL, 16),
	(15, NULL, '2018', NULL, 17),
	(16, NULL, '2018', NULL, 18),
	(17, NULL, '2018', NULL, 19),
	(18, NULL, '2018', NULL, 20),
	(19, NULL, '2018', NULL, 21),
	(20, NULL, '2018', NULL, 22),
	(21, NULL, '2018', NULL, 3),
	(22, NULL, '2018', NULL, 4),
	(23, NULL, '2018', NULL, 5),
	(24, NULL, '2018', NULL, 6),
	(25, NULL, '2018', NULL, 7),
	(26, NULL, '2018', NULL, 8),
	(27, NULL, '2018', NULL, 9),
	(28, NULL, '2018', NULL, 10),
	(29, NULL, '2018', NULL, 11),
	(30, NULL, '2018', NULL, 12),
	(31, NULL, '2018', NULL, 13),
	(32, NULL, '2018', NULL, 14),
	(33, NULL, '2018', NULL, 15),
	(34, NULL, '2018', NULL, 16),
	(35, NULL, '2018', NULL, 17),
	(36, NULL, '2018', NULL, 18),
	(37, NULL, '2018', NULL, 19),
	(38, NULL, '2018', NULL, 20),
	(39, NULL, '2018', NULL, 21),
	(40, NULL, '2018', NULL, 22);
/*!40000 ALTER TABLE `aluno` ENABLE KEYS */;

-- Copiando estrutura para tabela sier.cargo
CREATE TABLE IF NOT EXISTS `cargo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `nome` varchar(255) NOT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela sier.cargo: 3 rows
/*!40000 ALTER TABLE `cargo` DISABLE KEYS */;
INSERT INTO `cargo` (`id`, `created_at`, `nome`, `updated_at`) VALUES
	(1, NULL, 'ADMINISTRADOR', NULL),
	(2, NULL, 'PROFESSOR', NULL),
	(3, NULL, 'ALUNO', NULL);
/*!40000 ALTER TABLE `cargo` ENABLE KEYS */;

-- Copiando estrutura para tabela sier.edificio
CREATE TABLE IF NOT EXISTS `edificio` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by_id` bigint(20) DEFAULT NULL,
  `updated_by_id` bigint(20) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `nome_conhecido` varchar(255) NOT NULL,
  `data_construcao` date DEFAULT NULL,
  `numero_andares` int(11) DEFAULT NULL,
  `fonte_coleta` varchar(255) DEFAULT NULL,
  `informacoes` varchar(255) DEFAULT NULL,
  `cep` varchar(255) DEFAULT NULL,
  `numero` varchar(255) DEFAULT NULL,
  `rua` varchar(255) DEFAULT NULL,
  `bairro` varchar(255) DEFAULT NULL,
  `cidade` varchar(255) DEFAULT NULL,
  `estado` varchar(255) DEFAULT NULL,
  `updated_at` date DEFAULT NULL,
  `created_at` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1jodw5igc0vd44d3apba4w4dw` (`created_by_id`),
  KEY `FK9no4jykkvjr8vbu6tjmef14vi` (`updated_by_id`)
) ENGINE=MyISAM AUTO_INCREMENT=62 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela sier.edificio: 43 rows
/*!40000 ALTER TABLE `edificio` DISABLE KEYS */;
INSERT INTO `edificio` (`id`, `created_by_id`, `updated_by_id`, `nome`, `nome_conhecido`, `data_construcao`, `numero_andares`, `fonte_coleta`, `informacoes`, `cep`, `numero`, `rua`, `bairro`, `cidade`, `estado`, `updated_at`, `created_at`) VALUES
	(1, 19, 1, 'Residencial San Pablo III A ', 'San Pablo', NULL, 3, 'INTERNET', NULL, '86055630', '1260', 'Rua Ernâni Lacerda de Athayde', 'Gleba Fazenda Palhano', 'Londrina', 'PR', '2019-12-04', '2018-10-14'),
	(2, 6, NULL, 'Vivere Palhano', 'Vivere', NULL, 18, 'INTERNET', NULL, '86055630', '1200', 'Rua Ernâni Lacerda de Athayde', 'Gleba Fazenda Palhano', 'Londrina', 'PR', NULL, '2018-10-14'),
	(3, 21, NULL, 'Condomínio Água Verde', 'Condomínio Água Verde', NULL, 3, 'INTERNET', NULL, '86050620', '110', 'Rua Flor-da-manhã', 'Colina Verde', 'Londrina', 'PR', NULL, '2018-10-14'),
	(4, 9, NULL, 'MAISON GIVERNY', 'GIVERNY', NULL, 22, 'INTERNET', '4 apartamentos por andar c/ opções de 2 e 3 vagas de garagem Apto. c/ 2 vagas área total: 197 m²área útil 110m²Apto. c/ 3 vagas área total: 211 m²área útil 110m²', '86050464', '458', 'Rua Eurico Hummig', 'Gleba Fazenda Palhano', 'Londrina', 'PR', NULL, '2018-10-14'),
	(5, 1, NULL, 'Condomínio Talent Residence', 'Talent Residence', NULL, 19, 'INTERNET', 'Construtora A. Yoshii Engenharia 3 dormitórios (1 suíte) 2 vagas de garagem 80m²de área privativa 142m²de área total', '86050464', '404', 'Rua Eurico Hummig', 'Gleba Fazenda Palhano', 'Londrina', 'PR', NULL, '2018-10-14'),
	(6, 9, NULL, 'MAISON LEGACY', 'LEGACY', NULL, 25, 'INTERNET', '4 apartamentos por andar Aptos c/ 2 vagas de garagem área total: 285,71 m²área útil 183 m²Aptos c/ 3 vagas de garagem área total: 299,60 m²área útil 183 m²', '86050464', '405', 'Rua Eurico Hummig', 'Gleba Fazenda Palhano', 'Londrina', 'PR', NULL, '2018-10-14'),
	(7, 4, NULL, 'MAISON LAZULI', 'LAZULI', NULL, 26, 'INTERNET', '1 (uma) torre Construtora A.YOSHII ENGENHARIA E CONSTRUÇÃO LTDA 4 aptos. por andar Aptos c/ 2 vagas de garagem área total: 264 m²área útil 173 m²Aptos c/ 3 vagas de garagem área total: 278 m²área útil 173 m²', '86050464', '350', 'Rua Eurico Hummig', 'Gleba Fazenda Palhano', 'Londrina', 'PR', NULL, '2018-10-14'),
	(8, 13, NULL, 'LANDMARK RESIDENCE', 'LANDMARK', NULL, 25, 'INTERNET', '04 aptos. por andar Aptos c/ 3 vagas de garagem área total: 342,56 m²área útil 214,76 m²Aptos c/ 4 vagas de garagem área total: 357,86 m²área útil 214,76 m²', '86050464', '355', 'Rua Eurico Hummig', 'Gleba Fazenda Palhano', 'Londrina', 'PR', NULL, '2018-10-14'),
	(9, 18, NULL, 'Jardins Eco Resort ', 'Eco Resort', NULL, 19, 'INTERNET', 'Google', '86050520', '300', 'Rua Jerusalém', 'Gleba Fazenda Palhano', 'Londrina', 'PR', NULL, '2018-10-14'),
	(10, 18, NULL, 'Edifício Florais Eco Resort', 'Florais', NULL, 19, 'INTERNET', 'Site Plaenge', '86050464', '255', 'Rua Eurico Hummig', 'Gleba Fazenda Palhano', 'Londrina', 'PR', NULL, '2018-10-14'),
	(11, 18, 1, 'Edifício Alameda Eco Resort', 'Edifício Alameda Eco Resort', NULL, 25, 'INTERNET', 'Site Plaenge', '86050490', '855', 'Rua João Huss', 'Gleba Fazenda Palhano', 'Londrina', 'PR', '2019-12-04', '2018-10-14'),
	(12, 18, NULL, 'Edifício Parc Rocher', 'Parc Rocher', NULL, 22, 'INTERNET', NULL, '86050492', '220', 'Alameda PéVermelho', 'Gleba Fazenda Palhano', 'Londrina', 'PR', NULL, '2018-10-14'),
	(13, 19, NULL, 'Authentique Residencial', 'Authentique', NULL, 24, 'INTERNET', NULL, '86050464', '577', 'Rua Eurico Hummig', 'Gleba Fazenda Palhano', 'Londrina', 'PR', NULL, '2018-10-14'),
	(14, 17, NULL, 'Condomínio Liberty Towers', 'Liberty Torres', NULL, 25, 'INTERNET', 'O prédio contém 25 andares, onde 22 são dedicados aos apartamentos. São 8 apartamentos por andar.', '86050480', '1200', 'Rua Ivan Sérgio Athayde Vicente', 'Gleba Fazenda Palhano', 'Londrina', 'PR', NULL, '2018-10-14'),
	(15, 16, NULL, 'L Harmonie Condominio', 'L Harmonie', NULL, 24, 'INTERNET', NULL, '86050492', '766', 'Alameda PéVermelho', 'Gleba Fazenda Palhano', 'Londrina', 'PR', NULL, '2018-10-14'),
	(16, 17, NULL, 'Edifício Vivere Palhano', 'Vivere ', NULL, 19, 'INTERNET', 'Edifício tem duas torres. Torre A e Torre B.', '86055630', '1200', 'Rua Ernâni Lacerda de Athayde', 'Gleba Fazenda Palhano', 'Londrina', 'PR', NULL, '2018-10-14'),
	(17, 17, NULL, 'Edifício Le Blanc', 'Le Blanc', NULL, 24, 'INTERNET', '*Endereço aparece diferente no momento em que o CEP écadastrado. Endereço real: R. Ernâni Lacerda de Athayde, 930 - Gleba Fazenda Palhano, Londrina - PR.', '86044766', '930', 'Rodovia JoséGarcia de Campos', 'Conjunto Habitacional Jamile Dequech', 'Londrina', 'PR', NULL, '2018-10-14'),
	(18, 17, NULL, 'Condomínio Edifício Mogno', 'Mogno', NULL, 21, 'INTERNET', NULL, '86055630', '45', 'Rua Ernâni Lacerda de Athayde', 'Gleba Fazenda Palhano', 'Londrina', 'PR', NULL, '2018-10-14'),
	(19, 5, NULL, 'Edifício Arquiteto Júlio Ribeiro', 'Arquiteto Júlio Ribeiro', NULL, 22, 'PORTARIA', 'Construtora: Plaenge', '86055620', '150', 'Rua Ulrico Zuínglio', 'Gleba Fazenda Palhano', 'Londrina', 'PR', NULL, '2018-10-14'),
	(20, NULL, NULL, 'Edifício Villa Solare', 'Villa Solare', NULL, 17, 'PORTARIA', 'Construtora: Ageplan Engenharia', '86050070', '1001', 'Rua Caracas', 'Santa Rosa', 'Londrina', 'PR', NULL, '2018-10-14'),
	(21, NULL, NULL, 'Edifício Anita Malfatti', 'Anita Malfatti', NULL, 17, 'PORTARIA', 'Construtora: Plaenge', '86055620', '100', 'Rua Ulrico Zuínglio', 'Gleba Fazenda Palhano', 'Londrina', 'PR', NULL, '2018-10-14'),
	(22, 8, NULL, 'Premiatto Residence', 'Premiatto', NULL, 22, 'PORTARIA', 'Edifício construído pela Vectra Construtora, localiza-se a uma quadra da Av. Ayrton Senna, próximo ao Aurora Shopping e também àAv. Madre Leônia Milito.', '86050464', '300', 'Rua Eurico Hummig', 'Gleba Fazenda Palhano', 'Londrina', 'PR', NULL, '2018-10-14'),
	(23, 8, NULL, 'Max Living', 'Max Living', NULL, 21, 'PORTARIA', 'Construtura: Vectra.', '86050464', '280', 'Rua Eurico Hummig', 'Gleba Fazenda Palhano', 'Londrina', 'PR', NULL, '2018-10-14'),
	(24, 10, NULL, 'Residencial Terra Brasil', 'Terra Brasil', NULL, 18, 'PORTARIA', 'Quadra Construtora', '86050470', '400', 'Rua Maria Lúcia da Paz', 'Gleba Fazenda Palhano', 'Londrina', 'PR', NULL, '2018-10-14'),
	(25, 10, NULL, 'Edifício Vision Residence', 'Vision', NULL, 18, 'PORTARIA', 'Construtora Great Incorporação e Empreen LTDA', '86050470', '450', 'Rua Maria Lúcia da Paz', 'Gleba Fazenda Palhano', 'Londrina', 'PR', NULL, '2018-10-14'),
	(26, 3, NULL, 'NYC Palhano', 'NYC Palhano', NULL, 25, 'INTERNET', NULL, '86050070', '1255', 'Rua Caracas', 'Santa Rosa', 'Londrina', 'PR', NULL, '2018-10-14'),
	(27, 20, NULL, 'Edifício Torre Ville', 'Torre Ville', NULL, 20, 'PORTARIA', 'Edifício em construção. Construtora: Galmo Construtora', '86050470', '350', 'Rua Maria Lúcia da Paz', 'Gleba Fazenda Palhano', 'Londrina', 'PR', NULL, '2018-10-14'),
	(28, 20, NULL, 'Evolution Home Alto da Palhano', 'Evolution', NULL, 21, 'PORTARIA', 'Data de entrega: março 2013. Construtora: Vectra', '86055620', '320', 'Rua Ulrico Zuínglio', 'Gleba Fazenda Palhano', 'Londrina', 'PR', NULL, '2018-10-14'),
	(29, 3, NULL, 'Condomínio Torre Valência', 'Torre Valência', NULL, 24, 'INTERNET', NULL, '86055630', '450', 'Rua Ernâni Lacerda de Athayde', 'Gleba Fazenda Palhano', 'Londrina', 'PR', NULL, '2018-10-14'),
	(30, 3, NULL, 'Condomínio Residencial Costa Esmeralda', 'Costa Esmeralda', NULL, 10, 'INTERNET', NULL, '86055630', '188', 'Rua Ernâni Lacerda de Athayde', 'Gleba Fazenda Palhano', 'Londrina', 'PR', NULL, '2018-10-14'),
	(31, 11, NULL, 'Edifício Garden Palhano', 'Garden Palhano', NULL, 21, 'INTERNET', 'O edifício tem três torres', '86055620', '500', 'Rua Ulrico Zuínglio', 'Gleba Fazenda Palhano', 'Londrina', 'PR', NULL, '2018-10-14'),
	(32, 11, NULL, 'Edifício Torre Almeria', 'Torre Almeria', NULL, 25, 'INTERNET', NULL, '86055630', '400', 'Rua Ernâni Lacerda de Athayde', 'Gleba Fazenda Palhano', 'Londrina', 'PR', NULL, '2018-10-14'),
	(33, 11, NULL, 'Condomínio Residencial Enseadas', 'Residencial Enseadas', NULL, 11, 'INTERNET', 'O condomínio tem duas torres', '86055630', '200', 'Rua Ernâni Lacerda de Athayde', 'Gleba Fazenda Palhano', 'Londrina', 'PR', NULL, '2018-10-14'),
	(34, 15, NULL, 'Edificio Torre Val Verde', 'Edificio Torre Val Verde', NULL, 30, 'INTERNET', '2 Apto. por andar; lote com 3.586,40 m²; área total construída de 19.737,96 m²; as vagas variam de 03 a 04. ', '86050490', '485', 'Rua João Huss', 'Gleba Fazenda Palhano', 'Londrina', 'PR', NULL, '2018-10-14'),
	(35, 15, NULL, 'Edifício Lac Royal', 'Edifício Lac Royal', NULL, 24, 'INTERNET', 'Construtora PLAENGE EMPREENDIMENTOS LTDA; área total de 23,869,97 m²; 02 vagas de garagem. Obs: A data de entrega encontrada foi no ano de 2002. ', '86050490', '200', 'Rua João Huss', 'Gleba Fazenda Palhano', 'Londrina', 'PR', NULL, '2018-10-14'),
	(36, 15, NULL, 'Cencept Palhano/Vanguard Home', 'Concept Palhano', NULL, 1, 'INTERNET', 'Área Priv. entre 58m²e 81m²; 3 Dormitórios e 1 suíte; estáem construção, portanto ainda não édisponibilizado a quantidade de andares; 1 ou 2 vagas; ', '86050490', '1000', 'Rua João Huss', 'Gleba Fazenda Palhano', 'Londrina', 'PR', NULL, '2018-10-14'),
	(37, 15, NULL, 'Vert Residence ', 'Vert Residence ', NULL, 25, 'INTERNET', '04 aptos por andar; 02 vagas de garagem, opção para 03; área total de 205,00m²', '86050490', '881', 'Rua João Huss', 'Gleba Fazenda Palhano', 'Londrina', 'PR', NULL, '2018-10-14'),
	(38, 15, NULL, 'Edifício Alameda Eco Resort & Residence', ' Alameda Eco Resort & Residence', NULL, 24, 'INTERNET', 'A Torre 1 Jasmim foi entregue em Outubro 2017 e a Torre 2 JatobáAbril 2017; área total 30,351,15m²; com 02 quartos, 01 suíte; possui academia, brinquedoteca; churrasqueira; espaço Gourmet, Hall de entrada, pscina coberta e aquecida, playground e etc.', '86050490', '855', 'Rua João Huss', 'Gleba Fazenda Palhano', 'Londrina', 'PR', NULL, '2018-10-14'),
	(39, 15, NULL, 'Edifício Maison Murano ', 'Maison Murano', NULL, 20, 'INTERNET', 'Construtora YOSHI ENGENHARIA E CONSTRUÇÕES LTDA; área total de 12.537,59m²; 04 aptos por andar com 01 vaga dupla de garagem.', '86050490', '455', 'Rua João Huss', 'Gleba Fazenda Palhano', 'Londrina', 'PR', NULL, '2018-10-14'),
	(40, 15, NULL, 'Edifício Joan Miró', 'Joan Miró', NULL, 24, 'INTERNET', 'Construtora: PLAENGE EMPREENDIMENTOS LTDA., 04 aptos; vagas de garagens simples e duplas; área total de 231,47m².', '86050490', '380', 'Rua João Huss', 'Gleba Fazenda Palhano', 'Londrina', 'PR', NULL, '2018-10-14'),
	(41, 15, NULL, 'Residencial Manaca', 'Edifício Manaca', NULL, 21, 'INTERNET', 'Construtora: A. Yoshii; 02 vagas de garagem; 2 piscinas; espaço fitness; varanda com churrasqueira; espaço gourmet; 04 aptos por andar. ', '86050490', '405', 'Rua João Huss', 'Gleba Fazenda Palhano', 'Londrina', 'PR', NULL, '2018-10-14'),
	(42, 14, NULL, NULL, 'Condomínio Residencial Bosque Wilmar H Berbert', NULL, 1, 'INTERNET', NULL, '86050490', '115', 'Rua João Huss', 'Gleba Fazenda Palhano', 'Londrina', 'PR', NULL, '2018-10-14'),
	(43, 14, NULL, NULL, 'Condomínio Residencial Pérola Negra', NULL, 1, 'INTERNET', NULL, '86050490', '177', 'Rua João Huss', 'Gleba Fazenda Palhano', 'Londrina', 'PR', NULL, '2018-10-14');
/*!40000 ALTER TABLE `edificio` ENABLE KEYS */;

-- Copiando estrutura para tabela sier.usuario
CREATE TABLE IF NOT EXISTS `usuario` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ativo` bit(1) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `last_login` datetime DEFAULT NULL,
  `nome` varchar(255) NOT NULL,
  `senha` varchar(255) NOT NULL,
  `updated_at` datetime DEFAULT NULL,
  `apelido` varchar(255) NOT NULL,
  `cargo_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_5171l57faosmj8myawaucatdw` (`email`),
  KEY `FKnnbsgmv6we6ee7x59r9m693c3` (`cargo_id`)
) ENGINE=MyISAM AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela sier.usuario: 22 rows
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` (`id`, `ativo`, `created_at`, `email`, `last_login`, `nome`, `senha`, `updated_at`, `apelido`, `cargo_id`) VALUES
	(1, b'1', '2018-01-01 00:00:00', 'renato.henrique_98@hotmail.com', '2018-08-21 11:43:07', 'Renato Henrique Horacio dos Santos', '$2a$10$fNntSM.zr5PDvKsoVgvENuJDbMJuBsM3lZpYXPd/RO3E/JKqKu0Ai', '2019-06-17 12:20:09', 'Renato', 1),
	(2, b'1', '2018-01-01 00:00:00', 'brigida.administrador@saier.com', '2018-07-26 17:44:33', 'Brigida Cervantes', '$2a$10$bAl.hJ0s.Aj3reNzBvuYY.YvmXPixO.izSYv8cgUFnLPxCkGmafwO', '2019-06-17 12:43:31', 'Brigida', 2),
	(3, b'1', '2018-01-01 00:00:00', 'ana.silva@saier.com', '2018-08-02 07:21:18', 'Ana K Hobold Ferreira da Silva', '$2a$10$AItIN2UQLQMqHTR/K/kab.3vt0X8AzHHo639LuwbPDi0EvhpzzftS', '2019-06-17 12:43:31', 'Ana Silva', 3),
	(4, b'1', '2018-01-01 00:00:00', 'ana.teixeira@saier.com', '2018-07-31 10:29:18', 'Ana Paula dos Santos Teixeira', '$2a$10$YthoycnyU0O3/uJDELFo6.oz/VpCtknZ9O5eom3baYcpawhC4dl9W', '2019-06-17 12:43:31', 'Ana Paula', 3),
	(5, b'1', '2017-12-31 23:59:59', 'bianca.martins@saier.com', '2018-08-01 12:02:00', 'Bianca Rodrigues Martins', '$2a$10$7cL1Yt6lc7HhxlJX/BEZKuZSqmP9vbgYeJa2TAkeBx6mgzmWOXggy', '2019-06-17 12:43:31', 'Bianca Rodrigues', 3),
	(6, b'1', '2018-01-01 00:00:00', 'catia.goedert@saier.com', '2018-07-31 09:33:05', 'Catia Bertuol da Silva Goedert', '$2a$10$w04xpMsP9I15bJJ.W5PPJ.whJILde1VJc4uNyB8gS.HALrrQVw0Ii', '2019-06-17 12:43:31', 'Catia Bertuol', 3),
	(7, b'1', '2018-01-01 00:00:00', 'debora.ferreira@saier.com', NULL, 'Debora de Souza Ferreira', '$2a$10$gxwyl2pf6JVpFqA4O66MLOep88MTD5VQtDagMOHtVp8TQVISO6yOu', '2019-06-17 12:43:31', 'Debora de Souza', 3),
	(8, b'1', '2018-01-01 00:00:00', 'elisio.junior@saier.com', '2018-08-01 12:38:18', 'Elisio Custodio Brentan Junior', '$2a$10$JBXOAO/I634wWSt.Cv41y.YPkcKmNkvz30dJhQbLA8PXv9D1evqf.', '2019-06-17 12:43:31', 'Elisio Custodio', 3),
	(9, b'1', '2018-01-01 00:00:00', 'estela.cotta@saier.com', '2018-07-31 10:19:18', 'Estela  Maria Szytko Cotta', '$2a$10$/YZz1poetZ2BBc.qFym19./2iP3ulB9rt9X/S9h6zCqz/07XtrXkS', '2019-06-17 12:43:31', 'Estela Maria', 3),
	(10, b'1', '2018-01-01 00:00:00', 'felipe.pereira@saier.com', '2018-08-02 07:35:33', 'Felipe C de Almeida Pereira', '$2a$10$ZoSKMPcruxKHBC5bqDL39O6bBQmbhLJS3CS6B9A4/yAlF6rAEmT3W', '2019-06-17 12:43:31', 'Felipe Almeida', 3),
	(11, b'1', '2018-01-01 00:00:00', 'giovanna.pereira@saier.com', '2018-08-02 08:34:11', 'Giovanna Silva Pereira ', '$2a$10$VVyqa3nGK4yaeb0nUIUwYextEgr1YAV9a1SDJHjrprXZzUoWmaWC6', '2019-06-17 12:43:31', 'Giovana Silva', 3),
	(12, b'1', '2018-01-01 00:00:00', 'higor.silva@saier.com', NULL, 'Higor Ismael Iglesias da Silva', '$2a$10$ZVWerW8NDkG57mW2SbyNI.7RfUsSjyxQQ1cpblL7CT3Bi9GCGRMqG', '2019-06-17 12:43:31', 'Higor Ismael', 3),
	(13, b'1', '2018-01-01 00:00:00', 'janaina.fereira@saier.com', '2018-07-31 10:37:43', 'Janaina Carla Ferreira', '$2a$10$NmTbxebGR.Mh.be4XkvjxOVCqRloXj02aqiUxIcBCxMONFfdYpEMG', '2019-06-17 12:43:31', 'Janaina Carla', 3),
	(14, b'1', '2018-01-01 00:00:00', 'luis.junior@saier.com', '2018-08-02 10:36:41', 'Luis Carlos da Silva Junior', '$2a$10$AAdHKZyvImpBbTxY96ILxO4p6mgUslhWEfr0r/COZNCs6PeHd6i4y', '2019-06-17 12:43:31', 'Luis Carlos', 3),
	(15, b'1', '2018-01-01 00:00:00', 'marcia.batistao@saier.com', '2018-08-02 09:35:41', 'Marcia Batistao', '$2a$10$6S5vPcTDDBZXaIG7IJmce./upFh09YrlW4Wx4iKNegflaGMOncsIK', '2019-06-17 12:43:31', 'Marcia Batistao', 3),
	(16, b'1', '2018-01-01 00:00:00', 'maria.komatsu@saier.com', '2018-08-01 11:02:23', 'Maria de Lurdes C Basso Komatsu', '$2a$10$/EfjDOCS6gUxNivxVUIbtOtNlGLdIxwgpu2iylVWDQkODx.1uuxBq', '2019-06-17 12:43:31', 'Maria de Lurdes', 3),
	(17, b'1', '2018-01-01 00:00:00', 'rafaela.belliboni@saier.com', '2018-08-01 11:43:50', 'Rafaela Luz Belliboni', '$2a$10$Z7WoRwdiyY2patESHmmm9.DMbQQm3ZL0MObQXYVRMKtULCcItl4.S', '2019-06-17 12:43:31', 'Rafaela Luz', 3),
	(18, b'1', '2018-01-01 00:00:00', 'silvia.silva@saier.com', '2018-08-01 08:58:45', 'Silvia Regina da Silva', '$2a$10$FTowv5MfkZ0xPpk2G5L4xeWGuu0xR0XKXXrZ9oGz6afUHDwma0bLi', '2019-06-17 12:43:31', 'Silvia Regina', 3),
	(19, b'1', '2018-01-01 00:00:00', 'sueli.sagatelli@saier.com', '2018-08-01 10:36:11', 'Sueli de Melo Sagatelli ', '$2a$10$TA0Qx8UJtQVnz6rPlDIR2uGrOex7JUG/O/XuYyD6./G/0.tEufsm2', '2019-06-17 12:43:31', 'Sueli de Melo', 3),
	(20, b'1', '2018-01-01 00:00:00', 'taiza.oliveira@saier.com', '2018-08-02 07:46:32', 'Taiza Maria Lozano de Oliveira', '$2a$10$qwjd/J0J1fistw6R/LoVU.BB2iYybAoiFIlcSKUeUFYf7gqH2.mZW', '2019-06-17 12:43:31', 'Taiza Maria', 3),
	(21, b'1', '2018-01-01 00:00:00', 'thais.santos@saier.com', '2018-07-31 09:40:54', 'Thais Pricila dos Santos', '$2a$10$1XxlEvprTHqkNcbkt9W6devPxNThjSjWnYL5CSWUN3mwfMIbUr8Za', '2019-06-17 12:43:31', 'Thais Pricila', 3),
	(22, b'1', '2018-01-01 00:00:00', 'thaysa.andrade@saier.com', '2018-07-31 10:31:44', 'Thaysa A Garbosa Vieira de Andrade', '$2a$10$UYn4Fcs7dd3saaTeZPrev.eMGwRD.9ZvoaecgaV0VmPxQkfEl9hky', '2019-06-17 12:43:31', 'Thaysa Garbosa', 3);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
