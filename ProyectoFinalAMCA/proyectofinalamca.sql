-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 17-03-2023 a las 22:45:43
-- Versión del servidor: 10.4.21-MariaDB
-- Versión de PHP: 8.0.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `proyectofinalamca`
--

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`id`, `caracteristicas`, `nombre`, `precio`, `stock`) VALUES
(1, 'Pegatinas de Emojis:\r\n- Pack de 5 Stickers.\r\n- Laminas de vinilo serigrafiadas', 'Pegatina', 2.5, 20);

--
-- Volcado de datos para la tabla `rol`
--

INSERT INTO `rol` (`id`, `role`) VALUES
(1, 'ROLE_USER'),
(2, 'ROLE_ADMIN');

--
-- Volcado de datos para la tabla `roles_usuarios`
--

INSERT INTO `roles_usuarios` (`id_rol`, `id_usuario`) VALUES
(2, 16);

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id`, `direccion`, `correo_electronico`, `nombre_apellidos`, `password`, `nick`) VALUES
(16, 'admin', 'admin@merch.com', 'admin istrador', '$2a$10$M.DVg75F9/IJQw4fUnkSa.F/0yWc8v2hmPhU6ctU4cJwJ0ueqa.mu', 'admin');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
