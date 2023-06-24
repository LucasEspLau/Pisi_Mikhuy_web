package com.fia.sem7.models.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;

import jakarta.validation.constraints.NotEmpty;


@Entity
@Table(name="admins")
public class Admin implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	@Column(name="identidad")
	private String iden;
	
	@NotEmpty
	@Column(name="codigo")
	private String cod;
	
	@NotEmpty
	@Column(name="nombres")
	private String nom;
	
	@NotEmpty
	@Column(name="apellido_paterno")
	private String apePa;
	
	@NotEmpty
	@Column(name="apellido_materno")
	private String apeMa;
	
	@NotEmpty
	@Column(name="contrasena")
	private String con;
	
	@Email
	@NotEmpty
	@Column(name="correo")
	private String correo;
	@NotEmpty
	@Column(name="genero")
	private String genero;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getApePa() {
		return apePa;
	}
	public void setApePa(String apePa) {
		this.apePa = apePa;
	}
	public String getApeMa() {
		return apeMa;
	}
	public void setApeMa(String apeMa) {
		this.apeMa = apeMa;
	}
	public String getCon() {
		return con;
	}
	public void setCon(String con) {
		this.con = con;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public String getIden() {
		return iden;
	}
	public void setIden(String iden) {
		this.iden = iden;
	}
	public String getCod() {
		return cod;
	}
	public void setCod(String cod) {
		this.cod = cod;
	}

}
