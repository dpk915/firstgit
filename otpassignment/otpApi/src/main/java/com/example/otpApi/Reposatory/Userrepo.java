package com.example.otpApi.Reposatory;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.otpApi.Entity.User;

public interface Userrepo extends JpaRepository<User, Long>{
	Optional<User> findByEmail(String email);
}
