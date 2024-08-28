package com.crio.rentread.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crio.rentread.config.SecurityConfig;
import com.crio.rentread.dto.UserLoginDetails;
import com.crio.rentread.dto.UserRegistrationDetails;
import com.crio.rentread.entity.UserDetails;
import com.crio.rentread.exception.UserNotFoundException;
import com.crio.rentread.repository.RentReadRepository;

@Service
public class RentReadService {

	@Autowired
	RentReadRepository rentReadRepository;

	@Autowired
	SecurityConfig securityConfig;

	public boolean storeUserRegistrationDetails(UserRegistrationDetails userRegistrationDetails) {
		String encodedPassword = securityConfig.passwordEncoder().encode(userRegistrationDetails.getPassword());
		UserDetails userDetails = new UserDetails(userRegistrationDetails.getFirstName(),
				userRegistrationDetails.getLastName(), userRegistrationDetails.getEmail(), encodedPassword);
		UserDetails userDetailsObj = rentReadRepository.save(userDetails);
		if (userDetailsObj != null)
			return true;
		else
			return false;
	}

	public boolean authenticateUser(UserLoginDetails userLoginDetails) throws UserNotFoundException {
		Optional<UserDetails> optionalUserDetails = rentReadRepository.findById(userLoginDetails.getEmail());
		if (optionalUserDetails.isEmpty())
			throw new UserNotFoundException("no user present with email " + userLoginDetails.getEmail());

		return securityConfig.passwordEncoder().matches(userLoginDetails.getPassword(),
				optionalUserDetails.get().getPassword());
	}

}
