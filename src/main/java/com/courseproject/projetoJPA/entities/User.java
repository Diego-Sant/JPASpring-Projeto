package com.courseproject.projetoJPA.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_user") // Criar tabela no h2-console
public class User implements Serializable {
	
	// Serializable serve para quando precisamos enviar objetos pela rede ou salvar no disco
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Id do cliente será gerado automaticamente pelo banco de dados
	private Long id;
	
	private String name;
	private String email;
	private String phone;
	private String password;

	// (- order | *) significa que um cliente pode ter vários pedidos
	@JsonIgnore // Arrumar problema de looping do OneToMany e ManyToOne
	@OneToMany(mappedBy = "client") // Um cliente para muitos pedidos - "client" é o atributo usado no ManyToOne
	private List<Order> orders = new ArrayList<>();
	
	// Coleções(List) não precisam de Set, apenas Get para adicionar e remover
	public List<Order> getOrders() {
		return orders;
	}
	
	public User() {
	}

	public User(Long id, String name, String email, String phone, String password) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.password = password;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email) && Objects.equals(id, other.id);
	}

}