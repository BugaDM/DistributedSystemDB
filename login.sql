drop procedure if exists login;

delimiter //

create procedure login(
    in id_cliente_in int,
    in contraseña_in varchar(45))
    begin
    if (select ID_Cliente from Cliente where ID_Cliente=id_cliente_in and Pass=contraseña_in) is not null then
        set @ID_Cliente=id_cliente_in;
        select "Login correcto" as log;
    else
        select "Contraseña o usuario incorrecto" as log;
    end if;
    end
    //
delimiter ;