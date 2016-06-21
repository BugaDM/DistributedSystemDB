/* 
Esta consulta mostrará una lista del nombre
 e id de espectáculos a los que pueden asistir infantiles.
*/

DROP PROCEDURE IF EXISTS mostrarInfantiles;
DELIMITER $$


CREATE PROCEDURE mostrarInfantiles()
BEGIN 
select DISTINCT ID_Espectaculo,Descripcion,Espectaculos.Tipo FROM
Espectaculos,Espectaculos_has_Tipo_Permite,Tipo_Permite,Eventos
WHERE Espectaculos.ID_espectaculo=Espectaculos_has_Tipo_Permite.Espectaculos_ID_Espectaculo
AND
Espectaculos_has_Tipo_Permite.Tipo_Permite_Tipo=2
AND
eventos.estado=1
AND
Eventos.FK_ID_Espectaculo=Espectaculos.ID_Espectaculo;

END$$

DELIMITER ;




END$$

DELIMITER ;
