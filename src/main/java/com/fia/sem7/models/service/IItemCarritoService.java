package com.fia.sem7.models.service;

import java.util.List;

import com.fia.sem7.models.entity.ItemCarrito;



public interface IItemCarritoService {
	public List<ItemCarrito> findAll();
	
	public void save(ItemCarrito itemcarrito);
	
	public ItemCarrito findOne(Long id);
	
	public void delete(Long id);
	

}
