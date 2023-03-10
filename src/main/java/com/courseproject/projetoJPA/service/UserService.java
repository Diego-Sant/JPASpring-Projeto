package com.courseproject.projetoJPA.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.courseproject.projetoJPA.entities.User;
import com.courseproject.projetoJPA.repositories.UserRepository;
import com.courseproject.projetoJPA.service.exceptions.DatabaseException;
import com.courseproject.projetoJPA.service.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

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
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	// Adicionar um usuário - Usado em UserResource
	public User insert(User obj) {
		return repository.save(obj);
	}
	
	// Deletar um usuário - Usado em UserResource
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} 
		catch (EmptyResultDataAccessException e) { // Excessão quando coloca um Id que não existe
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) { // Excessão quando tenta deletar um usuário com pedidos realizados
			throw new DatabaseException(e.getMessage());
		}
	}
	
	// Atualizar informações do usuário - Usado em UserResource
	public User update(Long id, User obj) {
		try {
			User entity = repository.getReferenceById(id); // Ao em vez do FindById que busca o Id e já trás para o usuário, o ReferenceById apenas monitora
			updateDate(entity, obj);
			return repository.save(entity);
		}
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateDate(User entity, User obj) {
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
		entity.setPassword(obj.getPassword());
	}

}
