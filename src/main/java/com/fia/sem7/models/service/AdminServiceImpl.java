package com.fia.sem7.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fia.sem7.models.dao.IAdminDao;
import com.fia.sem7.models.entity.Admin;


@Service
public class AdminServiceImpl implements IAdminService {
	@Autowired
	private IAdminDao adminDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Admin> findAll() {
		// TODO Auto-generated method stub
		return (List<Admin>) adminDao.findAll();
	}

	@Override
	@Transactional
	public void save(Admin admin) {
		// TODO Auto-generated method stub
		adminDao.save(admin);
	}

	@Override
	@Transactional(readOnly = true)
	public Admin findOne(Long id) {
		// TODO Auto-generated method stub
		return adminDao.findById(id).orElseGet(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		adminDao.deleteById(id);
	}
	@Override
	@Transactional
	public boolean buscarCuenta(Admin admin) {
		for (Admin  ae:findAll()) {	
			if(admin.getIden().equals(ae.getIden()) && admin.getCon().equals(ae.getCon())&& admin.getCod().equals(ae.getCod())) {				
				return true;				
			}			
		}
		return false;
	}
	@Override
	@Transactional
	public Admin buscarXIdentidad(Admin admin) {
		for (Admin ae:findAll()) {	
			if(admin.getIden().equals(ae.getIden())) {				
				return ae;				
			}					
		}
		return null;
	}
}
