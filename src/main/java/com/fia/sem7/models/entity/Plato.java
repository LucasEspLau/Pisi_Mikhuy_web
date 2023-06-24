package com.fia.sem7.models.entity;

import java.io.Serializable;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
@Entity
@Table(name="platos")
public class Plato implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotEmpty
	@Column(name="nombre_plato")
	private String nomPla;
	@NotNull
	@Column(name="precio")
	private Integer precio;
	@NotEmpty
	@Column(name="descripcion")
	private String descripcion;
	@Column(name="imagen")
	private String imagen;
	public Plato() {
	}

	public Plato(Long id, @NotEmpty String nomPla, @NotNull Integer precio, @NotEmpty String descripcion,
			String imagen) {
		super();
		this.id = id;
		this.nomPla = nomPla;
		this.precio = precio;
		this.descripcion = descripcion;
		this.imagen = imagen;
	}
	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomPla() {
		return nomPla;
	}

	public void setNomPla(String nomPla) {
		this.nomPla = nomPla;
	}

	public Integer getPrecio() {
		return precio;
	}

	public void setPrecio(Integer precio) {
		this.precio = precio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}



	
}
