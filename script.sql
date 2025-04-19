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
 (11,'ROLE_ADMIN', 2), (12,'ROLE_USER',2),
 (7,'ROLE_ADMIN', 6), (8,'ROLE_USER',6),
 (9,'ROLE_ADMIN', 7), (10,'ROLE_USER',7);

/*Se crea la tabla de Productos*/
create table producto (
  id_producto INT NOT NULL AUTO_INCREMENT,
  descripcion VARCHAR(30) NOT NULL,  /*Nombre*/
  detalle VARCHAR(1600) NOT NULL, /*Descripcion*/
  precio double,
  disponibles int,  
  ruta_imagen varchar(1024),
  activo bool,
  PRIMARY KEY (id_producto)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

/*inserts a productos - pasteles*/
insert into producto (id_producto, descripcion, detalle, precio, disponibles, ruta_imagen, activo) values
 (1, 'Pastel de Zanahoria', 'Rebanada de pastel de zanahoria', 8, 12, 'https://drive.google.com/file/d/16_3GlQpqBBGmt3i95dpm8nTRttPAnKbL', 1),
 (2, 'Pastel de Chocolate', 'Rebanada de pastel de chocolate semiamargo', 9, 15, 'https://drive.google.com/file/d/1eNwyFNkyyiHVkGDI0Mm3HZhMv9fpHH4Q', 1),
 (3, 'Pastel de Fresa', 'Pastel de fresa con crema chantilly', 8, 10, 'https://drive.google.com/file/d/1HHylwVraOZfXyR5fkLdI-UVfEDV10dXo', 1),	
 (4, 'Pastel de Limón', 'Pastel de limón con cubierta de merengue', 9.5, 8, 'https://drive.google.com/file/d/1zP1_uKHerjijqVqvg5EoHR0FiKRjr5es', 1),
 (5, 'Pastel Red Velvet', 'Pastel Red Velvet con betún de queso crema', 10, 14, 'https://drive.google.com/file/d/137K7EdVLRWZ8CBNK5a9pxGEmCBVKa8CV', 1),
 (6, 'Pastel de Tres Leches', 'Rebanada de pastel de tres leches con cereza', 9, 9, 'https://drive.google.com/file/d/118aIjeGntYdI76wvYorF-BfdUejC4z9M', 1);

/*inserts a productos - donas*/
insert into producto (id_producto, descripcion, detalle, precio, disponibles, ruta_imagen, activo) values
 (7, 'Dona Glaseada', 'Dona clásica con glaseado de azúcar', 2.5, 20, 'https://drive.google.com/file/d/1hk63buQXzv86EFXAykClu6_XwBegQoJ-', 1),
 (8, 'Dona de Chocolate', 'Dona cubierta con chocolate oscuro', 3, 18, 'https://drive.google.com/file/d/11bpq5qqRfXsvemCyX8Ho1sVq-_8k90tZ', 1),
 (9, 'Dona Rellena de Crema', 'Dona con relleno de crema pastelera', 3.5, 16, 'https://drive.google.com/file/d/1MhiY-6vujqjZAnk5yLyGK84b76lJaP86', 1),
 (10, 'Dona con Chispas', 'Dona glaseada con chispas de colores', 2.75, 22, 'https://drive.google.com/file/d/19AlfNyRCv1WAOJFQia-mvgDVAXwtlpK3', 1),
 (11, 'Dona de Canela', 'Dona espolvoreada con azúcar y canela', 2, 25, 'https://drive.google.com/file/d/1vyDAaLaGUbEj6NFY6VecmG0UgeuwlqxN', 1),
 (12, 'Dona Rellena de Fresa', 'Dona rellena con mermelada de fresa', 3.75, 14, 'https://drive.google.com/file/d/16ONHNvE_R5bc1oGs1YjsWcJ20sk0Zmhp', 1);

/*inserts a productos - galletas*/
insert into producto (id_producto, descripcion, detalle, precio, disponibles, ruta_imagen, activo) values
 (13, 'Galleta Red Velvet', 'Galleta suave de red velvet con chispas de chocolate', 3, 18, 'https://drive.google.com/file/d/1T8jVr8OVMdL6JUyX5RLyIK4Cfs-aHmC4', 1),
 (14, 'Galleta de Avena', 'Galleta de avena con pasas y nuez', 2.5, 20, 'https://drive.google.com/file/d/1Vk9zy-QnNyuEh5PVXqIatxhBDfysFGmT', 1),
 (15, 'Galleta de Chocolate', 'Galleta clásica con trozos de chocolate', 2.75, 22, 'https://drive.google.com/file/d/1h4ogemTZkZm_h2ZqKEfDvEiZRso9d_2p', 1),
 (16, 'Galleta de Mantequilla', 'Galleta crujiente de mantequilla con un toque de vainilla', 2, 25, 'https://drive.google.com/file/d/1TuT9A3ZQUaHKl3BzMh8UzqkF1yWFShBm', 1),
 (17, 'Galleta de Limón', 'Galleta suave con glaseado de limón', 2.5, 15, 'https://drive.google.com/file/d/1512K1qrkT1Mn0MpnrcNVcHpSf4_ALmkA', 1),
 (18, 'Galleta de Coco', 'Galleta con trozos de coco rallado', 2.25, 19, 'https://drive.google.com/file/d/10dH70dcLTfcCQz75iwQscn5BZWV-lV2F', 1);

/*inserts a productos - reposteria salada*/
insert into producto (id_producto, descripcion, detalle, precio, disponibles, ruta_imagen, activo) values
 (19, 'Croissant', 'Crujiente croissant', 3, 15, 'https://drive.google.com/file/d/1uGMKFO4Hgk7NTLksBBMmBldnE1aAWIkn', 1),
 (20, 'Empanada de Pollo', 'Empanada horneada rellena de pollo y especias', 2.5, 18, 'https://drive.google.com/file/d/1kl7q7zmK9V5poyD3S5S_Idbeu_Uqtml3', 1),
 (21, 'Quiche de Espinaca', 'Mini quiche con espinaca, queso y huevo', 2.75, 12, 'https://drive.google.com/file/d/1PXAFS9GxshpdbIVsSv4DloOtqQvi1Eyk', 1),
 (22, 'Panecillo de Queso', 'Panecillo salado con costra de queso gratinado', 2, 20, 'https://drive.google.com/file/d/1Wn3ySSEPc0ATXFdBuFOTu--Zvytv-9N3', 1),
 (23, 'Rollito de Salchicha', 'Rollito de masa hojaldrada con salchicha', 2.25, 17, 'https://drive.google.com/file/d/17M7ar6geL3D7ftmvnaPqiDO3K_TE6N0C', 1),
 (24, 'Mini Pizza', 'Mini pizza suprema', 2.5, 14, 'https://drive.google.com/file/d/1fGArj68gILkPZ7rVJ6ObtVNm4iPaTxNO', 1);

/*inserts a productos - reposteria dulce*/
insert into producto (id_producto, descripcion, detalle, precio, disponibles, ruta_imagen, activo) values
 (25, 'Alfajor', 'Alfajor pequeño', 2.75, 16, 'https://drive.google.com/file/d/1Lo3QZOuM6s-1inyiCGmLYt4yeRAkQJQO', 1),
 (26, 'Cupcake de Vainilla', 'Cupcake suave de vainilla', 2.5, 18, 'https://drive.google.com/file/d/1hmoSDox7p_puA90wzeE9zgSteGX3OndP', 1),
 (27, 'Brownie', 'Brownie de chocolate oscuro con nueces', 3, 14, 'https://drive.google.com/file/d/1JhgIfhjLkbEVJ98vtb9ILxm-bkepS2DH', 1),
 (28, 'Concha de Vainilla', 'Pan dulce tradicional con cubierta de vainilla', 2, 22, 'https://drive.google.com/file/d/1ltwFIsFT0B6Ys2tyQvZBWuANp5MD7LZ0', 1),
 (29, 'Pan de Elote', 'Rebanada de pan de elote suave y dulce', 2.25, 20, 'https://drive.google.com/file/d/18fSPI_3xBoS9KfPhApka277E620etXKu', 1),
 (30, 'Muffin de Arándanos', 'Muffin esponjoso con arándanos naturales', 2.75, 17, 'https://drive.google.com/file/d/1P8175qQ853pwQX7N7QjU7XdqpIzNjCs6', 1);

/*inserts a productos - emparedados*/
insert into producto (id_producto, descripcion, detalle, precio, disponibles, ruta_imagen, activo) values
 (31, 'Emparedado Clásico', 'Pan artesanal con jamón, queso, lechuga y tomate', 6.5, 12, 'https://drive.google.com/file/d/1kW-Uj_egWdgkclv-TbmAUTBR5smA2Jay', 1),
 (32, 'Emparedado de Pollo', 'Emparedado con pechuga de pollo a la plancha y aderezo', 7.5, 10, 'https://drive.google.com/file/d/1qFNgKPNF-3dQUjL3MostCf-pYnPEKE0m', 1),
 (33, 'Emparedado Vegetariano', 'Pan integral con hummus, aguacate, espinacas y zanahoria', 6, 8, 'https://drive.google.com/file/d/1UDKWWl5oiENx3OAE1sVtbIZnJYos1w2N/view?usp=drive_link', 1),
 (34, 'Emparedado de Roast Beef', 'Roast beef con cebolla caramelizada y queso suizo', 9.5, 6, 'https://drive.google.com/file/d/1MTGZO_s3YXJE9FqmY6ZY-dTNI42w8K5r', 1),
 (35, 'Emparedado BBQ', 'Cerdo desmenuzado con salsa BBQ ', 8.25, 7, 'https://drive.google.com/file/d/1SOvzuO4TGZanVn7ogh0YTLu1dKyCMr-K', 1),
 (36, 'Emparedado de Atún', 'Emparedado frío con mezcla cremosa de atún y verduras', 5.5, 9, 'https://drive.google.com/file/d/1lg4gFpTs4_moVp40zMH5AS-xMaORjOoz', 1);
 
use proyecto;
select * FROM usuario;
select * FROM rol;

select * from producto;
 
drop table rol;
drop table usuario;
drop table producto;