-- a) Devolver por cada reclamo, el detalle de materiales utilizados para
-- solucionarlo, si un reclamo no uso materiales, listarlo también.
select r.numero_reclamo, u.codigo_mat, u.cantidad, m.descripcion_mat from reclamo r
left join utiliza u
on r.numero_reclamo = u.numero_reclamo
left join material m
on u.codigo_mat = m.codigo_mat;

-- b) Devolver los usuarios que tienen más de un reclamo.
select id_usuario, count(numero_reclamo) as cantidad_reclamos from reclamo
group by id_usuario
having count(numero_reclamo) > 1;

-- c) Listado de reclamos que fueron asignados a más de un empleado de
-- mantenimiento.
select numero_reclamo, count(id_usuario) as cantidad_empleados from deriva
group by numero_reclamo
having count(id_usuario) > 1;