package com.mahela.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mahela.exceptions.CartItemException;
import com.mahela.exceptions.UserException;
import com.mahela.model.Cart;
import com.mahela.model.CartItem;
import com.mahela.model.Product;
import com.mahela.model.User;
import com.mahela.repository.CartItemRepository;
import com.mahela.repository.CartRepository;


@Service
public class CartItemServiceImplementation implements CartItemService {
	
	private CartItemRepository cartItemRepository;
	private UserService userService;
	
	public CartItemServiceImplementation(CartItemRepository cartItemRepository,UserService userService,CartRepository cartRepository) {
		this.cartItemRepository = cartItemRepository;
		this.userService = userService;
	}
	
	@Override
	public CartItem createCartItem(CartItem cartItem) {
		cartItem.setQuantity(1);
		cartItem.setPrice(cartItem.getProduct().getPrice() * cartItem.getQuantity());
		cartItem.setDiscountedPrice(cartItem.getProduct().getDiscountedPrice() * cartItem.getQuantity());
		
		CartItem createdCartItem = cartItemRepository.save(cartItem);
		
		return createdCartItem;
	}

	@Override
	public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException {
		CartItem item = findCartItemByid(id);
		User user = userService.findUserById(item.getUserId());
		
		if(user.getId().equals(userId)) {
			item.setQuantity(cartItem.getQuantity());
			item.setPrice(item.getProduct().getPrice() * item.getQuantity() );
			item.setDiscountedPrice(item.getProduct().getDiscountedPrice() * item.getQuantity());
			
		}
		
		return cartItemRepository.save(item);
	}

	@Override
	public CartItem isCartItemExist(Cart cart, Product product, String size, Long userId) {
		
		CartItem cartItem = cartItemRepository.isCartItemExist(cart, product, size, userId);
		
		return cartItem;
	}

	@Override
	public void removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException {
		CartItem cartItem = findCartItemByid(cartItemId);
		
		User user = userService.findUserById(cartItem.getUserId());
		
		User reqUser = userService.findUserById(userId);
		
		if(user.getId().equals(reqUser.getId())) {
			cartItemRepository.deleteById(cartItemId);
		}
		else {
			throw new UserException("you can't remove another users' items");
		}
	}

	@Override
	public CartItem findCartItemByid(Long cartItemId) throws CartItemException {
		Optional<CartItem> opt = cartItemRepository.findById(cartItemId);
		
		if(opt.isPresent()) {
			return opt.get();
		}
		
		throw new CartItemException("cart item not found with id :"+cartItemId);
	
	}

}
