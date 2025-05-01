-- Insertar 10 registros en la tabla `metodos_pago`
INSERT INTO `metodos_pago` (`nombre`, `descripcion`, `activo`)
VALUES ('Tarjeta de Crédito', 'Pago con tarjeta de crédito', 1),
       ('Transferencia Bancaria', 'Pago mediante transferencia bancaria', 1),
       ('PayPal', 'Pago a través de PayPal', 1),
       ('Bitcoin', 'Pago mediante Bitcoin', 1),
       ('MercadoPago', 'Pago a través de MercadoPago', 1),
       ('Stripe', 'Pago mediante Stripe', 1),
       ('Skrill', 'Pago mediante Skrill', 1),
       ('Western Union', 'Pago mediante Western Union', 1),
       ('Apple Pay', 'Pago mediante Apple Pay', 1),
       ('Google Pay', 'Pago mediante Google Pay', 1);

-- Insertar 10 registros en la tabla `usuarios`
INSERT INTO `usuarios` (`nombre`, `email`, `password`, `telefono`, `direccion`, `tipo`, `numero_cuenta`)
VALUES ('Juan Pérez', 'juan.perez@example.com', '$2a$10$o7IfldgbgyNjBZ0Fz5BTy.cslMXh1aL1KqUIpqdMvHarqsFU3ZOqi', '1234567890', 'Calle Falsa 123', 'COMPRADOR', null),
       ('María Gómez', 'maria.gomez@example.com', '$2a$10$o7IfldgbgyNjBZ0Fz5BTy.cslMXh1aL1KqUIpqdMvHarqsFU3ZOqi', '0987654321', 'Calle Verdadera 456', 'VENDEDOR', '100000000002'),
       ('Carlos Ruiz', 'carlos.ruiz@example.com', '$2a$10$o7IfldgbgyNjBZ0Fz5BTy.cslMXh1aL1KqUIpqdMvHarqsFU3ZOqi', '1122334455', 'Avenida Siempre Viva 789', 'VENDEDOR', '100000000003'),
       ('Ana López', 'ana.lopez@example.com', '$2a$10$o7IfldgbgyNjBZ0Fz5BTy.cslMXh1aL1KqUIpqdMvHarqsFU3ZOqi', '6677889900', 'Calle Cualquiera 101', 'COMPRADOR', null),
       ('Luis Fernández', 'luis.fernandez@example.com', '$2a$10$o7IfldgbgyNjBZ0Fz5BTy.cslMXh1aL1KqUIpqdMvHarqsFU3ZOqi', '9988776655', 'Calle Larga 202', 'VENDEDOR', '100000000005');



-- Insertar categorías principales
-- Se insertan las categorías principales y se guarda su ID utilizando LAST_INSERT_ID()

-- Electrónica
INSERT INTO `categorias` (`nombre`, `descripcion`, `categoria_padre`)
VALUES ('Electrónica', 'Productos electrónicos', NULL);
SET @categoria_electronica = LAST_INSERT_ID();

-- Ropa
INSERT INTO `categorias` (`nombre`, `descripcion`, `categoria_padre`)
VALUES ('Ropa', 'Ropa y vestimenta', NULL);
SET @categoria_ropa = LAST_INSERT_ID();

-- Alimentos
INSERT INTO `categorias` (`nombre`, `descripcion`, `categoria_padre`)
VALUES ('Alimentos', 'Comida y bebidas', NULL);
SET @categoria_alimentos = LAST_INSERT_ID();

-- Hogar
INSERT INTO `categorias` (`nombre`, `descripcion`, `categoria_padre`)
VALUES ('Hogar', 'Productos para el hogar', NULL);
SET @categoria_hogar = LAST_INSERT_ID();

-- Juguetes
INSERT INTO `categorias` (`nombre`, `descripcion`, `categoria_padre`)
VALUES ('Juguetes', 'Juguetes para niños', NULL);
SET @categoria_juguetes = LAST_INSERT_ID();

-- Deportes
INSERT INTO `categorias` (`nombre`, `descripcion`, `categoria_padre`)
VALUES ('Deportes', 'Artículos deportivos', NULL);
SET @categoria_deportes = LAST_INSERT_ID();

-- Muebles
INSERT INTO `categorias` (`nombre`, `descripcion`, `categoria_padre`)
VALUES ('Muebles', 'Muebles para el hogar', NULL);
SET @categoria_muebles = LAST_INSERT_ID();

-- Belleza
INSERT INTO `categorias` (`nombre`, `descripcion`, `categoria_padre`)
VALUES ('Belleza', 'Productos de belleza', NULL);
SET @categoria_belleza = LAST_INSERT_ID();

