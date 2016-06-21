/* 
Esta consulta mostrará una lista del nombre
 e id de espectáculos a los que pueden asistir bebés.
*/

DROP PROCEDURE IF EXISTS mostrarBebes;
DELIMITER $$


CREATE PROCEDURE mostrarBebes()
BEGIN 
select DISTINCT ID_Espectaculo,Descripcion,Espectaculos.Tipo FROM
Espectaculos,Espectaculos_has_Tipo_Permite,Tipo_Permite,Eventos
WHERE Espectaculos.ID_espectaculo=Espectaculos_has_Tipo_Permite.Espectaculos_ID_Espectaculo
AND
Espectaculos_has_Tipo_Permite.Tipo_Permite_Tipo=1
AND
eventos.estado=1
AND
Eventos.FK_ID_Espectaculo=Espectaculos.ID_Espectaculo;


END$$

DELIMITER ;





