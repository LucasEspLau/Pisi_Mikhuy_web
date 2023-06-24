package com.fia.sem7.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="carritos")
public class Carrito implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	

	@Column(name="cantidad")
	private Integer cant;


	@Column(name="descuento")
	private Double descuento;
	@Column(name="pago")
	private double pago;
	
	@Column(name="precio_final")
	private double precioF;
	
	@Column(name="total_con_descuento")
	private double totDes;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="cliente_id")
	private Cliente cliente;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="carrito_id")
	private List<ItemCarrito> items;
	
	


	public Integer getCant() {
		return cant;
	}

	public void setCant(Integer cant) {
		this.cant = cant;
	}


	

	public Double getDescuento() {
		return descuento;
	}

	public void setDescuento(Double descuento) {
		this.descuento = descuento;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public void addItemCarrito(ItemCarrito item) {
		this.items.add(item);
	}

	public List<ItemCarrito> getItems() {
		return items;
	}

	public void setItems(List<ItemCarrito> items) {
		this.items = items;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}
	
	
	public double getPago() {
		return pago;
	}

	public void setPago(double pago) {
		this.pago = pago;
	}

	
	
	public double getTotDes() {
		return totDes;
	}

	public void setTotDes(double totDes) {
		this.totDes = totDes;
	}
	
	public double getPrecioF() {
		return precioF;
	}

	public void setPrecioF(double precioF) {
		this.precioF = precioF;
	}


	public Carrito() {
		this.items =new ArrayList<ItemCarrito>();
	}

}
