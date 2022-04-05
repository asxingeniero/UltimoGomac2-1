-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 18-06-2021 a las 17:51:08
-- Versión del servidor: 10.4.19-MariaDB
-- Versión de PHP: 8.0.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `gomac_v_18062021`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `apertura`
--

CREATE TABLE `apertura` (
  `cod_apertura` int(11) NOT NULL,
  `cod_usuario_FK` int(11) NOT NULL,
  `monto_apertura` bigint(20) NOT NULL,
  `fecha_apertura` date NOT NULL,
  `hora_apertura` time NOT NULL,
  `nombreCaja` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categoria`
--

CREATE TABLE `categoria` (
  `nombre_categoria` varchar(450) NOT NULL,
  `cod_categoria` int(11) NOT NULL,
  `descripcion_categoria` varchar(450) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `categoria`
--

INSERT INTO `categoria` (`nombre_categoria`, `cod_categoria`, `descripcion_categoria`) VALUES
('Belleza', 1, '1');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cierre`
--

CREATE TABLE `cierre` (
  `cod_cierre` int(11) NOT NULL,
  `cod_usuario_fk` int(11) NOT NULL,
  `monto_cierre` bigint(20) NOT NULL,
  `fecha_cierre` date NOT NULL,
  `hora_cierre` time NOT NULL,
  `diferencia_cierre` bigint(20) NOT NULL,
  `nombreCaja` varchar(250) NOT NULL,
  `efectivo` bigint(20) NOT NULL,
  `tarjeta` bigint(20) NOT NULL,
  `credito` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `cod_cliente` int(11) NOT NULL,
  `rut_cliente` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`cod_cliente`, `rut_cliente`) VALUES
(2, '0');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `datos_empresa`
--

CREATE TABLE `datos_empresa` (
  `cod_empresa` int(11) NOT NULL,
  `nombre_empresa` varchar(450) NOT NULL,
  `rut_empresa` varchar(450) NOT NULL,
  `domicilio_empresa` varchar(450) NOT NULL,
  `actividad_empresa` varchar(450) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `datos_empresa`
--

INSERT INTO `datos_empresa` (`cod_empresa`, `nombre_empresa`, `rut_empresa`, `domicilio_empresa`, `actividad_empresa`) VALUES
(1, 'Gomac', '1110800-9', 'C.C. Combeima L.237', 'Articulos de Belleza');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle_venta`
--

CREATE TABLE `detalle_venta` (
  `cod_detalle` int(11) NOT NULL,
  `cantidad_detalle` int(11) NOT NULL,
  `cod_productoFK` bigint(20) NOT NULL,
  `precio_producto` bigint(20) NOT NULL,
  `cod_ventaFK` int(11) NOT NULL,
  `subtotal` bigint(20) NOT NULL,
  `subPrecioCompra` bigint(20) NOT NULL,
  `precio_compra` bigint(20) NOT NULL,
  `precio_v_mayor` bigint(20) NOT NULL,
  `descuento_xp` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `detalle_venta`
--

INSERT INTO `detalle_venta` (`cod_detalle`, `cantidad_detalle`, `cod_productoFK`, `precio_producto`, `cod_ventaFK`, `subtotal`, `subPrecioCompra`, `precio_compra`, `precio_v_mayor`, `descuento_xp`) VALUES
(1, 1, 1001, 2000, 1, 2000, 1000, 1000, 0, 0),
(2, 1, 1002, 3000, 1, 3000, 2000, 2000, 0, 0),
(3, 1, 1003, 4000, 1, 4000, 3000, 3000, 0, 0),
(4, 1, 1004, 5000, 1, 5000, 4000, 4000, 0, 0),
(5, 1, 1004, 5000, 2, 5000, 4000, 4000, 0, 0),
(6, 12, 1001, 1500, 3, 18000, 12000, 1000, 1500, 0),
(7, 24, 1003, 3500, 3, 84000, 72000, 3000, 3500, 0),
(8, 1, 1001, 1500, 4, 1500, 1000, 1000, 0, 25),
(9, 1, 1002, 2250, 4, 2250, 2000, 2000, 0, 25),
(10, 1, 1003, 3000, 4, 3000, 3000, 3000, 0, 25),
(11, 1, 1004, 3750, 4, 3750, 4000, 4000, 0, 25),
(12, 1, 1001, 2000, 5, 2000, 1000, 1000, 0, 0),
(13, 1, 1001, 2000, 7, 2000, 1000, 1000, 0, 0),
(14, 26, 1001, 1500, 7, 39000, 26000, 1000, 1500, 0),
(15, 8, 1002, 2500, 9, 20000, 16000, 2000, 2500, 0),
(16, 1, 1002, 3000, 10, 3000, 2000, 2000, 0, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `historial`
--

CREATE TABLE `historial` (
  `cod_historial` int(11) NOT NULL,
  `cod_productoFK1` bigint(20) NOT NULL,
  `cod_usuarioFK1` int(11) NOT NULL,
  `descripcion` varchar(200) DEFAULT NULL,
  `referencia` varchar(100) DEFAULT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `fecha` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `historial`
--

INSERT INTO `historial` (`cod_historial`, `cod_productoFK1`, `cod_usuarioFK1`, `descripcion`, `referencia`, `cantidad`, `fecha`) VALUES
(1, 1001, 1, 'Sumo al stock ', 'COMPRA', 200, '2021-06-16'),
(2, 1002, 1, 'Sumo al stock ', 'c', 1, '2021-06-18');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `persona`
--

CREATE TABLE `persona` (
  `cod_persona` int(11) NOT NULL,
  `nombre_persona` varchar(255) NOT NULL,
  `direccion` varchar(255) NOT NULL,
  `telefono` varchar(12) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `contacto` varchar(20) DEFAULT NULL,
  `cel_contacto` varchar(10) DEFAULT NULL,
  `correo_contacto` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `persona`
--

INSERT INTO `persona` (`cod_persona`, `nombre_persona`, `direccion`, `telefono`, `email`, `contacto`, `cel_contacto`, `correo_contacto`) VALUES
(1, 'Administrator', 'Ibague', '300', 'admin@gmail.com', NULL, NULL, NULL),
(2, 'Cliente General', '0', '0', '0', NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `cod_producto` bigint(20) NOT NULL,
  `nombre_producto` varchar(255) NOT NULL,
  `descripcion_producto` varchar(255) DEFAULT NULL,
  `unidad_producto` varchar(20) NOT NULL,
  `precio_producto` bigint(20) NOT NULL,
  `precio_venta_mayor` bigint(20) DEFAULT NULL COMMENT 'Campo nuevo para insertar precio de venta al por mayor del producto',
  `precio_compra` bigint(20) NOT NULL,
  `stock_producto` bigint(20) NOT NULL,
  `ubicacion_bodega` varchar(250) DEFAULT NULL,
  `cod_categoriaFK` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`cod_producto`, `nombre_producto`, `descripcion_producto`, `unidad_producto`, `precio_producto`, `precio_venta_mayor`, `precio_compra`, `stock_producto`, `ubicacion_bodega`, `cod_categoriaFK`) VALUES
(1001, 'Labial 01', 'gel', 'Unidad', 2000, 1500, 1000, 100, '1', 1),
(1002, 'Labial 02', 'humecta', 'Unidad', 3000, 2500, 2000, 2501, '1', 1),
(1003, 'Labial 03', 'protector', 'Unidad', 4000, 3500, 3000, 100, '1', 1),
(1004, 'Labial 04', 'acrilico', 'Unidad', 5000, 4500, 4000, 100, '1', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productoescaneado`
--

CREATE TABLE `productoescaneado` (
  `cod_producto` bigint(20) NOT NULL,
  `nombre` varchar(450) NOT NULL,
  `cantidad` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productostock`
--

CREATE TABLE `productostock` (
  `cod_producto` bigint(20) NOT NULL,
  `nombre_producto` varchar(450) NOT NULL,
  `descripcion_producto` varchar(450) DEFAULT NULL,
  `unidad_producto` varchar(450) DEFAULT NULL,
  `precio_producto` bigint(20) NOT NULL,
  `precio_compra` bigint(20) NOT NULL,
  `stock_producto` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedor`
--

CREATE TABLE `proveedor` (
  `cod_proveedor` int(11) NOT NULL,
  `rut_proveedor` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `cod_usuario` int(11) NOT NULL,
  `rut_usuario` varchar(20) NOT NULL,
  `login` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `estado` varchar(8) NOT NULL,
  `acceso` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`cod_usuario`, `rut_usuario`, `login`, `password`, `estado`, `acceso`) VALUES
(1, '11101', '1', '1', 'Activo', 'Administrador');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `venta`
--

CREATE TABLE `venta` (
  `cod_venta` int(11) NOT NULL,
  `fecha_venta` date NOT NULL,
  `total_venta` bigint(20) NOT NULL,
  `cod_usuarioFK` int(11) NOT NULL,
  `cod_clienteFK` int(11) NOT NULL,
  `tipo_comprobante` varchar(10) NOT NULL,
  `num_factura` bigint(20) DEFAULT NULL,
  `pago` bigint(20) NOT NULL,
  `descuento` bigint(20) NOT NULL,
  `metodo_pago` varchar(250) DEFAULT NULL,
  `nomCaja` varchar(250) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `venta`
--

INSERT INTO `venta` (`cod_venta`, `fecha_venta`, `total_venta`, `cod_usuarioFK`, `cod_clienteFK`, `tipo_comprobante`, `num_factura`, `pago`, `descuento`, `metodo_pago`, `nomCaja`) VALUES
(1, '2021-06-15', 14000, 1, 2, 'Boleta', 0, 20000, 0, 'Efectivo', 'DESKTOP-6SF0T7E'),
(2, '2021-06-15', 5000, 1, 2, 'Boleta', 0, 25000, 0, 'Efectivo', 'DESKTOP-6SF0T7E'),
(3, '2021-06-15', 102000, 1, 2, 'Boleta', 0, 200000, 0, 'Efectivo', 'DESKTOP-6SF0T7E'),
(4, '2021-06-15', 10500, 1, 2, 'Boleta', 0, 15000, 0, 'Efectivo', 'DESKTOP-6SF0T7E'),
(5, '2021-06-15', 2000, 1, 2, 'Boleta', 0, 5000, 0, 'Efectivo', 'DESKTOP-6SF0T7E'),
(6, '2021-06-15', 0, 1, 2, 'Boleta', 0, 0, 0, NULL, NULL),
(7, '2021-06-15', 41000, 1, 2, 'Boleta', 0, 45000, 0, 'Efectivo', 'DESKTOP-6SF0T7E'),
(9, '2021-06-15', 20000, 1, 2, 'Boleta', 0, 50000, 0, 'Efectivo', 'DESKTOP-6SF0T7E'),
(10, '2021-06-17', 3000, 1, 2, 'Boleta', 0, 5000, 0, 'Efectivo', 'DESKTOP-6SF0T7E');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `apertura`
--
ALTER TABLE `apertura`
  ADD PRIMARY KEY (`cod_apertura`),
  ADD KEY `cod_usuarioFK_idx2` (`cod_usuario_FK`);

--
-- Indices de la tabla `categoria`
--
ALTER TABLE `categoria`
  ADD PRIMARY KEY (`cod_categoria`);

--
-- Indices de la tabla `cierre`
--
ALTER TABLE `cierre`
  ADD PRIMARY KEY (`cod_cierre`),
  ADD KEY `cod_usuarioFK_idx` (`cod_usuario_fk`);

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`cod_cliente`);

--
-- Indices de la tabla `datos_empresa`
--
ALTER TABLE `datos_empresa`
  ADD PRIMARY KEY (`cod_empresa`);

--
-- Indices de la tabla `detalle_venta`
--
ALTER TABLE `detalle_venta`
  ADD PRIMARY KEY (`cod_detalle`,`cod_ventaFK`),
  ADD KEY `detalle_productoFK_idx` (`cod_productoFK`),
  ADD KEY `detalle_ventaFK_idx` (`cod_ventaFK`);

--
-- Indices de la tabla `historial`
--
ALTER TABLE `historial`
  ADD PRIMARY KEY (`cod_historial`),
  ADD KEY `historial_productoFK_idx` (`cod_productoFK1`),
  ADD KEY `historial_usuarioFK_idx` (`cod_usuarioFK1`);

--
-- Indices de la tabla `persona`
--
ALTER TABLE `persona`
  ADD PRIMARY KEY (`cod_persona`);

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`cod_producto`,`cod_categoriaFK`),
  ADD KEY `cod_cat_prodFK_idx` (`cod_categoriaFK`);

--
-- Indices de la tabla `productoescaneado`
--
ALTER TABLE `productoescaneado`
  ADD PRIMARY KEY (`cod_producto`);

--
-- Indices de la tabla `productostock`
--
ALTER TABLE `productostock`
  ADD PRIMARY KEY (`cod_producto`);

--
-- Indices de la tabla `proveedor`
--
ALTER TABLE `proveedor`
  ADD PRIMARY KEY (`cod_proveedor`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`cod_usuario`);

--
-- Indices de la tabla `venta`
--
ALTER TABLE `venta`
  ADD PRIMARY KEY (`cod_venta`),
  ADD KEY `venta_usuarioFK_idx` (`cod_usuarioFK`),
  ADD KEY `venta_clienteFK_idx` (`cod_clienteFK`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `apertura`
--
ALTER TABLE `apertura`
  MODIFY `cod_apertura` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `categoria`
--
ALTER TABLE `categoria`
  MODIFY `cod_categoria` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `cierre`
--
ALTER TABLE `cierre`
  MODIFY `cod_cierre` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `datos_empresa`
--
ALTER TABLE `datos_empresa`
  MODIFY `cod_empresa` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `detalle_venta`
--
ALTER TABLE `detalle_venta`
  MODIFY `cod_detalle` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT de la tabla `historial`
--
ALTER TABLE `historial`
  MODIFY `cod_historial` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `persona`
--
ALTER TABLE `persona`
  MODIFY `cod_persona` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `venta`
--
ALTER TABLE `venta`
  MODIFY `cod_venta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

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
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
