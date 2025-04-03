/*Se crea la base de datos */
drop schema if exists proyecto;
drop user if exists usuario_admin;
CREATE SCHEMA proyecto;

/*Se crea un usuario para la base de datos llamado y la contraseña*/
create user 'usuario_admin'@'%' identified by 'Pr0y3ct0-1';

/*Se asignan los prvilegios sobre la base de datos al usuario creado */
grant all privileges on proyecto.* to 'usuario_admin'@'%';
flush privileges;

use proyecto;

/*Se crea la tabla de Usuarios*/
CREATE TABLE usuario (
  id_usuario INT NOT NULL AUTO_INCREMENT,
  cedula INT NOT NULL,
  username varchar(20) NOT NULL,
  password varchar(512) NOT NULL,
  nombre VARCHAR(20) NOT NULL,
  apellidos VARCHAR(30) NOT NULL,
  direccion varchar(100),
  correo VARCHAR(75) NULL,
  telefono VARCHAR(15) NULL,
  activo boolean,
  PRIMARY KEY (`id_usuario`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

/*Se insertan valores en la tabla de Usuarios*/
INSERT INTO usuario (cedula, username, nombre, apellidos, direccion, correo, telefono, password, activo) VALUES 
(108560789, 'kendall', 'Kendall', 'Gamboa', 'San Jose', 'kendallgamboa4@gmail.com', '65478921', 'r8yZPX4YxvyK5DB5GaHGFfLmMFUITLQr/d/8e7Vg4ZQ',1),
(107560084, 'alexander', 'Alexander', 'Montero', 'San Pedro', 'alexandermontero73@gmail.com', '96547832', 'r8yZPX4YxvyK5DB5GaHGFfLmMFUITLQr/d/8e7Vg4ZQ',1);

/*Se crea la tabla de Usuarios*/
create table rol (
  id_rol INT NOT NULL AUTO_INCREMENT,
  nombre varchar(20),
  id_usuario int,
  PRIMARY KEY (id_rol),
  foreign key fk_rol_usuario (id_usuario) references usuario(id_usuario)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

/*Se insertan valores en la tabla de Usuarios*/
insert into rol (id_rol, nombre, id_usuario) values
 (1,'ROLE_ADMIN', 2), (2,'ROLE_USER',2),
 (7,'ROLE_ADMIN', 6), (8,'ROLE_USER',6),
 (9,'ROLE_ADMIN', 7), (10,'ROLE_USER',7);
 
/*Se crea la tabla de Categorias*/
create table categoria (
  id_categoria INT NOT NULL AUTO_INCREMENT,
  descripcion VARCHAR(30) NOT NULL,
  activo bool,
  PRIMARY KEY (id_categoria))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

/*Se crea la tabla de Productos*/
create table producto (
  id_producto INT NOT NULL AUTO_INCREMENT,
  id_categoria INT NOT NULL,
  descripcion VARCHAR(30) NOT NULL,  /*Nombre*/
  detalle VARCHAR(1600) NOT NULL, /*Descripcion*/
  precio double,
  disponibles int,  
  ruta_imagen varchar(1024),
  activo bool,
  PRIMARY KEY (id_producto),
  foreign key fk_producto_caregoria (id_categoria) references categoria(id_categoria)  
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;
 
use proyecto;
select * FROM usuario;
select * FROM rol;
 
drop table rol;
drop table usuario;