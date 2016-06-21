/*	
 Esta consulta muestra los eventos activos para un 
 espectáculo dado junto con su
 recinto y fecha.

Selecciona todos los eventos que están activos,
después restringe a los que solamente coinciden
en gradas, recinto y evento.
*/



DROP PROCEDURE IF EXISTS eventosEspectaculo;
DELIMITER $$


CREATE PROCEDURE eventosEspectaculo(
       IN ID_Espectaculo INT
)
BEGIN

SELECT DISTINCT
       Eventos.ID_evento,
       Eventos.fecha,
       Recintos.ID_Recinto,
       Recintos.Descripcion as 'Descripcion Recinto'
             
FROM  
      (Eventos,Recintos,Precio,Gradas)
WHERE 
      FK_ID_Espectaculo=ID_Espectaculo
      AND
      Eventos.estado=1
      AND
      Gradas.ID_gradas=Precio.Gradas_ID_gradas
      AND
      Recintos.ID_Recinto=Gradas.FK_ID_Recinto
      AND
      Precio.FK_ID_evento=Eventos.ID_evento;
      


END$$

DELIMITER ;


