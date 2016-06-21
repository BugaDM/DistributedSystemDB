/* Procedimiento almacenado que será manejado por
el scheduler, para comprobar las fechas en las
que las pre-reservas caducan.
*/

DROP EVENT IF EXISTS reserva_check;

DELIMITER $$

CREATE 
	EVENT `reserva_check` 
	ON SCHEDULE EVERY 1 MINUTE STARTS '2011-07-24 03:00:00' 
	DO BEGIN
	CALL comprobarFinPreReserva();
    CALL comprobarFinEvento();
	    
	END $$

DELIMITER ;


