package com.ritik.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ritik.models.User;
import com.ritik.repository.SignupRepository;

@Service
public class SignupService
{
	@Autowired
	private SignupRepository signupRepository;

	public void registerUser(User user)
	{
	   signupRepository.saveUser(user);	
	}

	public boolean loginUser(String email, String password)
	{
		User user = signupRepository.findUserByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return true;
        }
        return false;
	}
	

}
