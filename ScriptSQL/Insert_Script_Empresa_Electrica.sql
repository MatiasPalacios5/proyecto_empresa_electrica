-- Script de carga de datos a empresa eléctrica para administrar informacion de reclamos.
use empresa_electrica;

-- Insertamos datos en la tabla Usuarios 
insert into usuario (id_usuario, direccion, telefono) values 
    (1, 'San Martin 1100', '3584555999'),
    (2, 'Belgrano 200', '3584222333'),
    (3, 'Constitucion 500', '3585444777'),
    (4, 'Sobremonte 756', '3583659148'),
    (5, 'Cabrera 950', '3585698530'),
    (6, 'Rivadavia 333', '3584888999'),
    (7, 'Maipu 445', '3584123456'),
    (8, '9 de Julio 890', '3585987654');

-- Insertamos datos en la tabla Empresas (Son Usuarios con Cuit y Capacidad Kw instalada)
insert into empresa (id_usuario, cuit, cap_kw) values
    (1, '30-12345678-9', 28500.00),
    (5, '15-87654321-0', 42000.00);

-- Insertamos datos en la tabla Personas (Son Usuarios con DNI, Nombre y Apellido)
insert into persona (id_usuario, dni, nombre, apellido) values
    (2, 34555888, 'Ana', 'Martinez'),
    (3, 29777333, 'Juan', 'Perez'),
    (4, 30111222, 'Carlos', 'Gomez'),
    (6, 28999444, 'Maria', 'Lopez'),
    (7, 35222111, 'Pedro', 'Rodriguez'),
    (8, 32444555, 'Laura', 'Fernandez');

-- Insertamos datos en la tabla Empleados (Son Usuarios Persona que pueden ser Empleados con su Sueldo) 
insert into empleado (id_usuario, sueldo) values
    (2, 450000.00),
    (4, 380000.00),
    (7, 420000.00);

-- Insertamos datos en la tabla Motivos
insert into motivo (codigo_mot, descripcion_mot) values
    (1, 'Corte de Suministro Electrico'),
    (2, 'Cable Cortado o Dañado'),
    (3, 'Poste de Luz Caido'),
    (4, 'Falta de Iluminacion Publica'),
    (5, 'Chispas en Instalacion');

-- Insertamos datos en la tabla Materiales
insert into material (codigo_mat, descripcion_mat) values
    (1, 'Cable de cobre #10'),
    (2, 'Fusibles de 20A'),
    (3, 'Cinta aisladora'),
    (4, 'Poste de hormigon 9m'),
    (5, 'Lampara LED 50W');

-- Insertamos datos en la tabla Reclamos (NULL significa que aun no se resolvieron)
insert into reclamo (numero_reclamo, fecha_resolucion, id_usuario, codigo_mot, fecha_reclamo, hora_reclamo) values
    (1, '2025-05-12', 1, 1, '2025-05-05', '08:00:00'),
    (2, '2025-07-20', 3, 4, '2025-07-15', '17:30:17'),
    (3, '2025-08-04', 5, 2, '2025-07-30', '12:06:49'),
    (4, null, 1, 3, '2025-10-01', '09:15:00'),
    (5, '2025-10-10', 3, 5, '2025-10-05', '14:20:00'),
    (6, null, 6, 1, '2025-10-15', '16:45:00'),
    (7, '2025-06-18', 8, 3, '2025-06-10', '11:30:00'),
    (8, null, 1, 2, '2025-10-18', '13:25:00'),
    (9, '2025-09-22', 3, 4, '2025-09-15', '10:00:00'),
    (10, null, 5, 1, '2025-10-20', '15:40:00'),
    (11, '2025-08-30', 4, 2, '2025-08-20', '09:10:00'),
    (12, null, 1, 5, '2025-10-21', '12:30:00');

-- Insertamos datos en la tabala Rellamados 
insert into rellamado (numero_reclamo, numero_llamado, fecha, hora) values 
    (1, 1, '2025-05-06', '08:10:19'),
    (1, 2, '2025-05-10', '09:30:22'),
    (2, 1, '2025-07-18', '10:00:00'),
    (3, 1, '2025-08-02', '18:37:34'),
    (4, 1, '2025-10-05', '11:20:00'),
    (4, 2, '2025-10-10', '15:30:00'),
    (4, 3, '2025-10-18', '09:45:00'),
    (5, 1, '2025-10-08', '16:00:00'),
    (6, 1, '2025-10-16', '10:30:00'),
    (9, 1, '2025-09-17', '11:15:00'),
    (11, 1, '2025-08-22', '13:40:00'),
    (12, 1, '2025-10-22', '08:15:00');

-- Insertamos datos en la tabla Deriva (Un reclamo puede tener varios empleados derivados a trabajar en el)
insert into deriva (numero_reclamo, id_usuario) values
    (1, 2),
    (1, 4),
    (2, 2),
    (3, 7),
    (4, 2),
    (4, 7),
    (5, 4),
    (6, 2),
    (7, 7),
    (8, 4),
    (9, 2),
    (9, 7),
    (10, 4),
    (11, 7),
    (12, 2);

-- Insertamos datos en la tabla Utiliza (No todos los reclamos usan materiales)
insert into utiliza (numero_reclamo, codigo_mat, cantidad) values
    (1, 1, 10),
    (1, 3, 2),
    (2, 4, 2),
    (3, 1, 5),
    (3, 2, 8),
    (5, 2, 5),
    (7, 5, 3),
    (9, 1, 8),
    (9, 3, 2),
    (11, 1, 12);