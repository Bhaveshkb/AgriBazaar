package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.tomcat.jni.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.entity.Farmer;
import com.example.entity.Register;
import com.example.helper.Message;
import com.example.repository.FarmerRepository;
import com.example.repository.RegisterRepository;
import com.example.service.EmailService;

@Controller
public class RegisterController {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private RegisterRepository regRepo;

	@Autowired
	private FarmerRepository farmRepo;

	@Autowired
	private EmailService emailService;

	@RequestMapping("/")
	public String indexPage(Model model) {
		model.addAttribute("title", "Agri Bazaar");
		return "index";
	}

	@RequestMapping("/about")
	public String about(Model model) {
		model.addAttribute("about", "Agri Bazaar");
		return "about";
	}

	@RequestMapping("/contact")
	public String contact(Model model) {
		model.addAttribute("contact", "Agri Bazaar");
		return "contact";
	}

	@RequestMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("signup", "Agri Bazaar");
		return "Registration";
	}

	@RequestMapping("/terms")
	public String terms(Model model) {
		return "terms";
	}

	// handler for register user
	@PostMapping("/register")
	public String register(@Validated @ModelAttribute("register") Register register,
			@RequestParam(value = "agreement", defaultValue = "false") boolean agreement, Model model,
			HttpSession session, HttpServletRequest request, HttpServletResponse response, BindingResult result1) {
			try {
			if (!agreement) {
				System.out.println("You have not agree the term and conditions");
				throw new Exception("You have not agree the term and conditions");
			}
			if (result1.hasErrors()) {
				return "Registration";
			}

			// email notification code
			String subject = "Welcome To Agri Bazaar" + " " + register.getFirstName();
			String message = "" + "<div style='border:1px solid #e2e2e2; padding:20px'>" 
			+ "<h1>"	
			+ "you have suscessfully registered to Agri Bazaar :" 
			+ "<br>" 
			+ "USERNAME = "+ register.getUsername() 
			+ "<br>" + "PASSWORD = " + register.getPassword() 
			+ "</n>" 
			+ "<br>"
			+ "Thank You !" 
			+ "</h1> " 
			+ "</div>";
			String to = register.getUsername();

			boolean flag = this.emailService.sendEmail(subject, message, to);
			// email notification code end

			register.setType(register.getType());
			register.setPassword(passwordEncoder.encode(register.getPassword()));

			System.out.println("Agreement " + agreement);
			System.out.println("REGISTER " + register);

			Register result = this.regRepo.save(register);
			model.addAttribute("Register", new Register());

			session.setAttribute("message", new Message("SuccessFully Register!!", "alert-success"));
			return "Registration";

		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
			model.addAttribute("Register", register);
			session.setAttribute("message", new Message("Email already Registered", "alert-danger"));
			return "Registration";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("Register", register);
			session.setAttribute("message", new Message("Please agree terms and conditions", "alert-danger"));
			return "Registration";
		}

	}

	// handler for login
	@RequestMapping("/signin")
	public String login(Register register, Model model, HttpSession session) {
		session.setAttribute("Register", register);
		model.addAttribute("Register", new Register());
		return "Login";
	}

	@RequestMapping(value = "/dologin", method = RequestMethod.POST)
	public String loginsuccess(@ModelAttribute("register") Register register, Model model, HttpSession session) {

		try {
			Register us = regRepo.getRegisterByRegisterName(register.getUsername());
			System.out.println("DOLOGIN USER" + us);

			String url = "";
			if ((us.getUsername().equals(register.getUsername())
					&& (passwordEncoder.matches(register.getPassword(), us.getPassword())))) {
				session.setAttribute("Username", register.getUsername());

				if (us.getType().equals("farmer")) {
					session.setAttribute("register", us);
					url = "redirect:/farmer/fhome";
				} else if (us.getType().equals("buyer")) {
					session.setAttribute("register", us);
					url = "redirect:/bidding/bhome";
				}
			} else {
				session.setAttribute("error", "Invalid Username or Password");
				url = "Login";
			}
			return url;
		} catch (Exception e) {
			return "redirect:signin";
		}
	}

	@RequestMapping(value = { "/logout" }, method = RequestMethod.POST)
	public String logoutDo(ModelMap map, HttpSession session, HttpServletRequest req) {
		session.removeAttribute("register");
		session.removeAttribute("farmer");
		req.getSession().invalidate();
		session.invalidate();
		return "redirect:/Login";
	}
}

