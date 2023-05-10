package com.aurosoft.loginsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.aurosoft.loginsystem.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	
	User findByEmailAndPassword(String email,String password);
	boolean existsByEmail(String email);
	User findByEmail(String email);
	

}
