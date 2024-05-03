package com.ritik.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ritik.models.JwtUtil;
import com.ritik.models.User;
import com.ritik.service.SignupService;

@RestController
@RequestMapping("/user")
public class SignupController
{
	@Autowired
	private SignupService signupService;
	
    @Autowired
    private JwtUtil jwtUtil;

	
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@RequestBody User user)
	{
		signupService.registerUser(user);
		return ResponseEntity.ok("Signup Successfully");
	}
	
	
	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody User user) {
	    boolean isValidUser = signupService.loginUser(user.getEmail(), user.getPassword());
	    if (isValidUser) {
	    	
            final String token = jwtUtil.generateToken(user.getEmail());
            return ResponseEntity.ok("Access Tokens :"+token);
            
	    } else {
	        return ResponseEntity.badRequest().body("Invalid Credentials");
	    }
	}

}
