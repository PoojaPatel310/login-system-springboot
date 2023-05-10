package com.aurosoft.loginsystem.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aurosoft.loginsystem.entity.User;
import com.aurosoft.loginsystem.service.UserService;


@Controller
@RequestMapping("/user")
public class UserController {

	
	@Autowired
	private	UserService userService;

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@GetMapping("/list")
	public String listUser(Model m) {
		List<User> list = userService.listAllUsers();
		m.addAttribute("list",list);
		return "admin/user_list";
	}
	
	
	@GetMapping("/new")
	public String showForm(User user) {
			
		return "/admin/user_add";
	}
	
	@PostMapping("/insert")
	public String insertUser(@ModelAttribute("user")User user) {
	
	user.setRegDate(new Date());
	
	userService.insertUser(user);
	return "redirect:/user/list";
	}
	
	@GetMapping(value= "/edit/{id}")
	public String edit(@PathVariable int id,Model m) {
		
		User user = userService.getUserById(id);
		m.addAttribute("user",user);
		
		return "/admin/user_edit";
	}
	
	@PostMapping(value = "/update")
	public String updateUser(@ModelAttribute("user")User user) {
		user.setRegDate(new Date());
	userService.updateUser(user);
	return "redirect:/user/list";
	}

	@GetMapping(value ="/delete/{id}")
	public String deleteUser(@PathVariable int id , Model m) {
	userService.deleteUser(id);
	return "redirect:/user/list";
	}
	
	
}
