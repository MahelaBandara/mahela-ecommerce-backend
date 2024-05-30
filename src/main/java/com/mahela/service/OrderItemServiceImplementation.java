package com.mahela.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.mahela.model.OrderItem;
import com.mahela.repository.OrderItemRepository;

public class OrderItemServiceImplementation implements OrderItemService{
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	
	@Override
	public OrderItem createdOrderItem(OrderItem orderItem) {
		
		return orderItemRepository.save(orderItem);
		
	}

}
