insert into entidad (id, activa, code, cuit, direccion, email, nombre, nombreCorto, descripcion, telefono)
VALUES
(1, 1 ,'fiuba' ,'30-54666656-1' ,'Av Paseo Colon 850 - Capital Federal - Argentina' ,'seube@fi.uba.ar','Facultad de Ingenieria de la UBA' , 'FIUBA', 'La FIUBA es la Facultad de Ingeniería que forma parte de la Universidad de Buenos Aires, en la actualidad compuesta por otras trece facultades. Se dictan en ella 11 carreras de grado y numerosos cursos de postgrado.', '011 4343-0968'),
(2, 1 ,'uade' ,'30-54666656-2' ,'Lima 717 - Capital Federal - Argentina' ,'contactouade@uade.edu.ar' ,'Universidad Argentina de la Empresa' , 'UADE', 'La Universidad Argentina de la Empresa (UADE) es una universidad con carácter de fundación, fundada en 1957 por la Cámara Argentina de Sociedades Anónimas. Inicialmente denominada Instituto Superior de Estudios de la Empresa, en el año 1962 que recibe su nombre actual.1​ Fundada y ubicada en el Barrio de Monserrat, en la Ciudad de Buenos Aires, su actual sede está localizada en la Calle Lima 717, muy próxima a la emblemática Avenida 9 de Julio. Asimismo, en 2012 inauguró su Campus Costa Argentina ubicado en Av. Intermédanos Sur 776, Pinamar.2​', '011 4000-7600'),
(3, 1 ,'unq' ,'30-54666656-3' ,'Roque Sáenz Peña 352 - Quilmes - Argentina' ,'info@unq.edu.ar' , 'Universidad Nacional de Quilmes', 'UNQ' , 'La Universidad Nacional de Quilmes (UNQ) es una universidad pública argentina con sede en la localidad de Bernal, en el partido bonaerense de Quilmes. Fue creada por ley nacional 23.7492​ de 1989, en 1991 tuvo lugar el primer ciclo lectivo y fue normalizada el 12 de diciembre de 1992. Ubicada en la zona sur del Gran Buenos Aires, su área de influencia se extiende a los vecinos partidos de Berazategui, Florencio Varela, Avellaneda y Almirante Brown. Esta Universidad fue pionera en Latinoamérica en materia de educación no presencial ya que en 1999 inauguró su primera aula virtual y fue la primera universidad argentina con el campus disponible por Google Street View.', '011 4365-7100');

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
(9, LOAD_FILE(CONCAT(@images_path, 'subjects/management.jpg')), 'jpg'),
(10, LOAD_FILE(CONCAT(@images_path, 'PDF1.pdf')), 'pdf'),
(11, LOAD_FILE(CONCAT(@images_path, 'PDF2.pdf')), 'pdf'),
(12, LOAD_FILE(CONCAT(@images_path, 'PDF3.pdf')), 'pdf'),
(13, LOAD_FILE(CONCAT(@images_path, 'PDF4.pdf')), 'pdf'),
(14, LOAD_FILE(CONCAT(@images_path, 'PDF5.pdf')), 'pdf'),
(15, LOAD_FILE(CONCAT(@images_path, 'PDF6.pdf')), 'pdf'),
(16, LOAD_FILE(CONCAT(@images_path, 'PDF7.pdf')), 'pdf'),
(17, LOAD_FILE(CONCAT(@images_path, 'PDF8.pdf')), 'pdf'),
(18, LOAD_FILE(CONCAT(@images_path, 'PDF9.pdf')), 'pdf');

insert into materia (id, code, destacada, foto_id, nombre)
VALUES
(1, 'biology', 1, 2, 'Biología'),
(2, 'computer', 1, 3, 'Computación'),
(3, 'economy', 1, 4, 'Economía'),
(4, 'engineering', 1, 5, 'Ingeniería'),
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



insert into documento (nombre, descripcion, eliminado, extension, fechaCreacion, fechaUltModificacion, publico, alumno_id, entidad_id, idioma_id, materia_id, nivel_id, puntuacion, puntuacionCantidad)
  VALUES
  ('Algoritmos y programación 1', 'Usted aprenderá las nociones básicas de la programación estructurada', 0, 'jpg', STR_TO_DATE('1-01-2018', '%d-%m-%Y'), STR_TO_DATE('1-01-2018', '%d-%m-%Y'), 1, 1, 1, 1, 2, 1, 0 ,0),
  ('Reglas de Oferta y Demanda', 'Nociones básicas de las reglas del mercado.', 0, 'jpg', STR_TO_DATE('10-7-2018', '%d-%m-%Y'), STR_TO_DATE('10-08-2018', '%d-%m-%Y'), 1, 2, 2, 1, 3, 1, 0 ,0),
  ('Análisis de variable compleja', 'Análisis de variable compleja', 0, 'jpg', STR_TO_DATE('1-01-2018', '%d-%m-%Y'), STR_TO_DATE('1-01-2018', '%d-%m-%Y'), 1, 1, 1, 1, 4, 3, 0 ,0),
  ('Física 2', 'Resumen de la materia Física 2 FIUBA', 0, 'jpg', STR_TO_DATE('10-01-2018', '%d-%m-%Y'), STR_TO_DATE('10-01-2018', '%d-%m-%Y'), 1, 1, 1, 1, 4, 2, 0 ,0),
  ('Biología General', 'Conocimientos básicos de biología', 0, 'jpg', STR_TO_DATE('23-08-2018', '%d-%m-%Y'), STR_TO_DATE('23-08-2018', '%d-%m-%Y'), 1, 3, 3, 1, 1, 1, 0 ,0),
  ('English for dummies', 'English por beginners.', 0, 'jpg', STR_TO_DATE('1-09-2018', '%d-%m-%Y'), STR_TO_DATE('1-09-2018', '%d-%m-%Y'), 1, 3, 3, 2, 7, 1, 0 ,0),
  ('Finanzas personales', 'Como manejar las finanzas personales.', 0, 'jpg', STR_TO_DATE('1-01-2018', '%d-%m-%Y'), STR_TO_DATE('1-01-2018', '%d-%m-%Y'), 1, 3, 3, 1, 5, 1, 0 ,0),
  ('Management de Pymes', 'Técnicas para el manejo de pequeñas y medianas empresas.', 0, 'jpg', STR_TO_DATE('18-01-2018', '%d-%m-%Y'), STR_TO_DATE('18-01-2018', '%d-%m-%Y'), 1, 2, 2, 1, 8, 2, 0 ,0),
  ('Historia Argentina', 'Historia argentina contemporanea', 0, 'jpg', STR_TO_DATE('25-06-2018', '%d-%m-%Y'), STR_TO_DATE('25-06-2018', '%d-%m-%Y'), 1, 3, 3, 1, 6, 1, 0 ,0);

insert into version (documento_id, file_id)
  VALUES
  (1,10),
  (2,11),
  (3,12),
  (4,13),
  (5,14),
  (6,15),
  (7,16),
  (8,17),
  (9,18);