/* 
Procedimiento que anula una localidad asociada a un cliente
*/

DROP PROCEDURE IF EXISTS anulacionLocalidad;
DELIMITER $$


CREATE PROCEDURE anulacionLocalidad(
IN 		ID_evento		INT,
IN 		ID_localidad	INT,
IN		ID_cliente		INT
)

BEGIN 

declare id_Estado_Localidad int;
declare hora_pre DATETIME;
declare t_penalizacion int;
declare porcentaje int;
declare ID_espectaculo int;



    /* Adquirimos el identificador Localidad->Evento->Compra */
SELECT 
    idEstado_Localidad
INTO id_Estado_Localidad FROM
    Estado_Localidad
WHERE
    FK_ID_localidad = ID_Localidad
        AND FK_ID_evento = ID_Evento;
/* Adquirimos hora de pre reserva */
SELECT 
    hora_preReserva
INTO hora_pre FROM
    Venta
WHERE
    FK_idEstado_Localidad = id_Estado_Localidad;

/*  Adquirimos el ID del espectaculo */
SELECT 
    FK_ID_Espectaculo
INTO ID_espectaculo FROM
    Eventos
WHERE
    Eventos.ID_evento = ID_evento;
/*  Una vez que tenemos el espectaculo, sacamos tiempo y porcentaje de penalizacion */


SELECT 
    Porcentaje_Penalizacion
INTO porcentaje
FROM
    Espectaculos
WHERE
    Espectaculos.ID_espectaculo = ID_espectaculo;
    
    
SELECT Espectaculos.Tiempo_Penalizacion
INTO t_penalizacion
FROM Espectaculos
WHERE Espectaculos.ID_espectaculo = ID_espectaculo;

if NOW()>DATE_ADD(hora_pre,INTERVAL t_penalizacion MINUTE)  then
select 'Se te penalizará con un'as'Estado Operación',porcentaje as 'porcentaje de penalizacion';

    else

SELECT 'no hay penalizacion, anulación correcta' as 'Estado Operación';
select id_Estado_Localidad,hora_pre,t_penalizacion,porcentaje,ID_espectaculo;

    end if;
    
    
    
    
UPDATE Estado_Localidad 
SET 
    estado = 'libre'
WHERE
    FK_ID_localidad = ID_Localidad
        AND FK_ID_evento = ID_Evento;
        
        
DELETE FROM Venta 
WHERE
    FK_idEstado_Localidad = id_Estado_Localidad;
END$$

DELIMITER ;

