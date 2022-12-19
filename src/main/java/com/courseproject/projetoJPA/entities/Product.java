package com.courseproject.projetoJPA.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

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
	
	// O Set é uma interface e não pode ser instanciado, por isso usado HashSet
	// O Set é uma coleção que garante que um produto não tenha a mesma categoria mais de uma vez
	@Transient // Usado para o programa não tentar interpretar o código pois ainda não está completo
	private Set<Category> categories = new HashSet<>();
	// Usado HashSet para garantir que a categoria não inicie como nula, precisa estar vazia porém instanciada;

	public Set<Category> getCategories() {
		return categories;
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
