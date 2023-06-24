package com.fia.sem7.models.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fia.sem7.models.dao.IPlatoDao;
import com.fia.sem7.models.entity.Plato;


@Service
public class PlatoServiceImpl implements IPlatoService {

	@Autowired
	private IPlatoDao platoDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Plato> findAll() {
		// TODO Auto-generated method stub
		return (List<Plato>) platoDao.findAll();
	}
	
	@Override
	@Transactional
	public Plato save(Plato plato) {
		// TODO Auto-generated method stub
		return platoDao.save(plato);
	}

	@Override
	@Transactional
	public Optional<Plato> get(Long id) {
		// TODO Auto-generated method stub
		return platoDao.findById(id);
	}
	
	@Override
	@Transactional
	public void update(Plato plato) {
		// TODO Auto-generated method stub
		platoDao.save(plato);
	}
	
	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		platoDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Plato findOne(Long id) {
		// TODO Auto-generated method stub
		return platoDao.findById(id).orElseGet(null);
	}
}

