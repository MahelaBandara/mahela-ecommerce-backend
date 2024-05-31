package com.mahela.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;


import com.mahela.config.JwtProvider;
import com.mahela.exceptions.UserException;
import com.mahela.model.Cart;
import com.mahela.model.User;
import com.mahela.repository.UserRepository;
import com.mahela.request.LoginRequest;
import com.mahela.response.AuthResponse;
import com.mahela.service.CartService;
import com.mahela.service.CustomUserServiceImplementation;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	private UserRepository userRepository;
	private JwtProvider jwtProvider;
	private PasswordEncoder passwordEncoder;
	private CustomUserServiceImplementation customUserService;
	private CartService cartService;
	
	public AuthController(UserRepository userRepository,CustomUserServiceImplementation customUserService,PasswordEncoder passwordEncoder,JwtProvider jwtProvider,CartService cartService) {
		this.userRepository = userRepository;
		this.customUserService = customUserService;
		this.passwordEncoder = passwordEncoder;
		this.jwtProvider = jwtProvider;
		this.cartService = cartService;
	}
	
	@PostMapping("/signup")
	@CrossOrigin(origins = "https://mahela-e-commerce-full-stack.vercel.app")
	public ResponseEntity<AuthResponse>createUserHandle(@RequestBody User user)throws UserException{
		String email = user.getEmail();
		String password = user.getPassword();
		String firstName = user.getFirstName();
		String lastName = user.getLastName();
		
		User isEmailExist = userRepository.findByEmail(email);
		
		if(isEmailExist != null) {
			throw new UserException("Email is Already used with Another Account");
		}
		
		
		User createdUser = new User();
		createdUser.setEmail(email);
		createdUser.setPassword(passwordEncoder.encode(password) );
		createdUser.setFirstName(firstName);
		createdUser.setLastName(lastName);
		
		User savedUser = userRepository.save(createdUser);
		Cart cart = cartService.createCart(savedUser);
		
		Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(), savedUser.getPassword());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String token = jwtProvider.generateToken(authentication);
		
		AuthResponse authResponse = new AuthResponse();
		authResponse.setJwt(token);
		authResponse.setMessage("signup success");
		
		return new ResponseEntity<AuthResponse>(authResponse,HttpStatus.CREATED);
	}
	
	@PostMapping("/signin")
	@CrossOrigin(origins = "https://mahela-e-commerce-full-stack.vercel.app")
	public  ResponseEntity<AuthResponse>loginUserHandler(@RequestBody LoginRequest loginRequest){
		
		String username = loginRequest.getEmail();
		String password = loginRequest.getPassword();
		
		Authentication authentication = authenticate(username,password);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String token = jwtProvider.generateToken(authentication);
		
		AuthResponse authResponse = new AuthResponse(token, "signin success");
		authResponse.setJwt(token);
		authResponse.setMessage("signin success");
		
		return new ResponseEntity<AuthResponse>(authResponse,HttpStatus.CREATED);
		
	}
	
	private Authentication authenticate(String username,String password) {
		
		UserDetails userDetails = customUserService.loadUserByUsername(username);
		
		if (userDetails==null) {
			throw new BadCredentialsException("Invalid username...");		
		}
		
		if (!passwordEncoder.matches(password, userDetails.getPassword())) {
			throw new BadCredentialsException("Invalid password...");
		}
		
		
		
		return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
		
	}
}