-- Salud
INSERT INTO `categorias` (`nombre`, `descripcion`, `categoria_padre`)
VALUES ('Salud', 'Productos de salud y cuidado personal', NULL);
SET @categoria_salud = LAST_INSERT_ID();

-- Libros
INSERT INTO `categorias` (`nombre`, `descripcion`, `categoria_padre`)
VALUES ('Libros', 'Libros y material educativo', NULL);
SET @categoria_libros = LAST_INSERT_ID();

-- Insertar subcategorías para cada categoría principal usando los ID obtenidos
-- Electrónica
INSERT INTO `categorias` (`nombre`, `descripcion`, `categoria_padre`)
VALUES
    ('Smartphones', 'Teléfonos inteligentes, accesorios y gadgets móviles', @categoria_electronica),
    ('Computadoras y Laptops', 'Laptops, computadoras de escritorio y accesorios de informática', @categoria_electronica),
    ('Electrodomésticos de Cocina', 'Electrodomésticos como microondas, licuadoras, y cafetera', @categoria_electronica);

-- Ropa
INSERT INTO `categorias` (`nombre`, `descripcion`, `categoria_padre`)
VALUES
    ('Ropa de Mujer', 'Vestidos, blusas, pantalones y accesorios para mujeres', @categoria_ropa),
    ('Ropa de Hombre', 'Camisas, pantalones, chaquetas y ropa formal para hombres', @categoria_ropa),
    ('Ropa Infantil', 'Ropa para niños, incluidos conjuntos, vestidos, pantalones y prendas escolares', @categoria_ropa),
    ('Ropa Deportiva para Hombre', 'Ropa deportiva para hombres, como sudaderas, leggings, zapatillas', @categoria_ropa),
    ('Ropa Deportiva para Mujer', 'Ropa deportiva para mujeres, como leggins, tops, chaquetas deportivas', @categoria_ropa);

-- Alimentos
INSERT INTO `categorias` (`nombre`, `descripcion`, `categoria_padre`)
VALUES
    ('Frutas y Verduras', 'Frutas frescas, verduras y hortalizas orgánicas y convencionales', @categoria_alimentos),
    ('Alimentos Enlatados', 'Conservas de vegetales, sopas, pescado y carne enlatados', @categoria_alimentos),
    ('Delicatessen', 'Productos gourmet como quesos, aceites, y jamones curados', @categoria_alimentos),
    ('Panadería', 'Pan fresco, galletas, pasteles y otros productos de panadería', @categoria_alimentos),
    ('Bebidas', 'Jugos, refrescos, agua mineral y bebidas alcohólicas', @categoria_alimentos);

-- Deportes
INSERT INTO `categorias` (`nombre`, `descripcion`, `categoria_padre`)
VALUES
    ('Fútbol', 'Balones, camisetas, botas y accesorios para fútbol', @categoria_deportes),
    ('Tenis y Raquetas', 'Raquetas de tenis, pelotas y accesorios para jugadores de tenis', @categoria_deportes),
    ('Ropa Deportiva para Entrenamiento', 'Ropa y accesorios para entrenamientos y gimnasio', @categoria_deportes),
    ('Camping y Senderismo', 'Equipamiento para camping, mochilas, carpas y ropa de senderismo', @categoria_deportes);

-- Muebles
INSERT INTO `categorias` (`nombre`, `descripcion`, `categoria_padre`)
VALUES
    ('Muebles para Sala', 'Sofás, mesas de café, estanterías y muebles de TV para la sala de estar', @categoria_muebles),
    ('Muebles para Dormitorio', 'Camas, colchones, armarios y mesas de noche para el dormitorio', @categoria_muebles);

-- Belleza
INSERT INTO `categorias` (`nombre`, `descripcion`, `categoria_padre`)
VALUES
    ('Cuidado de la Piel', 'Cremas, lociones, mascarillas y productos para el cuidado de la piel', @categoria_belleza),
    ('Maquillaje', 'Cosméticos, bases, labiales, sombras y productos para maquillar', @categoria_belleza),
    ('Cuidado del Cabello', 'Shampoo, acondicionador, aceites y productos para el cuidado capilar', @categoria_belleza);

-- Salud
INSERT INTO `categorias` (`nombre`, `descripcion`, `categoria_padre`)
VALUES
    ('Suplementos Nutricionales', 'Vitaminas, minerales, proteínas y otros suplementos para la salud', @categoria_salud),
    ('Higiene Personal', 'Productos de higiene como jabones, desodorantes, y cepillos de dientes', @categoria_salud);

