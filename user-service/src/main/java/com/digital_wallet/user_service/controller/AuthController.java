package com.digital_wallet.user_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digital_wallet.user_service.config.JwtUtil;
import com.digital_wallet.user_service.dto.AuthRequestDTO;
import com.digital_wallet.user_service.dto.AuthResponseDTO;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	private AuthenticationManager authManager;
	private JwtUtil jwtUtil;
	
	@Autowired
	public AuthController(AuthenticationManager authenticationManager,JwtUtil jwtUtil) {
			this.authManager = authenticationManager;
			this.jwtUtil = jwtUtil;
	}
	
	@PostMapping("/login")
	public AuthResponseDTO login(@RequestBody AuthRequestDTO request) {
		
		
		 Authentication authentication = authManager.authenticate(
		            new UsernamePasswordAuthenticationToken(
		                request.getEmail(),
		                request.getPassword()
		            )
		        );
		 
		 String email = authentication.getName();
		 String token = jwtUtil.generateToken(email);
		 
		 
		 return new AuthResponseDTO(token);
		
		
	}
	
}
