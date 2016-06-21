/*comprobaremos que la prereserva no excede el tiempo máximo 
que puede permaneecer en ese estado, de ser así se anula y libera la localidad.


Si $hora_pre_reserva + $tiempo_anulacion > $NOW entonces se anula.
*/

DROP PROCEDURE IF EXISTS comprobarFinPreReserva;
SET SQL_SAFE_UPDATES=0;

DELIMITER $$


CREATE PROCEDURE comprobarFinPreReserva()
BEGIN
-- Flag para finalizado
 DECLARE v_finished INTEGER DEFAULT 0;
 
 DECLARE tiempo_anula INTEGER DEFAULT 0;
 DECLARE ID_evento INTEGER DEFAULT 0;
 DECLARE ID_cliente INTEGER DEFAULT 0;
 DECLARE ID_localidad INTEGER DEFAULT 0;
 DECLARE horaPreReserva DATETIME DEFAULT (NOW()- NOW());
 
 -- declare cursor for employee email
 DEClARE reservaCursor CURSOR FOR 
SELECT 
eventos.tiempo_anula_preReserva as 'tiempo de anulacion',
venta.FK_ID_Cliente as 'ID cliente',
venta.hora_preReserva as 'hora de preReserva',
estado_localidad.FK_ID_localidad as 'localidad',
estado_localidad.FK_ID_evento as 'evento'
FROM
Venta,
estado_localidad,
eventos
WHERE Venta.estado='pre-reservado'
AND estado_localidad.FK_ID_evento=eventos.ID_evento
AND venta.FK_idEstado_Localidad=estado_localidad.idEstado_Localidad;

 -- declare NOT FOUND handler
 DECLARE CONTINUE HANDLER 
        FOR NOT FOUND SET v_finished = 1;
 
 OPEN reservaCursor;
 
 get_datos: LOOP
 
 FETCH reservaCursor INTO tiempo_anula,ID_cliente,horaPreReserva,ID_localidad,ID_evento;
 
 select tiempo_anula,ID_cliente,ID_cliente,horaPreReserva,ID_localidad,ID_evento ;
 
 if DATE_ADD(horaPreReserva,INTERVAL tiempo_anula MINUTE)< NOW()  then

     call anulacionLocalidad(ID_evento,ID_localidad,ID_cliente);

  /*  else

select 'no ha caducado';*/
    end if;
 
 IF v_finished = 1 THEN 
 LEAVE get_datos;
 END IF;
 
 END LOOP get_datos;
 
 CLOSE reservaCursor;
 

END$$

DELIMITER ;
