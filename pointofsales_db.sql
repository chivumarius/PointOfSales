-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 31, 2024 at 10:52 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pointofsales_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `customers`
--

CREATE TABLE `customers` (
  `customer_name` varchar(50) NOT NULL,
  `mobile_number` int(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `customers`
--

INSERT INTO `customers` (`customer_name`, `mobile_number`) VALUES
('Marius', 112233),
('Andrei', 212121),
('Jeanine', 434343),
('Robert', 484848),
('Nicholas', 625242),
('Aurel', 668844),
('Ella', 747276),
('Stefan', 776655),
('Cristian Ronaldo', 887799),
('George', 939393);

-- --------------------------------------------------------

--
-- Table structure for table `employees`
--

CREATE TABLE `employees` (
  `employee_name` varchar(50) NOT NULL,
  `mobile_number` int(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `employees`
--

INSERT INTO `employees` (`employee_name`, `mobile_number`) VALUES
('Marian Ionescu', 332211),
('Daniel Savescu', 445544),
('Elena Seorgescu', 464646),
('Mihai Anton', 663311),
('Constantin Iliescu', 665544),
('Laura Ivan', 771122),
('Radu Constantin', 882244),
('Ionut Stefanescu', 887722);

-- --------------------------------------------------------

--
-- Table structure for table `invoice`
--

CREATE TABLE `invoice` (
  `id` int(11) NOT NULL,
  `customer_name` varchar(200) NOT NULL,
  `products_sold` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `date` date NOT NULL,
  `total_price` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='total_price';

--
-- Dumping data for table `invoice`
--

INSERT INTO `invoice` (`id`, `customer_name`, `products_sold`, `date`, `total_price`) VALUES
(2, 'Marius', 'Milk , Goat  Cheese , ', '2024-01-30', 9),
(3, 'Marcel Gheorghe', 'Pepsi , Sprite , ', '2024-01-30', 4),
(4, 'Alina Basilescu', 'Chocolate , Cottage Cheese , ', '2024-01-30', 9),
(5, 'Eleonora Stoica', 'Milk , Candle , ', '2024-01-30', 4),
(6, 'Stefa Gabriel', 'Goat  Cheese , Cookies , Gum , ', '2024-01-30', 9),
(7, 'Marius Chivu', 'Sprite , Pepsi , ', '2024-01-30', 4);

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `bar_code` varchar(50) NOT NULL,
  `product_name` varchar(50) NOT NULL,
  `price` float NOT NULL,
  `quantity` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`bar_code`, `product_name`, `price`, `quantity`) VALUES
('11005', 'Pepsi', 1.8, 1),
('110077', 'Sprite', 1.8, 1),
('12345', 'Gum', 1, 4),
('22333', 'Cookies', 1.5, 1),
('228877', 'Goat  Cheese', 6.5, 1),
('3322', 'Candle', 2, 50),
('332211', 'Milk', 2, 1),
('54321', 'Chocolate', 3.2, 1),
('662244', 'Cottage Cheese', 5.8, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`mobile_number`);

--
-- Indexes for table `employees`
--
ALTER TABLE `employees`
  ADD PRIMARY KEY (`mobile_number`);

--
-- Indexes for table `invoice`
--
ALTER TABLE `invoice`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`bar_code`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `invoice`
--
ALTER TABLE `invoice`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
