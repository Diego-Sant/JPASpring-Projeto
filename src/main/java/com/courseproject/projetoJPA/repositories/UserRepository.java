package com.courseproject.projetoJPA.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.courseproject.projetoJPA.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
