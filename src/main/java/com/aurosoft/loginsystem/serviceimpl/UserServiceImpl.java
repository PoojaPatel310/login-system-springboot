package com.aurosoft.loginsystem.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurosoft.loginsystem.entity.User;
import com.aurosoft.loginsystem.repository.UserRepository;
import com.aurosoft.loginsystem.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository repository;
	
	@Override
	public List<User> listAllUsers() {
		
		return repository.findAll();
	}

	@Override
	public User getUserById(int id) {
		
		return repository.findById(id).orElseThrow();
	}

	@Override
	public User insertUser(User user) {
		
		return repository.save(user);
	}

	@Override
	public User updateUser(User user) {
		
		return repository.save(user);
	}

	@Override
	public int deleteUser(int id) {
		
		repository.deleteById(id);
		return id;
	}

	@Override
	public User findByEmailAndPassword(String email, String password) {
		
		return repository.findByEmailAndPassword(email, password);
	}

	@Override
	public boolean existsByEmail(String email) {
		
		return repository.existsByEmail(email);
	}

	@Override
	public User findByEmail(String email) {
		
		return repository.findByEmail(email);
	}


	
	
}
