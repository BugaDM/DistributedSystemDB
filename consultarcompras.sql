drop procedure if exists consultarcompras;

delimiter //

create procedure consultarcompras(
    in ID_Cliente int)
    begin
    select Estado_Localidad.FK_ID_evento as ID_Evento,Estado_Localidad.FK_ID_localidad as ID_Localidad,Venta.Estado
        from Venta,Estado_Localidad where FK_ID_Cliente=ID_Cliente 
        and FK_idEstado_Localidad=Estado_Localidad.idEstado_Localidad;
    end
    //
delimiter ;