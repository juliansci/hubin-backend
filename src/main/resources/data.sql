insert into entidad (
   activa
  ,code
  ,cuit
  ,direccion
  ,email
  ,nombre
  ,telefono
) VALUES (
   1 -- activa - IN bit(1)
  ,'abcd' -- code - IN varchar(30)
  ,'30-54666656-1' -- cuit - IN varchar(30)
  ,'Av Paseo Colon 850'  -- direccion - IN varchar(50)
  ,'personal@fi.uba.ar'  -- email - IN varchar(30)
  ,'FIUBA' -- nombre - IN varchar(30)
  ,'43430968'   -- telefono - IN int(11)
);

insert into idioma (
   code
  ,nombre
) VALUES (
  'qqww' -- code - IN varchar(30)
  ,'español' -- nombre - IN varchar(30)
);

insert into nivel (
   code
  ,nombre
) VALUES (
   'aabb' -- code - IN varchar(30)
  ,'intermedio' -- nombre - IN varchar(30)
);

insert into file (
   data
  ,extension
) VALUES (
   LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 5.7/Uploads/a.jpg')   -- data - IN longblob
  ,'jpg'   -- documento_id - IN int(11)
);

insert into materia (
   code
  ,destacada
  ,foto_id
  ,nombre
) VALUES (
   'qqqq' -- code - IN varchar(30)
  ,1 -- destacada - IN bit(1)
  ,(select id from file where extension='jpg' limit 1)
  ,'Analisis II' -- nombre - IN varchar(30)
);

insert into alumno (
   dni
  ,email
  ,fechaNac
  ,nombre
  ,password
  ,presentacion
  ,username
  ,foto
) VALUES (
   32000111   -- dni - IN int(11)
  ,'q@q.com'  -- email - IN varchar(255)
  ,STR_TO_DATE('1-01-1980', '%d-%m-%Y')  -- fechaNac - IN datetime
  ,'juan perez'  -- nombre - IN varchar(255)
  ,'MTIz'  -- password - IN varchar(255) ---> password: 123
  ,'hola mundooooo'  -- presentacion - IN varchar(255)
  ,'juan'  -- username - IN varchar(255)
  ,(select id from file where extension='jpg' limit 1)
);

insert into documento (
  descripcion
  ,eliminado
  ,extension
  ,fechaCreacion
  ,fechaUltModificacion
  ,nombre
  ,publico
  ,alumno_id
  ,entidad_id
  ,idioma_id
  ,materia_id
  ,nivel_id
) VALUES (
   'probando la creacion de un documento'  -- descripcion - IN varchar(255)
  ,0 -- eliminado - IN bit(1)
  ,'jpg'  -- extension - IN varchar(255)
  ,STR_TO_DATE('1-01-1950', '%d-%m-%Y')  -- fechaUltModificacion - IN datetime
  ,STR_TO_DATE('1-01-1980', '%d-%m-%Y')  -- fechaUltModificacion - IN datetime
  ,'documento1'  -- nombre - IN varchar(255)
  ,1 -- publico - IN bit(1)
  ,(select id from alumno where username='juan')   -- alumno_id - IN int(11)
  ,(select id from entidad where nombre='FIUBA')   -- entidad_id - IN int(11)
  ,(select id from idioma where nombre='español')   -- idioma_id - IN int(11)
  ,(select id from materia where nombre='Analisis II')   -- materia_id - IN int(11)
  ,(select id from nivel where nombre='intermedio')   -- nivel_id - IN int(11)
);


/*delete from compartido;
delete from version;
delete from documento;
delete from materia;
delete from entidad;
delete from nivel;
delete from idioma;
delete from alumno;
delete from file;
delete from publicidad;
delete from objetivo;
delete from proveedor;

drop table compartido;
drop table version;
drop table documento;
drop table materia;
drop table entidad;
drop table nivel;
drop table idioma;
drop table alumno;
drop table file;
drop table publicidad;
drop table objetivo;
drop table proveedor;
drop table administrador;*/
