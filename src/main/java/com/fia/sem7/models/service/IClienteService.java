package com.fia.sem7.models.service;

import java.util.List;

import com.fia.sem7.models.entity.Cliente;


public interface IClienteService {
	public List<Cliente> findAll();
	
	public void save(Cliente cliente);
	
	public Cliente findOne(Long id);
	
	public void delete(Long id);
	
	public boolean buscarCuenta(Cliente cliente);
	
	public Cliente buscarXIdentidad(Cliente cliente);
}
