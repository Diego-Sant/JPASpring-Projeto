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
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_category")
public class Category implements Serializable {
	
	// Serializable serve para quando precisamos enviar objetos pela rede ou salvar no disco
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Id do cliente será gerado automaticamente pelo banco de dados
	private Long id;
	private String name;
	
	//@Transient Usado para o programa não tentar interpretar o código pois ainda não está completo
	// O Set é uma interface e não pode ser instanciado, por isso usado HashSet
	// O Set é uma coleção que garante que um produto não tenha a mesma categoria mais de uma vez
	@JsonIgnore // Arrumar problema de looping do ManyToMany
	@ManyToMany(mappedBy = "categories")
	private Set<Product> products = new HashSet<>();
	// Usado HashSet para garantir que a categoria não inicie como nula, precisa estar vazia porém instanciada;
	
	public Set<Product> getProducts() {
		return products;
	}
	
	public Category() {
	}

	public Category(Long id, String name) {
		this.id = id;
		this.name = name;
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
		Category other = (Category) obj;
		return Objects.equals(id, other.id);
	}
	
}
