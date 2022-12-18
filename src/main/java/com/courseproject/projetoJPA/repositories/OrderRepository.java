package com.courseproject.projetoJPA.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.courseproject.projetoJPA.entities.Order;

// @Repository não é necessário por conta do JpaRepository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
