package com.fia.sem7.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fia.sem7.models.dao.IItemCarritoDao;
import com.fia.sem7.models.entity.ItemCarrito;


@Service
public class ItemCarritoService implements IItemCarritoService {
	@Autowired
	private IItemCarritoDao itemCarritoDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<ItemCarrito> findAll() {
		// TODO Auto-generated method stub
		return (List<ItemCarrito>)itemCarritoDao.findAll();
	}

	@Override
	@Transactional
	public void save(ItemCarrito itemCarrito) {
		// TODO Auto-generated method stub
		itemCarritoDao.save(itemCarrito);
	}

	@Override
	@Transactional(readOnly = true)
	public ItemCarrito findOne(Long id) {
		// TODO Auto-generated method stub
		return itemCarritoDao.findById(id).orElseGet(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		itemCarritoDao.deleteById(id);
	}
}
