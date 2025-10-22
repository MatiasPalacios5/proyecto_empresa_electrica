-- Script
-- Realiza la limpieza de tablas antes de insertar

set foreign_key_checks = 0; -- Desactivamos las fk temporalmente para evitar errores.
set sql_safe_updates = 0; 	-- Desactivamos el modo seguro temporalmente para poder eliminar datos. 

delete from utiliza;
delete from deriva;
delete from rellamado;
delete from reclamo;
delete from material;
delete from motivo;
delete from empleado;
delete from persona;
delete from empresa;
delete from usuario;
delete from auditoria_reclamo;

set foreign_key_checks = 1;
set sql_safe_updates = 1;