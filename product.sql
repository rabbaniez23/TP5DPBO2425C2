-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 17, 2025 at 08:36 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_product`
--

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `no` int(11) NOT NULL,
  `id` varchar(255) NOT NULL,
  `nama` varchar(255) NOT NULL,
  `harga` double NOT NULL,
  `kategori` varchar(255) NOT NULL,
  `stok` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`no`, `id`, `nama`, `harga`, `kategori`, `stok`) VALUES
(1, 'PRD001', 'Laptop ASUS', 8500000, 'Elektronik', 25),
(2, 'PRD002', 'Mouse Wireless', 150000, 'Elektronik', 150),
(3, 'PRD003', 'Keyboard Gaming', 450000, 'Elektronik', 80),
(4, 'PRD004', 'Monitor 24 inch', 2200000, 'Elektronik', 40),
(5, 'PRD005', 'Headset Gaming', 350000, 'Elektronik', 120),
(6, 'PRD006', 'Smartphone Samsung', 4500000, 'Elektronik', 55),
(7, 'PRD007', 'Charger USB-C', 85000, 'Aksesoris', 200),
(8, 'PRD008', 'Power Bank', 250000, 'Aksesoris', 180),
(9, 'PRD009', 'Webcam HD', 180000, 'Elektronik', 95),
(10, 'PRD010', 'Speaker Bluetooth', 320000, 'Elektronik', 70),
(11, 'PRD011', 'Tablet Android', 2800000, 'Elektronik', 35),
(12, 'PRD012', 'Smartwatch', 1200000, 'Aksesoris', 60),
(13, 'PRD013', 'Flash Drive 32GB', 65000, 'Penyimpanan', 300),
(14, 'PRD014', 'Hard Disk 1TB', 750000, 'Penyimpanan', 50),
(15, 'PRD015', 'Router WiFi', 420000, 'Jaringan', 85),
(16, 'PRD016', 'Kabel HDMI', 45000, 'Aksesoris', 250),
(17, 'PRD017', 'Printer Inkjet', 850000, 'Perangkat Kantor', 30),
(18, 'PRD018', 'Scanner Document', 650000, 'Perangkat Kantor', 20),
(19, 'PRD019', 'Cooling Pad', 120000, 'Aksesoris', 110),
(24, 'PRD021', 'AYAM GORENG', 37000, 'Makanan', 10);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`no`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `no` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
