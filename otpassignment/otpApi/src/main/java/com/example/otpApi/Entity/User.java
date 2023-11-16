package com.example.otpApi.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String email;
	private String password;
	private boolean active;
	private String otp;
	private LocalDateTime otpgenrateTime;
	public User(String name, String email, String password, boolean active, String otp, LocalDateTime otpgenrateTime) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.active = active;
		this.otp = otp;
		this.otpgenrateTime = otpgenrateTime;
	}
	

}
