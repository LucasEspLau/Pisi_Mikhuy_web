package com.fia.sem7.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.fia.sem7.models.entity.Cliente;


public interface IClienteDao extends CrudRepository<Cliente, Long>{

}
