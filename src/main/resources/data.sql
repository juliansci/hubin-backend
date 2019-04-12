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
insert into file (id, data, extension, fecha)
VALUES
(1, LOAD_FILE(CONCAT(@images_path, 'user-profile.jpg')), 'jpg', STR_TO_DATE('01-01-2019', '%d-%m-%Y')),
(2, LOAD_FILE(CONCAT(@images_path, 'subjects/biology.jpg')), 'jpg', STR_TO_DATE('01-01-2019', '%d-%m-%Y')),
(3, LOAD_FILE(CONCAT(@images_path, 'subjects/computer.jpg')), 'jpg', STR_TO_DATE('01-01-2019', '%d-%m-%Y')),
(4, LOAD_FILE(CONCAT(@images_path, 'subjects/economy.jpg')), 'jpg', STR_TO_DATE('01-01-2019', '%d-%m-%Y')),
(5, LOAD_FILE(CONCAT(@images_path, 'subjects/engineering.jpg')), 'jpg', STR_TO_DATE('01-01-2019', '%d-%m-%Y')),
(6, LOAD_FILE(CONCAT(@images_path, 'subjects/finance.jpg')), 'jpg', STR_TO_DATE('01-01-2019', '%d-%m-%Y')),
(7, LOAD_FILE(CONCAT(@images_path, 'subjects/history.jpg')), 'jpg', STR_TO_DATE('01-01-2019', '%d-%m-%Y')),
(8, LOAD_FILE(CONCAT(@images_path, 'subjects/language.jpg')), 'jpg', STR_TO_DATE('01-01-2019', '%d-%m-%Y')),
(9, LOAD_FILE(CONCAT(@images_path, 'subjects/management.jpg')), 'jpg', STR_TO_DATE('01-01-2019', '%d-%m-%Y')),
(10, LOAD_FILE(CONCAT(@images_path, 'PDF1.pdf')), 'pdf', STR_TO_DATE('01-01-2019', '%d-%m-%Y')),
(11, LOAD_FILE(CONCAT(@images_path, 'PDF2.pdf')), 'pdf', STR_TO_DATE('01-01-2019', '%d-%m-%Y')),
(12, LOAD_FILE(CONCAT(@images_path, 'PDF3.pdf')), 'pdf', STR_TO_DATE('01-01-2019', '%d-%m-%Y')),
(13, LOAD_FILE(CONCAT(@images_path, 'PDF4.pdf')), 'pdf', STR_TO_DATE('01-01-2019', '%d-%m-%Y')),
(14, LOAD_FILE(CONCAT(@images_path, 'PDF5.pdf')), 'pdf', STR_TO_DATE('01-01-2019', '%d-%m-%Y')),
(15, LOAD_FILE(CONCAT(@images_path, 'PDF6.pdf')), 'pdf', STR_TO_DATE('01-01-2019', '%d-%m-%Y')),
(16, LOAD_FILE(CONCAT(@images_path, 'PDF7.pdf')), 'pdf', STR_TO_DATE('01-01-2019', '%d-%m-%Y')),
(17, LOAD_FILE(CONCAT(@images_path, 'PDF8.pdf')), 'pdf', STR_TO_DATE('01-01-2019', '%d-%m-%Y')),
(18, LOAD_FILE(CONCAT(@images_path, 'PDF9.pdf')), 'pdf', STR_TO_DATE('01-01-2019', '%d-%m-%Y')),
(19, LOAD_FILE(CONCAT(@images_path, 'PDF2.pdf')), 'pdf', STR_TO_DATE('01-01-2019', '%d-%m-%Y')),
(20, LOAD_FILE(CONCAT(@images_path, 'PDF2.pdf')), 'pdf', STR_TO_DATE('01-01-2019', '%d-%m-%Y')),
(21, LOAD_FILE(CONCAT(@images_path, 'PDF2.pdf')), 'pdf', STR_TO_DATE('01-01-2019', '%d-%m-%Y'));

