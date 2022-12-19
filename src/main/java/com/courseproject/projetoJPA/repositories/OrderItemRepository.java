package com.courseproject.projetoJPA.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.courseproject.projetoJPA.entities.OrderItem;

// @Repository não é necessário por conta do JpaRepository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
