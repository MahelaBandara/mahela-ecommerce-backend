package com.mahela.service;

import com.mahela.exceptions.ProductException;
import com.mahela.model.Cart;
import com.mahela.model.User;
import com.mahela.request.AddItemRequest;

public interface CartService {
	
	public Cart createCart(User user);
	
	public String addCartItem(Long userId,AddItemRequest req) throws ProductException;
	
	public Cart findUserCart(Long userId);
}
