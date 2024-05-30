package com.mahela.service;

import com.mahela.exceptions.CartItemException;
import com.mahela.exceptions.UserException;
import com.mahela.model.Cart;
import com.mahela.model.CartItem;
import com.mahela.model.Product;


public interface CartItemService {
	
	public CartItem createCartItem(CartItem cartItem);
	
	public CartItem updateCartItem(Long userId,Long id,CartItem cartItem) throws CartItemException,UserException;
	
	public CartItem isCartItemExist(Cart cart,Product product,String size,Long userId);
	
	public void removeCartItem(Long userId,Long cartItemId) throws CartItemException,UserException;
	
	public CartItem findCartItemByid(Long cartItemId) throws CartItemException;
	
}
