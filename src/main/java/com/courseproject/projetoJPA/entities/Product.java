package com.courseproject.projetoJPA.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_product")
public class Product implements Serializable {

	// Serializable serve para quando precisamos enviar objetos pela rede ou salvar no disco
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Id do cliente será gerado automaticamente pelo banco de dados
	private Long id;
	
	private String name;
	private String description;
	private Double price;
	private String imgUrl;

	//@Transient Usado para o programa não tentar interpretar o código pois ainda não está completo
	@ManyToMany
	@JoinTable(name = "tb_product_category", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
	private Set<Category> categories = new HashSet<>();
	// JoinTable permite adicionar uma tabela e com JoinColumns adicionar colunas nessa mesma tabela
	
	public Set<Category> getCategories() {
		return categories;
	}
	
	@OneToMany(mappedBy = "id.product") // Usado id pois é pego no private OrderItemPK id; que pega o private Product product;
	private Set<OrderItem> items = new HashSet<>();
	
	// Percorrendo a coleção items e pra cada elemento x, adicionar x.getOrder()
	@JsonIgnore // Ao procurar por /orders/{id} a pessoa irá receber detalhes do cliente e de suas compras
	public Set<Order> getOrders() {
		Set<Order> set = new HashSet<>();
		for (OrderItem x : items) {
			set.add(x.getOrder());
		}
		return set;
	}
	
	public Product() {
	}

	// Não se usa coleções em construtores
	public Product(Long id, String name, String description, Double price, String imgUrl) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.imgUrl = imgUrl;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
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
		Product other = (Product) obj;
		return Objects.equals(id, other.id);
	}
	
}
