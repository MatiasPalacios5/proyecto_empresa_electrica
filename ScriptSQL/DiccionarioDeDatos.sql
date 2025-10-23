-- create database empresa_electrica;
-- use empresa_electrica;


create table if not exists usuario (
    id_usuario int not null auto_increment, -- identificador de usuario, clave primaria 
    direccion varchar(40) not null, 
    telefono varchar(40) not null, 
    constraint pk_usuario primary key (id_usuario)
);

create table if not exists empresa (
    id_usuario int not null, -- clave primaria 
    cuit varchar(15) not null,  -- cuit de la empresa, debe ser unico
    cap_kw decimal(10,2) not null, -- capacidad instalada (debe estar entre 0 y 50000)

    constraint pk_empresa primary key (id_usuario),  -- contrato pk_empresa clave primaria 
    constraint fk_empresa_usuario foreign key (id_usuario)
		references usuario (id_usuario) -- contrato clave foranea fk_empresa_usuario 
        on delete cascade -- si se borra usuario se borran registros relacionados
		on update cascade, -- cualquier cambio en id_usuario en la tabla usuario, actualiza el cambio aca
    constraint chk_cap_kw check (cap_kw >= 0 and cap_kw <= 50000), -- cap_kw puede tomar un valor entre ese rango
    constraint uk_cuit unique (cuit) -- ponemos que el atributo cuit es unico
);

create table if not exists persona (
    id_usuario int not null, -- clave primaria
    dni int not null, -- atributo unico
    nombre varchar(40) not null,
    apellido varchar(40) not null,

    constraint pk_persona primary key (id_usuario), -- contrato pk_persona clave primaria 
    constraint fk_persona_usuario foreign key (id_usuario) 
		references usuario(id_usuario) -- vincula cada persona con un usuario existente 
        on delete cascade -- si se elimina un usuario, se elimina la persona asociada 
		on update cascade, -- si se cambia un usuario, el cambio se transmite automaticamente aca
    constraint dni_persona check (dni > 0 and dni < 1000000000), -- un dni debe tener mas de cero numeros y menos de mil millones
    constraint uk_dni unique (dni) -- el dni de la persona debe tener un valor unico 
);

create table if not exists empleado (
    id_usuario int not null, -- clave primaria
    sueldo decimal(10,2) not null, 
    constraint pk_empleado primary key (id_usuario), 
    constraint fk_empleado_persona foreign key (id_usuario) 
        references persona(id_usuario) -- vincula cada empleado con un usuario existente
        on delete cascade -- si se borra un usuario, se borran registros relacionados
        on update cascade, -- si se cambia el id_usuario en la tabla usuario, el cambio se extiende automaticamente 
	constraint chk_sueldo_positivo check (sueldo > 0)   -- el sueldo no puede ser negativo ni 0
); 

create table if not exists motivo (
    codigo_mot int not null auto_increment, -- identificador unico de motivo (clave primaria)
    descripcion_mot varchar(50) not null,
    constraint pk_codigo_mot primary key(codigo_mot) -- contrato pk_codigo_mot clave primaria 
);


create table if not exists reclamo (
    numero_reclamo int not null auto_increment, -- clave primaria de reclamo
    fecha_resolucion date, 
    id_usuario int not null, 
    codigo_mot int not null, 
    fecha_reclamo date not null,
    hora_reclamo time not null,

    constraint pk_reclamo primary key (numero_reclamo), -- identifica de forma unica cada reclamo
    constraint fk_reclamo_us foreign key (id_usuario) 
	references usuario(id_usuario) -- vincula cada reclamo con el usuario correspondiente
        on delete cascade  -- Cuando se elimina un usuario a la informacion de los reclamos asociados, se elimina
		on update cascade, -- Si cambio un id_usuario, se actualiza 
    constraint fk_reclamo_cod foreign key(codigo_mot) 
		references motivo(codigo_mot) -- clave foranea que vinculq cada reclamo con un motivo
        on delete restrict -- protege de borrar info importante como el motivo
		on update cascade -- si cambio un codigo_mot, se actualiza
);

