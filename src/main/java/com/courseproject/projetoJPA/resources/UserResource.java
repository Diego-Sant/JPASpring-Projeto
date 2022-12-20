package com.courseproject.projetoJPA.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.courseproject.projetoJPA.entities.User;
import com.courseproject.projetoJPA.service.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired
	private UserService service;
	
	// Buscar todos os usuários no /users
	@GetMapping // Get pois você busca pelo usuário
	public ResponseEntity<List<User>> findAll() {
		List<User> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	// Buscar id dos usuários no /users/{id}
	@GetMapping(value = "/{id}") // Get pois você busca pelo usuário
	public ResponseEntity<User> findById(@PathVariable Long id) {
		User obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	// Adicionar um usuário
	@PostMapping // Post pois irá adicionar um usuário
	public ResponseEntity<User> insert(@RequestBody User obj) {
		obj = service.insert(obj);
		
		// Retornar HTTP 201 Created ao em vez de 200 OK
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	// <Void> pois como é deletar um usuário a responsa da inquisição não irá retornar nenhum corpo
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
