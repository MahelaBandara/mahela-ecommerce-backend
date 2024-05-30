package com.mahela.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mahela.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	public User findByEmail(String email);
	
}
