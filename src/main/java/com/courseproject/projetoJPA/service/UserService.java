package com.courseproject.projetoJPA.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.courseproject.projetoJPA.entities.User;
import com.courseproject.projetoJPA.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	// Achar com o /users - Usado em UserResource
	public List<User> findAll() {
		return repository.findAll();
	}
	
	// Achar com o /users/{id} - Usado em UserResource
	public User findById(Long id) {
		Optional<User> obj = repository.findById(id);
		return obj.get();
	}
	
	// Adicionar um usuário - Usado em UserResource
	public User insert(User obj) {
		return repository.save(obj);
	}

}
