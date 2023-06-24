package com.fia.sem7.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fia.sem7.models.dao.ICarritoDao;
import com.fia.sem7.models.entity.Carrito;

@Service
public class CarritoServiceImpl implements ICarritoService {

	@Autowired
	private ICarritoDao carritoDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Carrito> findAll() {
		// TODO Auto-generated method stub
		return (List<Carrito>) carritoDao.findAll();
	}

	@Override
	@Transactional
	public void save(Carrito carrito) {
		// TODO Auto-generated method stub
		carritoDao.save(carrito);
	}

	@Override
	@Transactional(readOnly = true)
	public Carrito findOne(Long id) {
		// TODO Auto-generated method stub
		return carritoDao.findById(id).orElseGet(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		carritoDao.deleteById(id);
	}
}
