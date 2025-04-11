DROP TABLE IF EXISTS `carrito_detalle`;
DROP TABLE IF EXISTS `carritos`;
DROP TABLE IF EXISTS `imagenes_producto`;
DROP TABLE IF EXISTS `historial_visitas`;
DROP TABLE IF EXISTS `favoritos`;
DROP TABLE IF EXISTS `envios`;
DROP TABLE IF EXISTS `detalle_orden`;
DROP TABLE IF EXISTS `transacciones_pago`;
DROP TABLE IF EXISTS `reseñas`;
DROP TABLE IF EXISTS `reportes`;
DROP TABLE IF EXISTS `productos`;
DROP TABLE IF EXISTS `ordenes`;
DROP TABLE IF EXISTS `mensajes`;
DROP TABLE IF EXISTS `categorias`;
DROP TABLE IF EXISTS `usuarios`;
DROP TABLE IF EXISTS `metodos_pago`;

-- ecommerce.metodos_pago definition
CREATE TABLE `metodos_pago` (
                                `id` int NOT NULL AUTO_INCREMENT,
                                `nombre` varchar(50) NOT NULL,
                                `descripcion` text,
                                `activo` tinyint(1) DEFAULT '1',
                                PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- ecommerce.usuarios definition
CREATE TABLE `usuarios` (
                            `id` int NOT NULL AUTO_INCREMENT,
                            `nombre` varchar(100) NOT NULL,
                            `email` varchar(150) NOT NULL,
                            `password` varchar(255) NOT NULL,
                            `telefono` varchar(20) DEFAULT NULL,
                            `direccion` text,
                            `tipo` enum('COMPRADOR','VENDEDOR','ADMIN') DEFAULT 'COMPRADOR',
                            `fecha_registro` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                            PRIMARY KEY (`id`),
                            UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- ecommerce.categorias definition

CREATE TABLE `categorias` (
                              `id` int NOT NULL AUTO_INCREMENT,
                              `nombre` varchar(100) NOT NULL,
                              `descripcion` text,
                              `categoria_padre` int DEFAULT NULL,
                              PRIMARY KEY (`id`),
                              UNIQUE KEY `nombre` (`nombre`),
                              KEY `categoria_padre` (`categoria_padre`),
                              CONSTRAINT `categorias_ibfk_1` FOREIGN KEY (`categoria_padre`) REFERENCES `categorias` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- ecommerce.mensajes definition
CREATE TABLE `mensajes` (
                            `id` int NOT NULL AUTO_INCREMENT,
                            `comprador_id` int NOT NULL,
                            `vendedor_id` int NOT NULL,
                            `contenido` text NOT NULL,
                            `fecha` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                            PRIMARY KEY (`id`),
                            KEY `comprador_id` (`comprador_id`),
                            KEY `vendedor_id` (`vendedor_id`),
                            CONSTRAINT `mensajes_ibfk_1` FOREIGN KEY (`comprador_id`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE,
                            CONSTRAINT `mensajes_ibfk_2` FOREIGN KEY (`vendedor_id`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- ecommerce.ordenes definition
CREATE TABLE `ordenes` (
                           `id` int NOT NULL AUTO_INCREMENT,
                           `comprador_id` int NOT NULL,
                           `total` decimal(10,2) NOT NULL,
                           `estado` enum('PENDIENTE','PAGADO','ENVIADO','ENTREGADO','CANCELADO') DEFAULT 'PENDIENTE',
                           `fecha_orden` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                           PRIMARY KEY (`id`),
                           KEY `comprador_id` (`comprador_id`),
                           CONSTRAINT `ordenes_ibfk_1` FOREIGN KEY (`comprador_id`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE,
                           CONSTRAINT `ordenes_chk_1` CHECK ((`total` >= 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- ecommerce.productos definition
CREATE TABLE `productos` (
                             `id` int NOT NULL AUTO_INCREMENT,
                             `vendedor_id` int NOT NULL,
                             `categoria_id` int DEFAULT NULL,
                             `nombre` varchar(200) NOT NULL,
                             `descripcion` text,
                             `precio` decimal(10,2) NOT NULL,
                             `stock` int DEFAULT '0',
                             `estado` enum('NUEVO','USADO') DEFAULT 'nuevo',
                             `fecha_publicacion` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                             PRIMARY KEY (`id`),
                             KEY `vendedor_id` (`vendedor_id`),
                             KEY `categoria_id` (`categoria_id`),
                             CONSTRAINT `productos_ibfk_1` FOREIGN KEY (`vendedor_id`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE,
                             CONSTRAINT `productos_ibfk_2` FOREIGN KEY (`categoria_id`) REFERENCES `categorias` (`id`) ON DELETE SET NULL,
                             CONSTRAINT `productos_chk_1` CHECK ((`precio` >= 0)),
                             CONSTRAINT `productos_chk_2` CHECK ((`stock` >= 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- ecommerce.reportes definition
CREATE TABLE `reportes` (
                            `id` int NOT NULL AUTO_INCREMENT,
                            `usuario_id` int NOT NULL,
                            `tipo` enum('PRODUCTO','USUARIO','ORDEN','OTRO') NOT NULL,
                            `descripcion` text NOT NULL,
                            `estado` enum('PENDIENTE','REVISADO','RESUELTO') DEFAULT 'PENDIENTE',
                            `fecha` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                            PRIMARY KEY (`id`),
                            KEY `usuario_id` (`usuario_id`),
                            CONSTRAINT `reportes_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- ecommerce.reseñas definition
CREATE TABLE `reseñas` (
                           `id` int NOT NULL AUTO_INCREMENT,
                           `producto_id` int NOT NULL,
                           `usuario_id` int NOT NULL,
                           `calificacion` int DEFAULT NULL,
                           `comentario` text,
                           `fecha` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                           PRIMARY KEY (`id`),
                           KEY `producto_id` (`producto_id`),
                           KEY `usuario_id` (`usuario_id`),
                           CONSTRAINT `reseñas_ibfk_1` FOREIGN KEY (`producto_id`) REFERENCES `productos` (`id`) ON DELETE CASCADE,
                           CONSTRAINT `reseñas_ibfk_2` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE,
                           CONSTRAINT `reseñas_chk_1` CHECK ((`calificacion` between 1 and 5))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- ecommerce.transacciones_pago definition

CREATE TABLE `transacciones_pago` (
                                      `id` int NOT NULL AUTO_INCREMENT,
                                      `orden_id` int NOT NULL,
                                      `metodo_pago_id` int NOT NULL,
                                      `monto` decimal(10,2) NOT NULL,
                                      `moneda` varchar(10) DEFAULT 'CLP',
                                      `estado` enum('PENDIENTE','COMPLETADO','FALLIDO') DEFAULT 'PENDIENTE',
                                      `codigo_autorizacion` varchar(100) DEFAULT NULL,
                                      `fecha_transaccion` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                      PRIMARY KEY (`id`),
                                      KEY `orden_id` (`orden_id`),
                                      KEY `metodo_pago_id` (`metodo_pago_id`),
                                      CONSTRAINT `transacciones_pago_ibfk_1` FOREIGN KEY (`orden_id`) REFERENCES `ordenes` (`id`) ON DELETE CASCADE,
                                      CONSTRAINT `transacciones_pago_ibfk_2` FOREIGN KEY (`metodo_pago_id`) REFERENCES `metodos_pago` (`id`) ON DELETE CASCADE,
                                      CONSTRAINT `transacciones_pago_chk_1` CHECK ((`monto` >= 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- ecommerce.detalle_orden definition

CREATE TABLE `detalle_orden` (
                                 `id` int NOT NULL AUTO_INCREMENT,
                                 `orden_id` int NOT NULL,
                                 `producto_id` int NOT NULL,
                                 `cantidad` int NOT NULL,
                                 `precio_unitario` decimal(10,2) NOT NULL,
                                 PRIMARY KEY (`id`),
                                 KEY `orden_id` (`orden_id`),
                                 KEY `producto_id` (`producto_id`),
                                 CONSTRAINT `detalle_orden_ibfk_1` FOREIGN KEY (`orden_id`) REFERENCES `ordenes` (`id`) ON DELETE CASCADE,
                                 CONSTRAINT `detalle_orden_ibfk_2` FOREIGN KEY (`producto_id`) REFERENCES `productos` (`id`) ON DELETE CASCADE,
                                 CONSTRAINT `detalle_orden_chk_1` CHECK ((`cantidad` > 0)),
                                 CONSTRAINT `detalle_orden_chk_2` CHECK ((`precio_unitario` >= 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- ecommerce.envios definition

CREATE TABLE `envios` (
                          `id` int NOT NULL AUTO_INCREMENT,
                          `orden_id` int NOT NULL,
                          `direccion_envio` text NOT NULL,
                          `estado` enum('PREPARANDO','ENVIADO','EN REPARTO','ENTREGADO') DEFAULT 'PREPARANDO',
                          `fecha_envio` timestamp NULL DEFAULT NULL,
                          `fecha_entrega` timestamp NULL DEFAULT NULL,
                          PRIMARY KEY (`id`),
                          KEY `orden_id` (`orden_id`),
                          CONSTRAINT `envios_ibfk_1` FOREIGN KEY (`orden_id`) REFERENCES `ordenes` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- ecommerce.favoritos definition

CREATE TABLE `favoritos` (
                             `id` int NOT NULL AUTO_INCREMENT,
                             `usuario_id` int NOT NULL,
                             `producto_id` int NOT NULL,
                             `fecha_agregado` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                             PRIMARY KEY (`id`),
                             KEY `usuario_id` (`usuario_id`),
                             KEY `producto_id` (`producto_id`),
                             CONSTRAINT `favoritos_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE,
                             CONSTRAINT `favoritos_ibfk_2` FOREIGN KEY (`producto_id`) REFERENCES `productos` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- ecommerce.historial_visitas definition

CREATE TABLE `historial_visitas` (
                                     `id` int NOT NULL AUTO_INCREMENT,
                                     `usuario_id` int NOT NULL,
                                     `producto_id` int NOT NULL,
                                     `fecha` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                     PRIMARY KEY (`id`),
                                     KEY `usuario_id` (`usuario_id`),
                                     KEY `producto_id` (`producto_id`),
                                     CONSTRAINT `historial_visitas_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE,
                                     CONSTRAINT `historial_visitas_ibfk_2` FOREIGN KEY (`producto_id`) REFERENCES `productos` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- ecommerce.imagenes_producto definition

CREATE TABLE `imagenes_producto` (
                                     `id` int NOT NULL AUTO_INCREMENT,
                                     `producto_id` int NOT NULL,
                                     `url_imagen` varchar(255) NOT NULL,
                                     PRIMARY KEY (`id`),
                                     KEY `producto_id` (`producto_id`),
                                     CONSTRAINT `imagenes_producto_ibfk_1` FOREIGN KEY (`producto_id`) REFERENCES `productos` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ecommerce.carritos definition
CREATE TABLE `carritos` (
                            `id` INT NOT NULL AUTO_INCREMENT,
                            `usuario_id` INT NOT NULL,
                            `fecha_creacion` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                            PRIMARY KEY (`id`),
                            FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ecommerce.carrito_detalle definition
CREATE TABLE `carrito_detalle` (
                                   `id` INT NOT NULL AUTO_INCREMENT,
                                   `carrito_id` INT NOT NULL,
                                   `producto_id` INT NOT NULL,
                                   `cantidad` INT NOT NULL CHECK (`cantidad` > 0),
                                   PRIMARY KEY (`id`),
                                   FOREIGN KEY (`carrito_id`) REFERENCES `carritos` (`id`) ON DELETE CASCADE,
                                   FOREIGN KEY (`producto_id`) REFERENCES `productos` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;