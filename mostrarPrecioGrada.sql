/*	
 Muestra el precio para una grada dada para un evento dado según 
tipo de espectador
*/



DROP PROCEDURE IF EXISTS mostrarPrecioGrada;
DELIMITER $$


CREATE PROCEDURE mostrarPrecioGrada(
       IN ID_grada INT,
       IN Evento INT
)
BEGIN



SELECT DISTINCT
	Gradas.ID_gradas as 'ID de Grada',
	Gradas.nombreGrada as 'Nombre de Grada',
	Precio.precio as 'Precio(€)',
	Precio.FK_Permite_Tipo as Permite,
	Precio.FK_ID_evento as ID_Evento
FROM
	Gradas,Precio	
WHERE
	Gradas.ID_gradas=ID_grada
AND
	FK_ID_evento=Evento
    ORDER BY Precio.FK_Permite_Tipo;
	


END$$

DELIMITER ;


