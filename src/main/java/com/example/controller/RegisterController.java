package com.example.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.entiry.Farmer;
import com.example.entiry.Register;
import com.example.helper.Message;
import com.example.repository.FarmerRepository;
import com.example.repository.RegisterRepository;

@Controller
public class RegisterController {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private RegisterRepository regRepo;
	
	@Autowired
	private FarmerRepository farmRepo;

	@RequestMapping("/")
	public String indexPage(Model model) {
		model.addAttribute("title", "Agri Bazar");
		return "index";
	}

	@RequestMapping("/about")
	public String about(Model model) {
		model.addAttribute("about", "Agri Bazar");
		return "about";
	}

	@RequestMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("signup", "Agri Bazar");
		return "Registration";
	}

	// handler for register user
	@PostMapping("/register")
	public String register(@Validated @ModelAttribute("register") Register register,
			@RequestParam(value = "agreement", defaultValue = "false") boolean agreement, Model model,
			HttpSession session, BindingResult result1) {
		try {
			if (!agreement) {
				System.out.println("You have not agree the term and conditions");
				throw new Exception("You have not agree the term and conditions");
			}

			if (result1.hasErrors()) {
				return "Registration";
			}

//			register.setType("ROLE_USER");
			register.setType(register.getType());
			register.setPassword(passwordEncoder.encode(register.getPassword()));

			System.out.println("Agreement " + agreement);
			System.out.println("REGISTER " + register);

			Register result = this.regRepo.save(register);
			model.addAttribute("Register", new Register());

			session.setAttribute("message", new Message("SuccessFully Register!!", "alert-error"));
			return "Login";

		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("Register", register);
			session.setAttribute("message", new Message("Something went Wrong!!" + e.getMessage(), "alert-danger"));
			return "Login";
		}

	}

	// handler for login
	@RequestMapping("/signin")
	public String login(Register register,Model model) {
		model.addAttribute("Register",new Register());
		return "Login";
	}

//	@GetMapping("/login") 
//	public String customLogin(Model model) {
//		  model.addAttribute("title","Login Page"); 
//		  model.addAttribute("user", new User());
//		  return "login"; 
//		  }
//	 
	
	@RequestMapping(value ="/dologin", method =RequestMethod.POST)
	public String loginsuccess(@ModelAttribute("register") Register register,  Model model,HttpSession session) {
		
		System.out.println("From "+register);
		System.out.println(register.getPassword());
		
		Register us = regRepo.getRegisterByRegisterName(register.getUsername());
		System.out.println("DOLOGIN USER"+us);
		
	
		String url="";
		if ((us.getUsername().equals(register.getUsername()) && (passwordEncoder.matches(register.getPassword(), us.getPassword() ))))
		{
		System.out.println("true");
			
		if(us.getType().equals("farmer")) {
			System.out.println("true");
			url="farmer/F_home";
			
		}else if(us.getType().equals("buyer")) {
			System.out.println("true");
			url="b_home";
		}else {
			
			url="login";
		}

		}
		return url;	
	}
	
	
	
	
}
