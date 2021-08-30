package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.entiry.Register;
import com.example.repository.RegisterRepository;

public class UserDetailServiceImp implements UserDetailsService {

	@Autowired
	private RegisterRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		// fetching user from database
		Register register = userRepo.getRegisterByRegisterName(username);

		if (register == null) {
			throw new UsernameNotFoundException("Could not found user");
		}

		CustomRegisterDetails customRegisterDetails = new CustomRegisterDetails(register);

		return customRegisterDetails;
		
		

//		Register register = userRepo.getRegisterByRegisterName(username);
//		CustomRegisterDetails userDetails = null ;
//		
//		if (register != null)
//		{
//			userDetails = new CustomRegisterDetails();
//			userDetails.setRegister(register);
//		}
//		
		
		
		
	}

}
