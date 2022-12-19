package com.courseproject.projetoJPA.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.courseproject.projetoJPA.entities.Category;

// @Repository não é necessário por conta do JpaRepository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
