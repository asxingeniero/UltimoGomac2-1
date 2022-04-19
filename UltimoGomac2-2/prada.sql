-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3306
-- Tiempo de generación: 11-04-2022 a las 20:23:17
-- Versión del servidor: 5.7.19
-- Versión de PHP: 7.1.9

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
-- Estructura de tabla para la tabla `apertura`
--

DROP TABLE IF EXISTS `apertura`;
CREATE TABLE IF NOT EXISTS `apertura` (
  `cod_apertura` int(11) NOT NULL AUTO_INCREMENT,
  `cod_usuario_FK` int(11) NOT NULL,
  `monto_apertura` bigint(20) NOT NULL,
  `fecha_apertura` date NOT NULL,
  `hora_apertura` time NOT NULL,
  `nombreCaja` varchar(255) NOT NULL,
  PRIMARY KEY (`cod_apertura`),
  KEY `cod_usuarioFK_idx2` (`cod_usuario_FK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categoria`
--

DROP TABLE IF EXISTS `categoria`;
CREATE TABLE IF NOT EXISTS `categoria` (
  `nombre_categoria` varchar(450) NOT NULL,
  `cod_categoria` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion_categoria` varchar(450) NOT NULL,
  PRIMARY KEY (`cod_categoria`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `categoria`
--

INSERT INTO `categoria` (`nombre_categoria`, `cod_categoria`, `descripcion_categoria`) VALUES
('Belleza', 1, '1');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cierre`
--

DROP TABLE IF EXISTS `cierre`;
CREATE TABLE IF NOT EXISTS `cierre` (
  `cod_cierre` int(11) NOT NULL AUTO_INCREMENT,
  `cod_usuario_fk` int(11) NOT NULL,
  `monto_cierre` bigint(20) NOT NULL,
  `fecha_cierre` date NOT NULL,
  `hora_cierre` time NOT NULL,
  `diferencia_cierre` bigint(20) NOT NULL,
  `nombreCaja` varchar(250) NOT NULL,
  `efectivo` bigint(20) NOT NULL,
  `tarjeta` bigint(20) NOT NULL,
  `credito` bigint(20) NOT NULL,
  PRIMARY KEY (`cod_cierre`),
  KEY `cod_usuarioFK_idx` (`cod_usuario_fk`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

DROP TABLE IF EXISTS `cliente`;
CREATE TABLE IF NOT EXISTS `cliente` (
  `cod_cliente` int(11) NOT NULL,
  `rut_cliente` varchar(20) NOT NULL,
  PRIMARY KEY (`cod_cliente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`cod_cliente`, `rut_cliente`) VALUES
(2, '0'),
(3, '93345');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `datos_empresa`
--

DROP TABLE IF EXISTS `datos_empresa`;
CREATE TABLE IF NOT EXISTS `datos_empresa` (
  `cod_empresa` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_empresa` varchar(450) NOT NULL,
  `rut_empresa` varchar(450) NOT NULL,
  `domicilio_empresa` varchar(450) NOT NULL,
  `actividad_empresa` varchar(450) NOT NULL,
  PRIMARY KEY (`cod_empresa`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `datos_empresa`
--

INSERT INTO `datos_empresa` (`cod_empresa`, `nombre_empresa`, `rut_empresa`, `domicilio_empresa`, `actividad_empresa`) VALUES
(1, 'Gomac', '1110800-9', 'C.C. Combeima L.237', 'Articulos de Belleza');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle_venta`
--

DROP TABLE IF EXISTS `detalle_venta`;
CREATE TABLE IF NOT EXISTS `detalle_venta` (
  `cod_detalle` int(11) NOT NULL AUTO_INCREMENT,
  `cantidad_detalle` int(11) NOT NULL,
  `cod_productoFK` bigint(20) NOT NULL,
  `precio_producto` bigint(20) NOT NULL,
  `cod_ventaFK` int(11) NOT NULL,
  `subtotal` bigint(20) NOT NULL,
  `subPrecioCompra` bigint(20) NOT NULL,
  `precio_compra` bigint(20) NOT NULL,
  `precio_v_mayor` bigint(20) NOT NULL,
  `descuento_xp` bigint(20) NOT NULL,
  PRIMARY KEY (`cod_detalle`,`cod_ventaFK`),
  KEY `detalle_productoFK_idx` (`cod_productoFK`),
  KEY `detalle_ventaFK_idx` (`cod_ventaFK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `devolucion`
--

DROP TABLE IF EXISTS `devolucion`;
CREATE TABLE IF NOT EXISTS `devolucion` (
  `cod_devolucion` int(11) NOT NULL AUTO_INCREMENT,
  `cod_venta` int(11) NOT NULL,
  `cod_producto` bigint(20) NOT NULL,
  `nombre_prod` varchar(100) COLLATE utf8_spanish2_ci NOT NULL,
  `cantidad` int(11) NOT NULL,
  `obs` varchar(300) COLLATE utf8_spanish2_ci NOT NULL,
  `fecha_devolucion` date NOT NULL,
  PRIMARY KEY (`cod_devolucion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `gastos`
--

DROP TABLE IF EXISTS `gastos`;
CREATE TABLE IF NOT EXISTS `gastos` (
  `cod_gastos` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(100) COLLATE utf8_spanish2_ci NOT NULL,
  `valor` bigint(20) NOT NULL,
  `obs` varchar(200) COLLATE utf8_spanish2_ci NOT NULL,
  PRIMARY KEY (`cod_gastos`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci COMMENT='gastos personales diarios';

--
-- Volcado de datos para la tabla `gastos`
--

INSERT INTO `gastos` (`cod_gastos`, `descripcion`, `valor`, `obs`) VALUES
(1, 'Almuerzo', 20000, 'almuerzo de trabajo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `historial`
--

DROP TABLE IF EXISTS `historial`;
CREATE TABLE IF NOT EXISTS `historial` (
  `cod_historial` int(11) NOT NULL AUTO_INCREMENT,
  `cod_productoFK1` bigint(20) NOT NULL,
  `cod_usuarioFK1` int(11) NOT NULL,
  `descripcion` varchar(200) DEFAULT NULL,
  `referencia` varchar(100) DEFAULT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  PRIMARY KEY (`cod_historial`),
  KEY `historial_productoFK_idx` (`cod_productoFK1`),
  KEY `historial_usuarioFK_idx` (`cod_usuarioFK1`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `historial`
--

INSERT INTO `historial` (`cod_historial`, `cod_productoFK1`, `cod_usuarioFK1`, `descripcion`, `referencia`, `cantidad`, `fecha`) VALUES
(1, 1001, 1, 'Sumo al stock ', 'COMPRA', 200, '2021-06-16'),
(2, 1002, 1, 'Sumo al stock ', 'c', 1, '2021-06-18'),
(3, 1001, 1, 'Sumo al stock ', 'a', 1, '2021-06-18'),
(4, 1001, 1, 'Resto al Stock', 'de', 1, '2021-06-18');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `persona`
--

DROP TABLE IF EXISTS `persona`;
CREATE TABLE IF NOT EXISTS `persona` (
  `cod_persona` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_persona` varchar(255) NOT NULL,
  `direccion` varchar(255) NOT NULL,
  `telefono` varchar(12) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `contacto` varchar(20) DEFAULT NULL,
  `cel_contacto` varchar(10) DEFAULT NULL,
  `correo_contacto` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`cod_persona`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `persona`
--

INSERT INTO `persona` (`cod_persona`, `nombre_persona`, `direccion`, `telefono`, `email`, `contacto`, `cel_contacto`, `correo_contacto`) VALUES
(1, 'Administrator', 'Ibague', '300', 'admin@gmail.com', NULL, NULL, NULL),
(2, 'Cliente General', '0', '0', '0', NULL, NULL, NULL),
(3, 'Alvaro Salasar', 'Calle 65 ', '31818', 'salasar@gmail.com', NULL, NULL, NULL),
(4, 'Luis Turraja', 'salado especial', '45455', 'turraja@gmail.com', '', '', ''),
(5, 'Empresa 1', 'calle 19', '2323', 'cual@gmail.com', NULL, NULL, NULL),
(6, 'Empresa 2', 'Ambala', '2424', 'dos@gmail.com', NULL, NULL, NULL),
(9, 'Chocolatina Yumbo', 'jumbo', '500', 'yumbo@gmsil.com', NULL, NULL, NULL);

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
  `cod_proveedor` varchar(11) DEFAULT NULL,
  `cod_categoriaFK` int(11) NOT NULL,
  KEY `cod_cat_prodFK_idx` (`cod_categoriaFK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`cod_producto`, `nombre_producto`, `descripcion_producto`, `unidad_producto`, `precio_producto`, `precio_venta_mayor`, `precio_compra`, `stock_producto`, `ubicacion_bodega`, `cod_proveedor`, `cod_categoriaFK`) VALUES
(111, '2', '3', 'unidad', 6, 7, 8, 9, '10', '5', 1),
(222, 'cocacola', 'bebida', 'unidad', 3000, 2800, 2500, 50, '1', '6', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productoescaneado`
--

DROP TABLE IF EXISTS `productoescaneado`;
CREATE TABLE IF NOT EXISTS `productoescaneado` (
  `cod_producto` bigint(20) NOT NULL,
  `nombre` varchar(450) NOT NULL,
  `cantidad` int(11) NOT NULL,
  PRIMARY KEY (`cod_producto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productostock`
--

DROP TABLE IF EXISTS `productostock`;
CREATE TABLE IF NOT EXISTS `productostock` (
  `cod_producto` bigint(20) NOT NULL,
  `nombre_producto` varchar(450) NOT NULL,
  `descripcion_producto` varchar(450) DEFAULT NULL,
  `unidad_producto` varchar(450) DEFAULT NULL,
  `precio_producto` bigint(20) NOT NULL,
  `precio_compra` bigint(20) NOT NULL,
  `stock_producto` int(11) NOT NULL,
  PRIMARY KEY (`cod_producto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedor`
--

DROP TABLE IF EXISTS `proveedor`;
CREATE TABLE IF NOT EXISTS `proveedor` (
  `cod_proveedor` int(11) NOT NULL,
  `rut_proveedor` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  KEY `fk_cod_proveedor` (`cod_proveedor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `proveedor`
--

INSERT INTO `proveedor` (`cod_proveedor`, `rut_proveedor`) VALUES
(5, '1313'),
(6, '1414'),
(9, '1515');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

DROP TABLE IF EXISTS `usuario`;
CREATE TABLE IF NOT EXISTS `usuario` (
  `cod_usuario` int(11) NOT NULL,
  `rut_usuario` varchar(20) NOT NULL,
  `login` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `estado` varchar(8) NOT NULL,
  `acceso` varchar(15) NOT NULL,
  PRIMARY KEY (`cod_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`cod_usuario`, `rut_usuario`, `login`, `password`, `estado`, `acceso`) VALUES
(1, '11101', '1', '1', 'Activo', 'Administrador'),
(4, '3434', 'turraja', 'turraja', 'Activo', 'UsuarioC');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `venta`
--

DROP TABLE IF EXISTS `venta`;
CREATE TABLE IF NOT EXISTS `venta` (
  `cod_venta` int(11) NOT NULL AUTO_INCREMENT,
  `fecha_venta` date NOT NULL,
  `total_venta` bigint(20) NOT NULL,
  `cod_usuarioFK` int(11) NOT NULL,
  `cod_clienteFK` int(11) NOT NULL,
  `tipo_comprobante` varchar(10) NOT NULL,
  `num_factura` bigint(20) DEFAULT NULL,
  `pago` bigint(20) NOT NULL,
  `descuento` bigint(20) NOT NULL,
  `metodo_pago` varchar(250) DEFAULT NULL,
  `nomCaja` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`cod_venta`),
  KEY `venta_usuarioFK_idx` (`cod_usuarioFK`),
  KEY `venta_clienteFK_idx` (`cod_clienteFK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `apertura`
--
ALTER TABLE `apertura`
  ADD CONSTRAINT `cod_usuarioFK` FOREIGN KEY (`cod_usuario_FK`) REFERENCES `usuario` (`cod_usuario`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `cierre`
--
ALTER TABLE `cierre`
  ADD CONSTRAINT `cod_usuario_FKS` FOREIGN KEY (`cod_usuario_fk`) REFERENCES `usuario` (`cod_usuario`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD CONSTRAINT `cliente_personaFK` FOREIGN KEY (`cod_cliente`) REFERENCES `persona` (`cod_persona`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `detalle_venta`
--
ALTER TABLE `detalle_venta`
  ADD CONSTRAINT `detalle_ventaFK` FOREIGN KEY (`cod_ventaFK`) REFERENCES `venta` (`cod_venta`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `proveedor`
--
ALTER TABLE `proveedor`
  ADD CONSTRAINT `proveedor_ibfk_1` FOREIGN KEY (`cod_proveedor`) REFERENCES `persona` (`cod_persona`) ON DELETE CASCADE ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
