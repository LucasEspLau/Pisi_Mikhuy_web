package com.fia.sem7.models.service;

import java.util.List;

import com.fia.sem7.models.entity.Carrito;

public interface ICarritoService {
	public List<Carrito> findAll();
	
	public void save(Carrito carrito);
	
	public Carrito findOne(Long id);
	
	public void delete(Long id);
}
