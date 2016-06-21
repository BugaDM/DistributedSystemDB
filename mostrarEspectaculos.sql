/*	
 Muestra la lista de todos los espectáculos con eventos activos
 junto a su descripción y tipo.
 
 Atención, se ha de cambiar en la cláusula WHERE el entero estado
 por el valor correspondiente a "Evento activo"
*/



DROP PROCEDURE IF EXISTS mostrarEspectaculos;
DELIMITER $$


CREATE PROCEDURE mostrarEspectaculos()
BEGIN



SELECT DISTINCT
	Espectaculos.ID_Espectaculo as ID,
    Espectaculos.Descripcion as Descripcion,
    Espectaculos.Tipo as 'Tipo de Espectáculo'
FROM
	Espectaculos,Eventos	
WHERE
	Eventos.estado=1
    AND
    Eventos.FK_ID_Espectaculo=Espectaculos.ID_Espectaculo;


END$$

DELIMITER ;
