package com.mahela.service;

import java.util.List;

import com.mahela.exceptions.ProductException;
import com.mahela.model.Rating;
import com.mahela.model.User;
import com.mahela.request.RatingRequest;

public interface RatingService  {
	
	public Rating createRating(RatingRequest req,User user) throws ProductException;
	
	public List<Rating>getProductsRating(Long productId);
	
	
	
}
