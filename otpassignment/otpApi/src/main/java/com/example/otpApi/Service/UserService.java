package com.example.otpApi.Service;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.otpApi.Component.EmailUtil;
import com.example.otpApi.Component.OtpUtils;
import com.example.otpApi.Dto.LoginDto;
import com.example.otpApi.Dto.RegisterDto;
import com.example.otpApi.Entity.User;
import com.example.otpApi.Reposatory.Userrepo;

import jakarta.mail.MessagingException;

@Service
public class UserService {
	
	@Autowired
	OtpUtils otpUtils;
	
	@Autowired
	EmailUtil emailUtil;
	
	@Autowired
	Userrepo userrepo;
public String register(RegisterDto regDto) {
	String otp=otpUtils.genrateotp();
	try {
	emailUtil.sendOtp(regDto.getEmail(), otp); 
	}catch(MessagingException e) {
		throw new RuntimeException("Some thing went wrong please try again");
	}
	User user=new User();
	user.setEmail(regDto.getEmail());
	user.setName(regDto.getName());
	user.setPassword(regDto.getPassword());
	user.setOtp(otp);
	user.setOtpgenrateTime(LocalDateTime.now());
	return otp;
}
public String verifyAccount(String email,String otp) {
	  User user = userrepo.findByEmail(email)
		        .orElseThrow(() -> new RuntimeException("User not found with this email: " + email));
	  if (user.getOtp().equals(otp) && Duration.between(user.getOtpgenrateTime(),
		        LocalDateTime.now()).getSeconds() < 60) {
		    user.setActive(true);
		    userrepo.save(user);
		    return "OTP verified Succesfully , you can login Now ";
		}
		return "Please regenerate OTP and try again";


};

public String regenrateotp(String Email) {
	 User user = userrepo.findByEmail(Email)
		        .orElseThrow(() -> new RuntimeException("User not found with this email: " + Email));
	 String otp=otpUtils.genrateotp();
		try {
		emailUtil.sendOtp(Email, otp);
		}catch(MessagingException e) {
			throw new RuntimeException("Some thing went wrong please try once again");
		}
		user.setOtp(otp);
		user.setOtpgenrateTime(LocalDateTime.now());
	 userrepo.save(user);
	 return "Otp regenrated Please login within 60 seconds";
	
}

public String Login(LoginDto logdto) {
	 User user = userrepo.findByEmail(logdto.getEmail())
		        .orElseThrow(() -> new RuntimeException("User not found with this email: " + logdto.getEmail()));

if(!user.getPassword().equals(logdto.getPassword())) return "Please enter Correct Password";
return "Login Successfull";
}

}
