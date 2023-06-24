package com.fia.sem7.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.fia.sem7.models.dao.IClienteDao;
import com.fia.sem7.models.entity.Cliente;




@Service
public class ClienteServiceImpl implements IClienteService {

	@Autowired
	private IClienteDao clienteDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findAll() {
		// TODO Auto-generated method stub
		return (List<Cliente>) clienteDao.findAll();
	}

	@Override
	@Transactional
	public void save(Cliente cliente) {
		// TODO Auto-generated method stub
		clienteDao.save(cliente);
	}

	@Override
	@Transactional(readOnly = true)
	public Cliente findOne(Long id) {
		// TODO Auto-generated method stub
		return clienteDao.findById(id).orElseGet(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		clienteDao.deleteById(id);
	}
	@Override
	@Transactional
	public boolean buscarCuenta(Cliente cliente) {
		for (Cliente  ae:findAll()) {	
			if(cliente.getIden().equals(ae.getIden()) && cliente.getCon().equals(ae.getCon())) {				
				return true;				
			}			
		}
		return false;
	}
	@Override
	@Transactional
	public Cliente buscarXIdentidad(Cliente cliente) {
		for (Cliente ae:findAll()) {	
			if(cliente.getIden().equals(ae.getIden())) {				
				return ae;				
			}					
		}
		return null;
	}
}
