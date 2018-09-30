insert into entidad (id, activa, code, cuit, direccion, email, nombre, telefono)
VALUES
(1, 1 ,'fiuba' ,'30-54666656-1' ,'Av Paseo Colon 850' ,'personal@fi.uba.ar' ,'FIUBA' ,'43430968'),
(2, 1 ,'uade' ,'30-54666656-2' ,'Independencia 199' ,'personal@uade.ar' ,'UADE' ,'43430969'),
(3, 1 ,'unqui' ,'30-54666656-3' ,'Av. Quilmes 800' ,'personal@unqui.ar' ,'UNQUI' ,'43430963');

insert into idioma (id, code, nombre)
VALUES
(1, 'spanish' ,'Español'),
(2, 'english' ,'Ingles'),
(3, 'portuguese' ,'Portugues');

insert into nivel (id, code, nombre)
VALUES
(1, 'initial', 'Inicial'),
(2, 'intermediate', 'Intermedio'),
(3, 'advanced', 'Avanzado');


SET @images_path := '/var/lib/mysql-files/';
insert into file (id, data, extension)
VALUES
(1, LOAD_FILE(CONCAT(@images_path, 'user-profile.jpg')), 'jpg'),
(2, LOAD_FILE(CONCAT(@images_path, 'subjects/biology.jpg')), 'jpg'),
(3, LOAD_FILE(CONCAT(@images_path, 'subjects/computer.jpg')), 'jpg'),
(4, LOAD_FILE(CONCAT(@images_path, 'subjects/economy.jpg')), 'jpg'),
(5, LOAD_FILE(CONCAT(@images_path, 'subjects/engineering.jpg')), 'jpg'),
(6, LOAD_FILE(CONCAT(@images_path, 'subjects/finance.jpg')), 'jpg'),
(7, LOAD_FILE(CONCAT(@images_path, 'subjects/history.jpg')), 'jpg'),
(8, LOAD_FILE(CONCAT(@images_path, 'subjects/language.jpg')), 'jpg'),
(9, LOAD_FILE(CONCAT(@images_path, 'subjects/management.jpg')), 'jpg');

insert into materia (id, code, destacada, foto_id, nombre)
VALUES
(1, 'biology', 1, 2, 'Biologia'),
(2, 'computer', 1, 3, 'Computacion'),
(3, 'economy', 1, 4, 'Economia'),
(4, 'engineering', 1, 5, 'Ingenieria'),
(5, 'finance', 1, 6, 'Finanzas'),
(6, 'history', 1, 7, 'Historia'),
(7, 'language', 1, 8, 'Idiomas'),
(8, 'management', 1, 9, 'Management');

--password: MTIz: 123
insert into alumno (username, nombre, password, dni, email, fechaNac, presentacion, foto)
VALUES
('user', 'Usuario 1', 'MTIz', 32000111, 'user@user.com', STR_TO_DATE('1-01-1980', '%d-%m-%Y'),  'hola mundooooo', 1),
('juan', 'Juan Perez', 'MTIz', 33000111, 'juan@user.com', STR_TO_DATE('1-01-1990', '%d-%m-%Y'),  'hola mundooooo', NULL),
('pedro', 'Pedro Batagglia', 'MTIz', 34000111, 'pedro@user.com', STR_TO_DATE('1-01-1997', '%d-%m-%Y'),  'hola mundooooo', NULL);



insert into documento (nombre, descripcion, eliminado, extension, fechaCreacion, fechaUltModificacion, publico, alumno_id, entidad_id, idioma_id, materia_id, nivel_id)
  VALUES
  ('Algoritmos y programación 1', 'Usted aprenderá las nociones básicas de la programación estructurada', 0, 'jpg', STR_TO_DATE('1-01-2018', '%d-%m-%Y'), STR_TO_DATE('1-01-2018', '%d-%m-%Y'), 1, 1, 1, 1, 2, 1),
  ('Reglas de Oferta y Demanda', 'Nociones básicas de las reglas del mercado.', 0, 'jpg', STR_TO_DATE('10-7-2018', '%d-%m-%Y'), STR_TO_DATE('10-08-2018', '%d-%m-%Y'), 1, 2, 2, 1, 3, 1),
  ('Análisis de variable compleja', 'Análisis de variable compleja', 0, 'jpg', STR_TO_DATE('1-01-2018', '%d-%m-%Y'), STR_TO_DATE('1-01-2018', '%d-%m-%Y'), 1, 1, 1, 1, 4, 3),
  ('Física 2', 'Resumen de la materia Física 2 FIUBA', 0, 'jpg', STR_TO_DATE('10-01-2018', '%d-%m-%Y'), STR_TO_DATE('10-01-2018', '%d-%m-%Y'), 1, 1, 1, 1, 4, 2),
  ('Biología General', 'Conocimientos básicos de biología', 0, 'jpg', STR_TO_DATE('23-08-2018', '%d-%m-%Y'), STR_TO_DATE('23-08-2018', '%d-%m-%Y'), 1, 3, 3, 1, 1, 1),
  ('English for dummies', 'English por beginners.', 0, 'jpg', STR_TO_DATE('1-09-2018', '%d-%m-%Y'), STR_TO_DATE('1-09-2018', '%d-%m-%Y'), 1, 3, 3, 2, 7, 1),
  ('Finanzas personales', 'Como manejar las finanzas personales.', 0, 'jpg', STR_TO_DATE('1-01-2018', '%d-%m-%Y'), STR_TO_DATE('1-01-2018', '%d-%m-%Y'), 1, 3, 3, 1, 5, 1),
  ('Management de Pymes', 'Técnicas para el manejo de pequeñas y medianas empresas.', 0, 'jpg', STR_TO_DATE('18-01-2018', '%d-%m-%Y'), STR_TO_DATE('18-01-2018', '%d-%m-%Y'), 1, 2, 2, 1, 8, 2),
  ('Historia Argentina', 'Historia argentina contemporanea', 0, 'jpg', STR_TO_DATE('25-06-2018', '%d-%m-%Y'), STR_TO_DATE('25-06-2018', '%d-%m-%Y'), 1, 3, 3, 1, 6, 1);