create table if not exists material (
    codigo_mat int not null auto_increment, -- clave primaria
    descripcion_mat varchar(50) not null,
    constraint pk_codigo_mat primary key(codigo_mat) -- identifica de forma unica cada material
);

create table if not exists rellamado (
    numero_reclamo int not null, 
    numero_llamado int not null,
    fecha date not null, -- fecha del rellamado
    hora time not null, -- hora del rellamado
	-- clave primaria compuesta; garantiza que no haya mas de un rellamado con el mismo numero para un mismo reclamo
    constraint pk_rellamado primary key (numero_reclamo, numero_llamado), 
    constraint fk_rellamado_reclamo foreign key (numero_reclamo) 
        references reclamo(numero_reclamo) -- vincula cada rellamado con el reclamo correspondiente 
        on delete cascade -- si se borra un reclamo se borran registros relacionados
        on update cascade -- si se cambia un numero_reclamo de la tabla usuario, se actualiza automaticamente aca
);

create table if not exists deriva (
    numero_reclamo int not null, 
    id_usuario int not null,
	-- clave primaria compuesta que garantiza que no haya mas de un derivacion con el mismo usuario para un mismo reclamo
    constraint pk_deriva primary key (numero_reclamo, id_usuario),  
    constraint fk_deriva_reclamo foreign key (numero_reclamo) 
        references reclamo(numero_reclamo) -- clave foranea que relaciona cada derivacion con su reclamo
        on delete cascade -- si se elimina un reclamo, se elimina la derivacion asociada
        on update cascade, -- si se cambia un reclamo, la informacion se transmite automaticamente
    constraint fk_deriva_empleado foreign key (id_usuario) 
        references empleado(id_usuario) -- se relaciona cada derivacion a un empleado correspondiete
        on delete cascade -- si se borra un empleado/usuario, se borran registros relacionados
        on update cascade -- si se cambia alguno de las anteriores, el cambio se extiende a aca
);

create table if not exists utiliza (
    numero_reclamo int not null,
    codigo_mat int not null,
    cantidad int not null, -- cantidad de material utilizado (mayor a 0)
    -- Se usa clave primaria compuesta (numero_reclamo, codigo) 
    -- para permitir varios materiales por reclamo.
    constraint pk_utiliza primary key (numero_reclamo, codigo_mat),
    constraint fk_utiliza_reclamo foreign key (numero_reclamo)
        references reclamo(numero_reclamo) 
        on delete cascade -- si se borra un reclamo, se borran registros relacionados
        on update cascade, -- se se cambia un numero_reclamo en la tabla reclamo, el cambio se transmite automaticamente
    constraint fk_utiliza_material foreign key (codigo_mat)
        references material(codigo_mat) 
		on delete restrict
        on update cascade,
	constraint chk_cantidad_positiva check (cantidad > 0) -- la cantidad de material utilizado debe ser mayor 0
);

-- TABLA AUDITORIA

create table if not exists auditoria_reclamo (
    id_auditoria int not null auto_increment, 
    numero_reclamo int not null,
    fecha_eliminacion date not null,
    usuario_bd varchar(40) not null,
    constraint pk_auditoria_reclamo primary key (id_auditoria) -- identificador unico para cada registro de auditoria
);

-- TRIGGER PARA LA ELIMINACION DE RECLAMOS
create trigger reclamos_eliminado 
	after delete on reclamo
	for each row 
	insert into auditoria_reclamo (numero_reclamo, fecha_eliminacion, usuario_bd)
	values (old.numero_reclamo, curdate(), user()) 	-- OLD fila recien borrada 
													-- curdate() devuelve fecha actual
													-- user() obtiene el usuario de la bdd 
