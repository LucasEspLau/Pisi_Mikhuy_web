package com.fia.sem7.models.service;

import java.util.List;
import java.util.Optional;

import com.fia.sem7.models.entity.Plato;

public interface IPlatoService {
	
public List<Plato> findAll();
	
public Plato save(Plato plato);

public Optional<Plato> get(Long id);

public void update(Plato plato);

public void delete(Long id);

public Plato findOne(Long id);

}
