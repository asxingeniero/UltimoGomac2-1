-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3306
-- Tiempo de generación: 09-07-2021 a las 20:24:09
-- Versión del servidor: 5.7.19
-- Versión de PHP: 5.6.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `gomac_v_0707_dev`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

DROP TABLE IF EXISTS `producto`;
CREATE TABLE IF NOT EXISTS `producto` (
  `cod_producto` bigint(20) NOT NULL,
  `nombre_producto` varchar(255) NOT NULL,
  `descripcion_producto` varchar(255) DEFAULT NULL,
  `unidad_producto` varchar(20) NOT NULL,
  `precio_producto` bigint(20) NOT NULL,
  `precio_venta_mayor` bigint(20) DEFAULT NULL COMMENT 'Campo nuevo para insertar precio de venta al por mayor del producto',
  `precio_compra` bigint(20) NOT NULL,
  `stock_producto` bigint(20) NOT NULL,
  `ubicacion_bodega` varchar(250) DEFAULT NULL,
  `cod_categoriaFK` int(11) NOT NULL,
  PRIMARY KEY (`cod_producto`,`cod_categoriaFK`),
  KEY `cod_cat_prodFK_idx` (`cod_categoriaFK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`cod_producto`, `nombre_producto`, `descripcion_producto`, `unidad_producto`, `precio_producto`, `precio_venta_mayor`, `precio_compra`, `stock_producto`, `ubicacion_bodega`, `cod_categoriaFK`) VALUES
(1001, 'Labial 01', 'gel', 'Unidad', 2000, 1500, 1000, 81, '1', 1),
(1002, 'Labial 02', 'humecta', 'Unidad', 3000, 2500, 2000, 88, '1', 1),
(1003, 'Labial 03', 'protector', 'Unidad', 4000, 3500, 3000, 92, '1', 1),
(1004, 'Labial 04', 'acrilico', 'Unidad', 5000, 4500, 4000, 98, '1', 1),
(1234567890123, 'prueba13digitos', 'pruebas', 'Unidad', 30000, 25000, 20000, 103, '1', 1);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
