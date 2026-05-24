package com.digital_wallet.user_service.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.digital_wallet.user_service.client.WalletServiceClient;
import com.digital_wallet.user_service.dto.CreateWalletRequest;
import com.digital_wallet.user_service.model.User;
import com.digital_wallet.user_service.repository.UserRepository;

import jakarta.transaction.Transactional;


@Service
public class UserService {
	
	private  UserRepository userRepo;
	private WalletServiceClient walletServiceClient;
	private PasswordEncoder passEncoder;
	@Autowired
	UserService(UserRepository userRepo,PasswordEncoder passEncoder,WalletServiceClient walletServiceClient){
		this.userRepo = userRepo;
		this.passEncoder = passEncoder;
		this.walletServiceClient=walletServiceClient;

	}
	
	
	
	//Saves user + auto creates a wallet for them.
	
	@Transactional
	public User registerUser(User user) {
		
		//Encrypting password before saving in the DB.
		user.setPassword(passEncoder.encode(user.getPassword()));
		
		User savedUser = userRepo.save(user);

		walletServiceClient.createWallet(new CreateWalletRequest(savedUser.getId()));
		return savedUser;	
	}
	
	public User getUserById(Long id) {
		
		User foundedUser = userRepo.findById(id).orElseThrow(()->new RuntimeException("User not found"));
		
		
		return foundedUser;
	}
	
	public List<User> getAllUsers(){
		return userRepo.findAll();
	}
	
}
