package com.fia.sem7.models.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;



import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="clientes")
public class Cliente implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	@Column(name="nombres")
	private String nom;
	
	@NotEmpty
	@Column(name="identidad")
	private String iden;
	
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
	
	
	
	@NotNull
	@Column(name="fecha_nacimiento")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date fecNac;
	
	@PrePersist
	public void prePersist() {
		fecCre=new Date();
	}
	@Column(name = "fecha_creacion")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecCre;
	
	
	@OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Carrito> carritos;
	
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

	public Date getFecNac() {
		return fecNac;
	}
	public void setFecNac(Date fecNac) {
		this.fecNac = fecNac;
	}
	public Date getFecCre() {
		return fecCre;
	}
	public void setFecCre(Date fecCre) {
		this.fecCre = fecCre;
	}
	public String getIden() {
		return iden;
	}
	public void setIden(String iden) {
		this.iden = iden;
	}
	public List<Carrito> getCarritos() {
		return carritos;
	}
	public void setCarritos(List<Carrito> carritos) {
		this.carritos = carritos;
	}
	
	
	
}
