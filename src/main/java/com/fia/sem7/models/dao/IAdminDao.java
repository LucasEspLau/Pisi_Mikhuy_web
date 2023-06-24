package com.fia.sem7.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.fia.sem7.models.entity.Admin;

public interface IAdminDao extends CrudRepository<Admin, Long>{

}
