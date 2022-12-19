package com.courseproject.projetoJPA.entities;

import java.io.Serializable;
import java.util.Objects;

import com.courseproject.projetoJPA.entities.pk.OrderItemPK;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_order_item")
public class OrderItem implements Serializable {
	
	// Serializable serve para quando precisamos enviar objetos pela rede ou salvar no disco
	private static final long serialVersionUID = 1L;

	@EmbeddedId // Por ser um id composto, se utiliza @EmbeddedId ao em vez de @Id
	private OrderItemPK id = new OrderItemPK();
	// Precisa instaciar para o valor não ser nulo;
	
	private Integer quantity;
	private Double price;
	
	public OrderItem() {
	}
	
	// Id não é utilizado nos construtores e no getters and setters

	//Order e Product adicionados manualmente
	public OrderItem(Order order, Product product, Integer quantity, Double price) {
		super();
		id.setOrder(order);
		id.setProduct(product);
		
		this.quantity = quantity;
		this.price = price;
	}
	
	
	//Getters and Setters de Order e Product adicionados manualmente
	@JsonIgnore // É o getOrder que chama o pedido
	public Order getOrder() {
		return id.getOrder();
	}
	
	public void setOrder(Order order) {
		id.setOrder(order);
	}
	
	public Product getProduct() {
		return id.getProduct();
	}
	
	public void setProduct(Product product) {
		id.setProduct(product);
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderItem other = (OrderItem) obj;
		return Objects.equals(id, other.id);
	}
	
}
