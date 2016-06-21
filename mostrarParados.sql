/* 
Esta consulta mostrará una lista del nombre
 e id de espectáculos a los que pueden asistir Parados.
*/

DROP PROCEDURE IF EXISTS mostrarParados;
DELIMITER $$


CREATE PROCEDURE mostrarParados()
BEGIN 
select DISTINCT ID_Espectaculo,Descripcion,Espectaculos.Tipo FROM
Espectaculos,Espectaculos_has_Tipo_Permite,Tipo_Permite,Eventos
WHERE Espectaculos.ID_espectaculo=Espectaculos_has_Tipo_Permite.Espectaculos_ID_Espectaculo
AND
Espectaculos_has_Tipo_Permite.Tipo_Permite_Tipo=4
AND
eventos.estado=1
AND
Eventos.FK_ID_Espectaculo=Espectaculos.ID_Espectaculo;

END$$

DELIMITER ;





