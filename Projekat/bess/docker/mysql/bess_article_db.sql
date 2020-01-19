-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 10, 2018 at 04:14 PM
-- Server version: 5.7.14
-- PHP Version: 5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bess_article_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `article`
--

CREATE TABLE `article` (
  `article_id` int(11) NOT NULL,
  `date` datetime DEFAULT NULL,
  `description` varchar(255) COLLATE utf8_slovenian_ci NOT NULL,
  `picture_url` varchar(255) COLLATE utf8_slovenian_ci NOT NULL,
  `title` varchar(255) COLLATE utf8_slovenian_ci NOT NULL,
  `cattitle` varchar(255) COLLATE utf8_slovenian_ci NOT NULL,
  `username` varchar(255) COLLATE utf8_slovenian_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_slovenian_ci;

--
-- Dumping data for table `article`
--

INSERT INTO `article` (`article_id`, `date`, `description`, `picture_url`, `title`, `cattitle`, `username`) VALUES
(1, '2018-06-10 16:03:43', 'Here are my advices on how to completely renovate kitchen in only two weeks', 'https://upload.wikimedia.org/wikipedia/commons/thumb/7/72/Modern_kitchen_gnangarra.JPG/1200px-Modern_kitchen_gnangarra.JPG', 'Kitchen renovation in two weeks', 'KITCHEN', 'belma123'),
(2, NULL, 'novi artikal', 'https://i.imgur.com/4dFvDfJ.png', 'provjera', 'STORIES', 'sunita123');

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `cattitle` varchar(255) COLLATE utf8_slovenian_ci NOT NULL,
  `description` varchar(255) COLLATE utf8_slovenian_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_slovenian_ci;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`cattitle`, `description`) VALUES
('STORIES', 'Some renovation stories'),
('KITCHEN', 'Kitchen renovation advices'),
('BATHROOM', 'Advices on how to properly design bathrooms'),
('BEDROOM', 'Some tips for bedroom renovation'),
('LIVING ROOM', 'Living room decor advices'),
('GARDEN', 'Advices for designing garden'),
('DECOR', 'Decor at its finest');

-- --------------------------------------------------------

--
-- Table structure for table `comment`
--

CREATE TABLE `comment` (
  `comment_id` int(11) NOT NULL,
  `text` varchar(255) COLLATE utf8_slovenian_ci NOT NULL,
  `article_id` int(11) DEFAULT NULL,
  `username` varchar(255) COLLATE utf8_slovenian_ci DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_slovenian_ci;

-- --------------------------------------------------------

--
-- Table structure for table `picture`
--

CREATE TABLE `picture` (
  `picture_id` int(11) NOT NULL,
  `picture_url` varchar(255) COLLATE utf8_slovenian_ci NOT NULL,
  `title` varchar(255) COLLATE utf8_slovenian_ci NOT NULL,
  `article_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_slovenian_ci;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `username` varchar(255) COLLATE utf8_slovenian_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_slovenian_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`username`) VALUES
('belma123'),
('emina123'),
('sajra123'),
('sunita123');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `article`
--
ALTER TABLE `article`
  ADD PRIMARY KEY (`article_id`),
  ADD KEY `FKjw4dmj76xd7xaycfsx9ap1nx6` (`cattitle`),
  ADD KEY `FK2y2umcenoqhjdcdyi5i35i7hm` (`username`);

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`cattitle`);

--
-- Indexes for table `comment`
--
ALTER TABLE `comment`
  ADD PRIMARY KEY (`comment_id`),
  ADD KEY `FK5yx0uphgjc6ik6hb82kkw501y` (`article_id`),
  ADD KEY `FKarq5xh8jp9xtdg8npd1ql6xfh` (`username`);

--
-- Indexes for table `picture`
--
ALTER TABLE `picture`
  ADD PRIMARY KEY (`picture_id`),
  ADD KEY `FKlngijisidt3rohbwyinu0se0v` (`article_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `article`
--
ALTER TABLE `article`
  MODIFY `article_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `comment`
--
ALTER TABLE `comment`
  MODIFY `comment_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `picture`
--
ALTER TABLE `picture`
  MODIFY `picture_id` int(11) NOT NULL AUTO_INCREMENT;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
