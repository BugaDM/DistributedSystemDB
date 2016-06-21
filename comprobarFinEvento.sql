/*	
Cada vez que se use la tabla de eventos se 
debe de llamar primero a este procedimiento, 
comprueba que los eventos que hayan empezado
*/



DROP PROCEDURE IF EXISTS comprobarFinEvento;
SET SQL_SAFE_UPDATES=0;

DELIMITER $$


CREATE PROCEDURE comprobarFinEvento()
BEGIN
UPDATE   eventos 
SET  eventos.estado=2
WHERE eventos.fecha < NOW();


END$$

DELIMITER ;
