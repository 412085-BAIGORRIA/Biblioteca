INSERT INTO libros (id, titulo, autor, isbn, categoria, disponible)
VALUES (1, 'Cien años de soledad', 'Gabriel García Márquez', '978-84-376-0494-7', 'Literatura', true),
       (2, 'El principito', 'Antoine de Saint-Exupéry', '978-84-261-4059-7', 'Infantil', true),
       (3, 'Don Quijote de la Mancha', 'Miguel de Cervantes', '978-84-376-0815-0', 'Clásicos', false),
       (4, 'Fundación', 'Isaac Asimov', '978-84-450-7514-4', 'Ciencia Ficción', true);

INSERT INTO usuarios (id, nombre_completo, email, telefono)
VALUES (1, 'Ana Rodríguez', 'ana.rodriguez@email.com', '555-0101'),
       (2, 'Carlos Mendoza', 'carlos.mendoza@email.com', '555-0102'),
       (3, 'María Fernández', 'maria.fernandez@email.com', '555-0103'),
       (4, 'Pedro Sánchez', 'pedro.sanchez@email.com', '555-0104');

INSERT INTO prestamos (id, fecha_prestamo, fecha_devolucion, estado, usuario_id, libro_id)
VALUES (100, '2024-01-15', '2024-01-29', 'DEVUELTO', 1, 1),
       (101, '2024-02-01', NULL, 'ACTIVO', 2, 3),
       (102, '2024-02-10', '2024-02-24', 'DEVUELTO', 3, 2),
       (103, '2024-02-20', NULL, 'VENCIDO', 4, 4);