package com.example.otpApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.otpApi.Dto.RegisterDto;
import com.example.otpApi.Service.UserService;

@RestController
public class UserController {
@Autowired
UserService usrser;

	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody RegisterDto regDto)
	{
		return new ResponseEntity<>(usrser.register(regDto),HttpStatus.OK);
	}
	
	@PutMapping("/verify-account")
	public ResponseEntity<String> verifyAccount(@RequestParam String email,@RequestParam String  otp){
		return null;
	}
	
}