-- Libros
INSERT INTO `categorias` (`nombre`, `descripcion`, `categoria_padre`)
VALUES
    ('Ficción', 'Libros de ficción, novelas, literatura y obras literarias', @categoria_libros),
    ('Educación y Desarrollo Personal', 'Libros educativos, guías de estudio, autoayuda y desarrollo personal', @categoria_libros),
    ('Historia y Biografías', 'Libros sobre historia, biografías de personajes históricos y relatos de acontecimientos pasados', @categoria_libros),
    ('Ciencia y Tecnología', 'Libros sobre ciencia, avances tecnológicos, matemáticas e ingeniería', @categoria_libros);



-- Insertar 10 registros en la tabla `mensajes`
INSERT INTO `mensajes` (`comprador_id`, `vendedor_id`, `contenido`)
VALUES (1, 2, '¿Este producto está disponible?'),
       (2, 1, 'Sí, el producto está disponible.'),
       (3, 2, '¿Cuál es el precio de este producto?'),
       (2, 3, 'El precio es de $50.'),
       (1, 3, '¿Puedo pagar con tarjeta de crédito?'),
       (4, 2, 'Sí, aceptamos tarjetas de crédito.');

-- Insertar 10 registros en la tabla `ordenes`
INSERT INTO `ordenes` (`comprador_id`, `total`, `estado`)
VALUES (1, 100.00, 'PENDIENTE'),
       (2, 50.00, 'PAGADO'),
       (3, 150.00, 'ENVIADO'),
       (4, 75.00, 'ENTREGADO'),
       (5, 200.00, 'CANCELADO');

-- Insertar 10 registros en la tabla `reportes`
INSERT INTO `reportes` (`usuario_id`, `tipo`, `descripcion`, `estado`)
VALUES (1, 'PRODUCTO', 'El producto llegó dañado', 'PENDIENTE'),
       (2, 'ORDEN', 'El pedido fue cancelado sin razón', 'PENDIENTE'),
       (3, 'USUARIO', 'Usuario abusivo en los comentarios', 'PENDIENTE'),
       (4, 'PRODUCTO', 'Producto no coincide con la descripción', 'REVISADO'),
       (5, 'ORDEN', 'Retraso en la entrega', 'RESUELTO');

-- Insertar 10 registros en la tabla `productos`
-- Insertar productos y obtener sus IDs generados automáticamente
-- Se insertan los productos y se obtiene el ID generado usando LAST_INSERT_ID()

-- Producto 1: Smartphone
INSERT INTO `productos` (`vendedor_id`, `categoria_id`, `nombre`, `descripcion`, `precio`, `stock`, `estado`)
VALUES (2, 1, 'Smartphone', 'Smartphone de última generación', 300.00, 10, 'NUEVO');
SET @producto_1 = LAST_INSERT_ID();

-- Producto 2: Camisa de algodón
INSERT INTO `productos` (`vendedor_id`, `categoria_id`, `nombre`, `descripcion`, `precio`, `stock`, `estado`)
VALUES (2, 2, 'Camisa de algodón', 'Camisa de algodón para hombre', 20.00, 50, 'NUEVO');
SET @producto_2 = LAST_INSERT_ID();

-- Producto 3: Jugo de naranja
INSERT INTO `productos` (`vendedor_id`, `categoria_id`, `nombre`, `descripcion`, `precio`, `stock`, `estado`)
VALUES (3, 3, 'Jugo de naranja', 'Jugo natural de naranja', 5.00, 100, 'NUEVO');
SET @producto_3 = LAST_INSERT_ID();

-- Producto 4: Sofá de 3 plazas
INSERT INTO `productos` (`vendedor_id`, `categoria_id`, `nombre`, `descripcion`, `precio`, `stock`, `estado`)
VALUES (4, 4, 'Sofá de 3 plazas', 'Sofá cómodo para sala de estar', 250.00, 5, 'NUEVO');
SET @producto_4 = LAST_INSERT_ID();

-- Producto 5: Pelota de fútbol
INSERT INTO `productos` (`vendedor_id`, `categoria_id`, `nombre`, `descripcion`, `precio`, `stock`, `estado`)
VALUES (5, 5, 'Pelota de fútbol', 'Pelota de fútbol tamaño estándar', 15.00, 30, 'NUEVO');
SET @producto_5 = LAST_INSERT_ID();

-- Insertar 10 registros en la tabla `reseñas`
INSERT INTO `reseñas` (`producto_id`, `usuario_id`, `calificacion`, `comentario`)
VALUES (@producto_1, 1, 5, 'Excelente producto, funciona de maravilla.'),
       (@producto_2, 2, 4, 'Buena calidad, pero un poco caro.'),
       (@producto_3, 3, 5, 'Muy sabroso y refrescante, me encanta.'),
       (@producto_4, 4, 3, 'El sofá es cómodo, pero llegó un poco dañado.'),
       (@producto_5, 5, 5, 'Perfecta para jugar fútbol, la recomiendo.');

