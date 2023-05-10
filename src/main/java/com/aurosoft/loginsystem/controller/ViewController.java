package com.aurosoft.loginsystem.controller;

import com.aurosoft.loginsystem.Helper;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.aurosoft.loginsystem.PasswordMatches;
import com.aurosoft.loginsystem.entity.ResetPasswordForm;
import com.aurosoft.loginsystem.entity.User;
import com.aurosoft.loginsystem.service.UserService;

import jakarta.validation.Valid;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Date;

@Controller
public class ViewController {
	
	@Autowired
	private UserService userService;	

	public ViewController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	

	@GetMapping("/login")
	public String login() {

		return "login";
	}

	@PostMapping("/login")
	public String checkLogin(@RequestParam("email") String email,
							 @RequestParam("password") String password,
							 HttpSession session)
		{		
			User user = userService.findByEmailAndPassword(email, password);

			if (user != null) {
				session.setAttribute("uname", user.getFname() + "   " + user.getLname());
				session.setAttribute("fname", user.getFname());
				session.setAttribute("uid", user.getId());
				session.setAttribute("urole", user.getRole());

//				System.out.println("---------");
//				System.out.println(user + "" + session.getAttribute("urole"));
//				System.out.println("---------");

				if (Helper.checkUserRole()) {
					session.setAttribute("msg", "You are successfully login..");
					return "redirect:/profile";

				} else {

					session.setAttribute("msg", "Something Went Wrong..");
					return "redirect:/logout";
				}
			}else
			{
				session.setAttribute("msg","Wrong Username or Password..");
				return "redirect:/login";
			}
	}
	
	
	
	@GetMapping(value= "/profile")
	public String profile(Model m , HttpSession session){


		if(!Helper.checkUserRole()){
		return "redirect:/login ";
	}

//		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
//		HttpSession session = attr.getRequest().getSession();
		int uid=0;
		if(session.getAttribute("uid") != null) {
			uid = (int)session.getAttribute("uid");
		}
		User user = userService.getUserById(uid);
		m.addAttribute("user",user);

		return "user/profile";
	}
	

	@GetMapping(value= "/editprofile")
	public String editProfile(Model m) {

		if(!Helper.checkUserRole()){
			return "redirect:/login ";
		}

		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession();
		int uid=0;
		if(session.getAttribute("uid") != null) {
			uid = (int)session.getAttribute("uid");
		}
		User user = userService.getUserById(uid);
		m.addAttribute("user",user);

		return "user/edit_profile";
	}
	
	@PostMapping(value = "/update-profile")
	public String updateProfile(@ModelAttribute("user")User user,HttpSession session) {

		User user2 =  userService.getUserById(user.getId());
		
		user2.setFname(user.getFname());
		user2.setLname(user.getLname());
		user2.setEmail(user.getEmail());
		user2.setPhone(user.getPhone());		
		
		userService.updateUser(user2);
		session.setAttribute("msg", "Your Profile is successfully updated..");
	return "redirect:/profile";
	}
	


	@GetMapping("/index")
	public String index() {
		return "index";
	}

	@GetMapping("/signup")
	public String signup(User user) {
		return "sign_up";
	}

	@PostMapping("/new-signup")
	public String newSignup(@ModelAttribute("user")User user,HttpSession session) {

		user.setRegDate(new Date());

		userService.insertUser(user);
		session.setAttribute("msg", "Sign up successfully!! Now you can login..");
		return "redirect:/login";
	}

	@GetMapping("/forgot-password")
	public String forgotPassword(User user) {
		return "forgot_password";
	}
	
	@PostMapping("/update-forgot-password")
	public String updateForgotPassword(@RequestParam("email") String email , HttpSession session)
	{
		User user = userService.findByEmail(email);

		if (user != null) {
			session.setAttribute("email",email);
				return "redirect:/reset-password";

			} else {

				session.setAttribute("msg", "Something Went Wrong..");
				return "redirect:/forgot-password";
			}
	}

	@GetMapping("/reset-password")
	public String resetPassword() {
		return "reset_password";
	}
	

	 @PostMapping("/reset-password1")
	    public String resetPassword(@RequestParam("password") String password,
									@RequestParam("cpassword") String cpassword,
									HttpSession session){

		if(password.equals(cpassword)){
			String email = session.getAttribute("email" ).toString();
			User user = userService.findByEmail(email);
			user.setPassword(password);
			user = userService.updateUser(user);
			if (user!= null){
				session.setAttribute("msg","Password Reset");
				return "redirect:/login";
			}
			else {
				session.setAttribute("msg","Something went wrong!!");
				return "redirect:/reset-password";
			}
		}else {
			session.setAttribute("msg","Password and Confirm password not match");
			return "redirect:/reset-password";
		}

	    }
	


	@GetMapping("/change-password")
	public String changePassword() {
		return "user/change_password";
	}

	@PostMapping("/update-change-password")
	public String updateChangePassword(@RequestParam("oldpassword") String oldpassword,
									   @RequestParam("password") String password,
									   @RequestParam("cpassword") String cpassword,
										HttpSession session){
		String email = session.getAttribute("email" ).toString();
		User user = userService.findByEmail(email);

		if (!user.getPassword().equals(oldpassword)){
		session.setAttribute("msg","Old Password is not matched");
		return "redirect:/change-password";
		}

		if(password.equals(cpassword)){

			user.setPassword(password);
			user = userService.updateUser(user);
			if (user!= null){
				session.setAttribute("msg","Password Reset Successfully");
				return "redirect:/login";
			}
			else {
				session.setAttribute("msg","Something went wrong!!");
				return "redirect:/reset-password";
			}
		}else {
			session.setAttribute("msg","Password and Confirm password is not matched");
			return "redirect:/reset-password";
		}

	}

	@GetMapping("/logout")
	public String logout() {

		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession();

		if( session.getAttribute("uname") != null)
			session.removeAttribute("uname");

		if(session.getAttribute("uid") != null)
			session.removeAttribute("uid");
		if(session.getAttribute("urole") != null)
			session.removeAttribute("urole");

		session.setAttribute("msg","You are successfully logged-out from system..");
		return "redirect:/login";
	}



	
}
