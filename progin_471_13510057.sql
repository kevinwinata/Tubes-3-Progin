-- phpMyAdmin SQL Dump
-- version 3.3.9
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Apr 13, 2013 at 06:44 AM
-- Server version: 5.5.8
-- PHP Version: 5.3.5

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `progin_405_13510057`
--

-- --------------------------------------------------------

--
-- Table structure for table `assignee`
--

CREATE TABLE IF NOT EXISTS `assignee` (
  `username` varchar(12) NOT NULL,
  `idtugas` varchar(10) NOT NULL,
  KEY `assignee_ibfk_1` (`idtugas`),
  KEY `assignee_ibfk_2` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `assignee`
--


-- --------------------------------------------------------

--
-- Table structure for table `attachment`
--

CREATE TABLE IF NOT EXISTS `attachment` (
  `idtugas` varchar(10) NOT NULL,
  `isiattachment` varchar(100) NOT NULL,
  KEY `attacment_ibfk_1` (`idtugas`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `attachment`
--


-- --------------------------------------------------------

--
-- Table structure for table `hak`
--

CREATE TABLE IF NOT EXISTS `hak` (
  `username` varchar(12) NOT NULL,
  `idkategori` varchar(10) NOT NULL,
  KEY `hak_ibfk_1` (`username`),
  KEY `hak_ibfk_2` (`idkategori`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `hak`
--


-- --------------------------------------------------------

--
-- Table structure for table `kategori`
--

CREATE TABLE IF NOT EXISTS `kategori` (
  `idkategori` varchar(10) NOT NULL,
  `namakategori` varchar(20) NOT NULL,
  PRIMARY KEY (`idkategori`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `kategori`
--


-- --------------------------------------------------------

--
-- Table structure for table `komentar`
--

CREATE TABLE IF NOT EXISTS `komentar` (
  `idtugas` varchar(10) NOT NULL,
  `username` varchar(12) NOT NULL,
  `isikomentar` varchar(100) NOT NULL,
  `waktu` datetime NOT NULL,
  KEY `komentar_ibfk_1` (`username`),
  KEY `komentar_ibfk_2` (`idtugas`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `komentar`
--


-- --------------------------------------------------------

--
-- Table structure for table `tag`
--

CREATE TABLE IF NOT EXISTS `tag` (
  `idtugas` varchar(10) NOT NULL,
  `isitag` varchar(20) NOT NULL,
  KEY `tag_ibfk_1` (`idtugas`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tag`
--


-- --------------------------------------------------------

--
-- Table structure for table `tugas`
--

CREATE TABLE IF NOT EXISTS `tugas` (
  `idtugas` varchar(10) NOT NULL,
  `namatugas` varchar(20) NOT NULL,
  `deadline` date NOT NULL,
  `idkategori` varchar(10) NOT NULL,
  `username` varchar(12) NOT NULL,
  `status` varchar(10) NOT NULL,
  UNIQUE KEY `idtugas` (`idtugas`),
  KEY `tugas_ibfk_1` (`username`),
  KEY `tugas_ibfk_2` (`idkategori`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tugas`
--


-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `username` varchar(12) NOT NULL,
  `password` varchar(20) NOT NULL,
  `fullname` varchar(25) NOT NULL,
  `birthdate` date NOT NULL,
  `phonenumber` varchar(12) NOT NULL,
  `email` varchar(100) NOT NULL,
  `avatar` varchar(50) NOT NULL,
  PRIMARY KEY (`username`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`username`, `password`, `fullname`, `birthdate`, `phonenumber`, `email`, `avatar`) VALUES
('kevina', '22222222', 'Kevin Alfianto', '1992-07-02', '081912345678', 'kevina@gmail.com', 'upload/kevin.jpg'),
('kevinw', '33333333', 'Kevin Winata', '1992-04-17', '081987654321', 'kevinw@gmail.com', ''),
('mario', '12345678', 'Mario Orlando', '1992-10-09', '081977922140', 'mario_orlando@live.com', 'upload/277941_700b.jpg');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `assignee`
--
ALTER TABLE `assignee`
  ADD CONSTRAINT `assignee_ibfk_1` FOREIGN KEY (`idtugas`) REFERENCES `tugas` (`idtugas`),
  ADD CONSTRAINT `assignee_ibfk_2` FOREIGN KEY (`username`) REFERENCES `user` (`username`);

--
-- Constraints for table `attachment`
--
ALTER TABLE `attachment`
  ADD CONSTRAINT `attacment_ibfk_1` FOREIGN KEY (`idtugas`) REFERENCES `tugas` (`idtugas`);

--
-- Constraints for table `hak`
--
ALTER TABLE `hak`
  ADD CONSTRAINT `hak_ibfk_1` FOREIGN KEY (`username`) REFERENCES `user` (`username`),
  ADD CONSTRAINT `hak_ibfk_2` FOREIGN KEY (`idkategori`) REFERENCES `kategori` (`idkategori`);

--
-- Constraints for table `komentar`
--
ALTER TABLE `komentar`
  ADD CONSTRAINT `komentar_ibfk_1` FOREIGN KEY (`username`) REFERENCES `user` (`username`),
  ADD CONSTRAINT `komentar_ibfk_2` FOREIGN KEY (`idtugas`) REFERENCES `tugas` (`idtugas`);

--
-- Constraints for table `tag`
--
ALTER TABLE `tag`
  ADD CONSTRAINT `tag_ibfk_1` FOREIGN KEY (`idtugas`) REFERENCES `tugas` (`idtugas`);

--
-- Constraints for table `tugas`
--
ALTER TABLE `tugas`
  ADD CONSTRAINT `tugas_ibfk_1` FOREIGN KEY (`username`) REFERENCES `user` (`username`),
  ADD CONSTRAINT `tugas_ibfk_2` FOREIGN KEY (`idkategori`) REFERENCES `kategori` (`idkategori`);
