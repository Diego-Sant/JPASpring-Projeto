package com.courseproject.projetoJPA.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.courseproject.projetoJPA.entities.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_order")
public class Order implements Serializable {

	// Serializable serve para quando precisamos enviar objetos pela rede ou salvar no disco
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Id do pedido será gerado automaticamente pelo banco de dados
	private Long id;

	// Instant é uma versão melhorada do Date
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant moment;

	// Dentro do programa será utilizado o id do status
	private Integer orderStatus;
	
	//------------------------------------------------------------------

	// (- client | 1) significa que o pedido só pode ter 1 cliente
	@ManyToOne // Muitos pedidos para apenas um cliente
	@JoinColumn(name = "client_id") // Adicionar id do cliente na tabela
	private User client;
	
	//------------------------------------------------------------------
	
	@OneToMany(mappedBy = "id.order") // Usado id pois é pego no private OrderItemPK id; que pega o private Order order;
	private Set<OrderItem> items = new HashSet<>();
	
	public Set<OrderItem> getItems() {
		return items;
	}
	
	//------------------------------------------------------------------
	
	// Cascade faz com que o pedido tenha o mesmo id do pagamento
	@OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
	private Payment payment;

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	
	//------------------------------------------------------------------

	public Order() {
	}

	public Order(Long id, Instant moment, OrderStatus orderStatus, User client) {
		this.id = id;
		this.moment = moment;
		setOrderStatus(orderStatus); // Técnica para utilizar o valor Integer dentro do sistema e String públicamente
		this.client = client;
	}
	
	//------------------------------------------------------------------

	// Técnica para utilizar o valor Integer dentro do sistema e String públicamente
	public OrderStatus getOrderStatus() {
		return OrderStatus.valueOf(orderStatus);
	}
	
	public void setOrderStatus(OrderStatus orderStatus) {
		if (orderStatus != null) {
			this.orderStatus = orderStatus.getCode();
		}
	}
	
	//------------------------------------------------------------------

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}

	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}
	
	public Double getTotal() {
		double sum = 0.0;
		for (OrderItem x : items) {
			sum += x.getSubTotal();
		}
		return sum;
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
		Order other = (Order) obj;
		return Objects.equals(id, other.id);
	}

}