-- Insertar 10 registros en la tabla `transacciones_pago`
INSERT INTO `transacciones_pago` (`orden_id`, `metodo_pago_id`, `monto`, `moneda`, `estado`)
VALUES (1, 1, 100.00, 'CLP', 'COMPLETADO'),
       (2, 2, 50.00, 'CLP', 'COMPLETADO'),
       (3, 3, 150.00, 'CLP', 'FALLIDO'),
       (4, 4, 75.00, 'CLP', 'COMPLETADO'),
       (5, 5, 200.00, 'CLP', 'PENDIENTE');



-- Insertar imágenes para cada producto utilizando los IDs generados
INSERT INTO `imagenes_producto` (`producto_id`, `url_imagen`)
VALUES
    (@producto_1, 'https://images.unsplash.com/photo-1511707171634-5f897ff02aa9?w=800&auto=format'), -- Smartphone
    (@producto_1, 'https://images.unsplash.com/photo-1512496015851-a90fb38ba796?w=800&auto=format'), -- Smartphone
    (@producto_1, 'https://images.unsplash.com/photo-1498050108023-c5249f4df085?w=800&auto=format'), -- Smartphone
    (@producto_2, 'https://images.unsplash.com/photo-1523381210434-271e8be1f52b?w=800&auto=format'), -- Camisa de algodón
    (@producto_2, 'https://images.unsplash.com/photo-1521577352947-9bb58764b69a?w=800&auto=format'), -- Camisa de algodón
    (@producto_2, 'https://images.unsplash.com/photo-1534941848857-9a50f96b9c03?w=800&auto=format'), -- Camisa de algodón
    (@producto_3, 'https://images.unsplash.com/photo-1570197787195-8b3b9c1e1b1b?w=800&auto=format'), -- Jugo de naranja
    (@producto_3, 'https://images.unsplash.com/photo-1571115177098-5f7f60d0d5c4?w=800&auto=format'), -- Jugo de naranja
    (@producto_3, 'https://images.unsplash.com/photo-1580910051073-0c7f62b3ca9a?w=800&auto=format'), -- Jugo de naranja
    (@producto_4, 'https://images.unsplash.com/photo-1598300053650-d7e10f27c8d4?w=800&auto=format'), -- Sofá de 3 plazas
    (@producto_4, 'https://images.unsplash.com/photo-1586023492125-27b2c045efd7?w=800&auto=format'), -- Sofá de 3 plazas
    (@producto_4, 'https://images.unsplash.com/photo-1598300053650-d7e10f27c8d4?w=800&auto=format'), -- Sofá de 3 plazas
    (@producto_5, 'https://images.unsplash.com/photo-1508606572321-901ea443707f?w=800&auto=format'), -- Pelota de fútbol
    (@producto_5, 'https://images.unsplash.com/photo-1579982175379-5e1e6e1f6c3e?w=800&auto=format'), -- Pelota de fútbol
    (@producto_5, 'https://images.unsplash.com/photo-1593113598332-4fbbf0c6f01e?w=800&auto=format'); -- Pelota de fútbol

INSERT INTO `favoritos` (`usuario_id`, `producto_id`)
VALUES (1, 1),
       (2, 2),
       (3, 3),
       (4, 4),
       (5, 5);

INSERT INTO `historial_visitas` (`usuario_id`, `producto_id`)
VALUES (1, 1),
       (2, 2),
       (3, 3),
       (4, 4),
       (5, 5);

INSERT INTO `detalle_orden` (`orden_id`, `producto_id`, `cantidad`, `precio_unitario`)
VALUES (1, 1, 1, 300.00),
       (2, 2, 2, 20.00),
       (3, 3, 3, 5.00),
       (4, 4, 1, 250.00),
       (5, 5, 4, 15.00);

-- Insertar 10 registros en la tabla `envios`
INSERT INTO `envios` (`orden_id`, `direccion_envio`, `estado`)
VALUES (1, 'Calle Falsa 123, Ciudad X', 'PREPARANDO'),
       (2, 'Calle Verdadera 456, Ciudad Y', 'ENVIADO'),
       (3, 'Avenida Siempre Viva 789, Ciudad Z', 'EN REPARTO'),
       (4, 'Calle Cualquiera 101, Ciudad A', 'ENTREGADO'),
       (5, 'Calle Larga 202, Ciudad B', 'PREPARANDO');

INSERT INTO `carritos` (`usuario_id`, `fecha_creacion`)
VALUES
    (1, NOW()),
    (2, NOW()),
    (3, NOW()),
    (4, NOW()),
    (5, NOW());

INSERT INTO `carrito_detalle` (`carrito_id`, `producto_id`, `cantidad`)
VALUES
    (1, 1, 2),
    (2, 3, 5),
    (3, 2, 1),
    (4, 5, 3);
