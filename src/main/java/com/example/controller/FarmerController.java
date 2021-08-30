package com.example.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.entiry.Farmer;
import com.example.entiry.Register;
import com.example.repository.FarmerRepository;
import com.example.repository.RegisterRepository;
import com.example.service.FarmerService;

@Controller
@RequestMapping("/farmer")
public class FarmerController {
	
	private BCryptPasswordEncoder  bCryptPasswordEncoder ;
	@Autowired
	private RegisterRepository regRepository ;
	
	@Autowired
	private FarmerRepository farmRepository;

	@Autowired
	private FarmerService farmerService;
	
	@RequestMapping("/fhome")
	public String farmerHome(Model model , Principal principal)  {
		
		return "farmer/F_home";
	}
	
	/*
	 * @RequestMapping("/fhome") public String farmerHome(Model model , Principal
	 * principal) { String userName = principal.getName();
	 * System.out.println("USERNAME"+userName); Register register =
	 * this.regRepository.getRegisterByRegisterName(userName);
	 * System.out.println("REGISTER"+register);
	 * model.getAttribute("register"+register); return "farmer/F_home"; }
	 */
	
	// handler for product
		@RequestMapping("/addproduct")
		public String addproduct(Model model)  {
			model.addAttribute("title", "AddProduct-Agri_Bazaar");
			model.addAttribute("farmer", new Farmer());
			return "farmer/addProduct";
		}

		
		@PostMapping("/addProduct")
		public String addProduct(@ModelAttribute("farmer") Farmer farmer, Model model) {
			Farmer result = this.farmRepository.save(farmer);
			model.addAttribute("farmer", result);
			return "farmer/addProduct";
		}
	
		@GetMapping("/ShowProducts")
		public String getProducts(Model model) {
			List<Farmer> list = farmerService.getAllProducts();
			model.addAttribute("products", list);
			return "farmer/ShowProducts";
	}
}
