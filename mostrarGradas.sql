/*	
 Muestra las gradas disponibles para un evento dado
*/



DROP PROCEDURE IF EXISTS mostrarGradas;
DELIMITER $$


CREATE PROCEDURE mostrarGradas(
       IN ID_Evento INT
)
BEGIN

SELECT DISTINCT
	Gradas.ID_gradas,Gradas.nombreGrada
FROM
	Gradas,Precio	
WHERE
	Precio.FK_ID_evento=ID_Evento;
	


END$$

DELIMITER ;