insert into materia (id, code, destacada, foto_id, nombre, descripcion)
VALUES
(1, 'biology', 1, 2, 'Biología', 'La Biología es la ciencia que estudia a los seres vivos y, más específicamente, su origen, su evolución y sus propiedades, nutrición, morfogénesis, reproducción (asexual y sexual), patogenia, etc. Se ocupa tanto de la descripción de las características y los comportamientos de los organismos individuales, como de las especies en su conjunto, así como de la reproducción de los seres vivos y de las interacciones entre ellos y el entorno. De este modo, trata de estudiar la estructura y la dinámica funcional comunes a todos los seres vivos, con el fin de establecer las leyes generales que rigen la vida orgánica y los principios de esta.'),
(2, 'computer', 1, 3, 'Computación', 'La Computación es la disciplina o campo de estudio que abarca el conjunto de conocimientos, métodos y técnicas referentes al tratamiento automático de la información, junto con sus teorías y aplicaciones prácticas, con el fin de almacenar, procesar y transmitir datos e información en formato digital utilizando sistemas computacionales. Los datos son la materia prima para que, mediante su proceso, se obtenga como resultado información.  Para ello, la informática crea y/o emplea sistemas de procesamiento de datos, que incluyen medios físicos (hardware) en interacción con medios lógicos (software) y las personas que los programan y/o los usan (humanware).'),
(3, 'economy', 1, 4, 'Economía', 'La Economía es la ciencia que estudia los recursos, la creación de riqueza y la producción, distribución y consumo de bienes y servicios, para satisfacer las necesidades humanas.'),
(4, 'engineering', 1, 5, 'Ingeniería', 'La ingeniería es el conjunto de conocimientos científicos y tecnológicos para la innovación, invención, desarrollo y mejora de técnicas y herramientas para satisfacer las necesidades y resolver los problemas de las empresas y la sociedad. La ingeniería aplica los conocimientos y métodos científicos a la invención o perfeccionamiento de tecnologías de manera pragmática y ágil, adecuándose a las limitaciones de tiempo, recursos, requisitos legales, requisitos de seguridad, ecológicos, etc. '),
(5, 'finance', 1, 6, 'Finanzas', 'Las finanzas son una rama de la administración y la economía que estudia el intercambio de capital entre individuos, empresas, o Estados y con la incertidumbre y el riesgo que estas actividades conllevan. Se dedica al estudio de la obtención de capital para la inversión en bienes productivos y de las decisiones de inversión de los ahorradores. Está relacionado con las transacciones y con la administración del dinero. En ese marco se estudia la obtención y gestión, por parte de una compañía, un individuo, o del propio Estado, de los fondos que necesita para cumplir sus objetivos, y de los criterios con que dispone de sus activos; en otras palabras, lo relativo a la obtención y gestión del dinero, así como de otros valores o sucedáneos del dinero, como lo son los títulos, los bonos, etc. '),
(6, 'history', 1, 7, 'Historia', 'La historia es la ciencia que tiene como objeto el estudio de sucesos del pasado, tradicionalmente de la humanidad​, y como método el propio de las Ciencias Sociales/Humanas, así como el de las Ciencias Naturales en un marco de interdisciplinariedad.2​ Siendo la disciplina que estudia y narra cronológicamente los acontecimientos pasados. Se denomina también «historia» al periodo que transcurre desde la aparición de la escritura hasta la actualidad, aunque es un convencionalismo ampliamente superado en la actualidad, considerando a la prehistoria también como parte intrínseca de la historia.'),
(7, 'language', 1, 8, 'Idiomas', 'Un idioma o lengua es un sistema de comunicación verbal (lengua oral y gráfica) o gestual (lengua signada), propia de una comunidad humana. Cada idioma se subdivide en dialectos (por definición, cada una de las formas en que se habla una lengua o idioma en una región específica), pero actualmente se duda que exista un criterio válido para hacer tal división (de lenguas o idiomas en dialectos) de una manera objetiva y segura. La determinación de si dos variedades lingüísticas son parte o no del mismo idioma es más una cuestión sociopolítica que lingüística.'),
(8, 'management', 1, 9, 'Management', 'El Management es la ciencia social que tiene por objeto el estudio de las organizaciones y la técnica encargada de la planificación, organización, dirección y control de los recursos (humanos, financieros, materiales, tecnológicos, del conocimiento, etc.) de una organización, con el fin de obtener el máximo beneficio posible; este beneficio puede ser social, económico, dependiendo de los fines perseguidos por la organización.');

