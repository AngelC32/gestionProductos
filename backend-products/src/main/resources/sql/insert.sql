INSERT INTO categoria (nombre, descripcion) VALUES
('Electrónica', 'Dispositivos electrónicos y gadgets'),
('Hogar', 'Productos para el hogar y la cocina'),
('Ropa', 'Vestimenta para hombres, mujeres y niños'),
('Juguetes', 'Juguetes para todas las edades'),
('Libros', 'Libros de diversos géneros y autores');

INSERT INTO producto (nombre, precio, stock, foto, id_categoria, fec_creacion) VALUES
('Smartphone X', '699.99', '25', '', 1, NOW()),
('Televisor 4K', '1299.50', '10', '', 1, NOW()),
('Licuadora', '89.99', '40', '', 2, NOW()),
('Set de sábanas', '45.00', '60', '', 2, NOW()),
('Camisa de algodón', '29.95', '100', '', 3, NOW()),
('Pantalón jeans', '49.99', '80', '', 3, NOW()),
('Muñeca interactiva', '35.50', '70', '', 4, NOW()),
('Lego Star Wars', '120.00', '15', '', 4, NOW()),
('Cien años de soledad', '19.90', '200', '', 5, NOW()),
('El principito', '14.95', '150', '', 5, NOW());