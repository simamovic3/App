-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 10, 2018 at 04:13 PM
-- Server version: 5.7.14
-- PHP Version: 5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bess_account_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `account`
--

CREATE TABLE `account` (
  `user_id` int(11) NOT NULL,
  `age` int(11) DEFAULT NULL,
  `email` varchar(255) COLLATE utf8_slovenian_ci NOT NULL,
  `lastname` varchar(255) COLLATE utf8_slovenian_ci NOT NULL,
  `name` varchar(255) COLLATE utf8_slovenian_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_slovenian_ci NOT NULL,
  `username` varchar(255) COLLATE utf8_slovenian_ci NOT NULL,
  `role` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_slovenian_ci;

--
-- Dumping data for table `account`
--

INSERT INTO `account` (`user_id`, `age`, `email`, `lastname`, `name`, `password`, `username`, `role`) VALUES
(26, 22, 'emina123@gmail.com', 'Omanovic', 'Emina', 'Emina123', 'emina123', 1),
(27, 22, 'sajra@gmail.com', 'Muratovic', 'Sajra', 'Sajra123', 'sajra123', 1),
(28, 22, 'sunita@gmail.com', 'Imamovic', 'Sunita', 'Sunita123', 'sunita123', 1),
(25, 22, 'belma123@gmail.com', 'Homarac', 'Belma', 'Belma123', 'belma123', 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `account`
--
ALTER TABLE `account`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
