package com.courseproject.projetoJPA.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.courseproject.projetoJPA.entities.User;

// @Repository não é necessário por conta do JpaRepository
public interface UserRepository extends JpaRepository<User, Long> {

}
