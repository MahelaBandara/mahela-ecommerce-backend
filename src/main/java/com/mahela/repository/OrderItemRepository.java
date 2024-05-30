package com.mahela.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mahela.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{
	
}
