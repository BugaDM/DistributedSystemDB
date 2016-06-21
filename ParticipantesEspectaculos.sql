/*
Esta consulta muestra los participantes para
un espectaculo dado.
*/



DROP PROCEDURE IF EXISTS participantesEspectaculo;
DELIMITER $$


CREATE PROCEDURE participantesEspectaculo(
       IN ID_Espectaculo INT
)
BEGIN 
SELECT ID_Participante,Nombre,Papel FROM
Participantes_Espectaculo,Participantes_Espectaculo_has_Espectaculos
WHERE Participantes_Espectaculo_has_Espectaculos.Espectaculos_ID_Espectaculo=ID_Espectaculo
AND
Participantes_Espectaculo_has_Espectaculos.Participantes_Espectaculo_ID_Participante=Participantes_Espectaculo.ID_Participante;


END$$

DELIMITER ;


