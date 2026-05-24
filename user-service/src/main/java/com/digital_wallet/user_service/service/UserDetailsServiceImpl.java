package com.digital_wallet.user_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.digital_wallet.user_service.model.User;
import com.digital_wallet.user_service.repository.UserRepository;


//UserDetails service is a Spring security interface.
//loadUserbyusername is a method which validates requests.

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	
	private UserRepository userRepo;
	
	@Autowired
	public UserDetailsServiceImpl(UserRepository userRepo) {
		this.userRepo=userRepo;
	}
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		User user = userRepo.findByEmail(email).orElseThrow(() -> new RuntimeException("User with email not found " +email));
		
		return org.springframework.security.core.userdetails.User
				.withUsername(user.getEmail())
				.password(user.getPassword())
				.roles(user.getRole())
				.build();
	}
	
	
	
	

}