--password: MTIz: 123
insert into alumno (username, nombre, password, dni, email, fechaNac, presentacion, foto)
VALUES
('user', 'Usuario 1', 'MTIz', 32000111, 'user@user.com', STR_TO_DATE('1-01-1980', '%d-%m-%Y'),  'hola mundooooo', 1),
('juan', 'Juan Perez', 'MTIz', 33000111, 'juan@user.com', STR_TO_DATE('1-01-1990', '%d-%m-%Y'),  'hola mundooooo', NULL),
('pedro', 'Pedro Batagglia', 'MTIz', 34000111, 'pedro@user.com', STR_TO_DATE('1-01-1997', '%d-%m-%Y'),  'hola mundooooo', NULL);

insert into alumno_materia_observable (id, fecha, alumno_id, materia_id)
VALUES
(1, STR_TO_DATE('1-01-2019', '%d-%m-%Y'), 2, 2);




insert into documento (nombre, descripcion, eliminado, extension, fechaCreacion, fechaUltModificacion, publico, alumno_id, entidad_id, idioma_id, materia_id, nivel_id, puntuacion, puntuacionCantidad)
  VALUES
  ('Algoritmos y programación 1', 'Usted aprenderá las nociones básicas de la programación estructurada', 0, 'jpg', STR_TO_DATE('1-01-2018', '%d-%m-%Y'), STR_TO_DATE('1-01-2018', '%d-%m-%Y'), 1, 1, 1, 1, 2, 1, 0 ,0),
  ('Python para Dummies', 'Usted aprenderá lo básico del lenguaje de programación Python', 0, 'jpg', STR_TO_DATE('1-03-2019', '%d-%m-%Y'),  STR_TO_DATE('1-03-2019', '%d-%m-%Y'), 1, 1, 1, 1, 2, 1, 0 ,0),
  ('Como graficar con Python', 'Tutorial para realizar gráficos profesionales mediante scripts en python.', 0, 'jpg', STR_TO_DATE('1-01-2018', '%d-%m-%Y'), STR_TO_DATE('1-01-2018', '%d-%m-%Y'), 1, 1, 1, 1, 2, 2, 0 ,0),
  ('Manual de python', 'Manual para python. Todo lo que necesita saber para manejar el lenguaje al 100%.', 0, 'jpg', STR_TO_DATE('1-01-2018', '%d-%m-%Y'), STR_TO_DATE('1-01-2018', '%d-%m-%Y'), 1, 1, 1, 1, 2, 1, 0 ,0),
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
  (2,19),
  (3,20),
  (4,21),
  (5,11),
  (6,12),
  (7,13),
  (8,14),
  (9,15),
  (10,16),
  (11,17),
  (12,18);

insert into proveedor (id, cuit, razonSocial, direccion, telefono, email) VALUES
(1, '234433424', 'Libreria del Sur', 'Esparsek 292', '1143555049', 'libreriadelsur@gmail.com'),
(2, '264533524', 'Instituto privado Villa Monica', 'San Martin 3392', '1145455089', 'instituto@villa-monica.com');

insert into objetivo (nombre, tipo, activo, descripcion, proveedor_id, orden) VALUES
('Primer comentario', 'comment_1', 1, 'Primer comentario en la plataforma.', NULL, 3),
('Quinto comentario', 'comment_5', 1, 'Quinto comentario en la plataforma.', NULL, 6),
('Primer documento', 'upload_1', 1, 'Suba su primer documento a la plataforma.', NULL, 2),
('Quinto documento', 'upload_5', 1, 'Suba su quinto documento a la plataforma.', NULL, 5),
('Actualizacion perfil', 'update_profile', 1, 'Actualice su perfil.', NULL, 1),
('Primera calificacion documento', 'rate_1', 1, 'Califique un documento.', NULL, 4),
('Quinta calificacion documento', 'rate_5', 1, 'Califique 5 veces.', NULL, 7);
