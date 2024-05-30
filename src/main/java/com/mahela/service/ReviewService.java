package com.mahela.service;

import java.util.List;

import com.mahela.exceptions.ProductException;
import com.mahela.model.Review;
import com.mahela.model.User;
import com.mahela.request.ReviewRequest;

public interface ReviewService {
	
	public Review createReview(ReviewRequest req,User user) throws ProductException;
	public List<Review>getAllReview(Long productId);
	
	
}
