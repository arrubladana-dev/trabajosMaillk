delimiter //
 create procedure pa_list_wish(in id int)
 begin
	select m.name as marca, p.name_Product from wish_list w 
	join products p on p.id = id_product
    join mark m on m.id = id_mark
	join users u on u.id = id_user where w.id_user = id;
 end //
delimiter ;

delimiter //
create procedure pa_list_catalog_products()
begin
    select 
        p.name_product as producto,p.stock as stock, m.name as marca,c.name as catalogo
    from products p
    join mark m on m.id = p.id_mark
    join catalog c on p.id = c.id_product;
end //
delimiter ;

call pa_list_catalog_products();

call pa_list_wish(2);