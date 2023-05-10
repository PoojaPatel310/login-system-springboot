package com.aurosoft.loginsystem.service;

import java.util.List;

import com.aurosoft.loginsystem.entity.User;

public interface UserService {
	
	List<User> listAllUsers();
	User getUserById(int id);
	User insertUser(User user);
	User updateUser(User user);
	int deleteUser(int id);
	User findByEmailAndPassword(String email,String password);
	boolean existsByEmail(String email);
	User findByEmail(String email);
   

}
