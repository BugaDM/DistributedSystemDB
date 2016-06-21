drop procedure if exists signup;

delimiter //

create procedure signup(
    in nombre_in varchar(45),
    in direccion_in varchar(45),
    in contraseña_in varchar(45),
    in metodo_de_pago_in varchar(45))
    begin
    /*if (select ID_Cliente from Cliente where ID_Cliente=id_cliente_in and Contraseña=contraseña_in) is not null then
        update Cliente set Metodo_de_pago=metodo_de_pago_in where ID_Cliente=id_cliente_in and Contraseña=contraseña_in;
    else */
        insert into Cliente (nombre,Direccion,Pass,Metodo_de_pago) values(nombre_in,direccion_in,contraseña_in,metodo_de_pago_in);
   /* end if;*/
    end
    //
delimiter ;