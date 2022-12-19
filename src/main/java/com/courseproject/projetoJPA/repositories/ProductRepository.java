package com.courseproject.projetoJPA.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.courseproject.projetoJPA.entities.Product;

// @Repository não é necessário por conta do JpaRepository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
