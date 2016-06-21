drop procedure if exists reservalocalidad;

delimiter //

create procedure reservalocalidad(
    in ID_Cliente int,
    in ID_Evento int,
    in ID_Localidad int)
    begin
    /* Mostramos al cliente las localidades
    de ese evento.
    */
    
    
    declare id_Estado_Localidad int;
SELECT 
    idEstado_Localidad
INTO id_Estado_Localidad FROM
    Estado_Localidad
WHERE
    FK_ID_localidad = ID_Localidad
        AND FK_ID_evento = ID_Evento;
    
    
UPDATE Estado_Localidad 
SET 
    estado = 'pre-reservado'
WHERE
    FK_ID_localidad = ID_Localidad
        AND FK_ID_evento = ID_Evento;
    if (select estado from Venta where FK_idEstado_Localidad=id_Estado_Localidad and FK_ID_Cliente=ID_Cliente) is not null then
        update Venta set Estado="pre-reservado" where FK_idEstado_Localidad=id_Estado_Localidad and FK_ID_Cliente=ID_Cliente;
    else 
        insert into Venta (Estado,FK_idEstado_Localidad,FK_ID_Cliente,hora_preReserva) values("pre-reservado",id_Estado_Localidad,ID_Cliente,NOW());
    end if;
    end
    //
delimiter ;