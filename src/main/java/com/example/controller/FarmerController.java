package com.example.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.entity.Farmer;
import com.example.entity.Register;
import com.example.repository.FarmerRepository;
import com.example.repository.RegisterRepository;
import com.example.service.FarmerService;

@Controller
@RequestMapping("/farmer")
public class FarmerController {

	@Autowired
	private FarmerRepository farmRepository;

	@Autowired
	private FarmerService farmerService;


	@RequestMapping("/fhome")
	public String farmerHome(Model model,HttpSession session) {

		try {
			int regId = ((Register) session.getAttribute("register")).getId();
			return "farmer/f_home";
		} catch (Exception e) {
			return "redirect:error";
		}
	}

	// handler for product
	@RequestMapping("/addproduct")
	public String addproduct(Model model ) {
	
			
				model.addAttribute("title", "AddProduct-Agri_Bazaar");
				model.addAttribute("farmer", new Farmer());
				return "farmer/addP";
		
	}

	@PostMapping("/addProduct")
	public String addProduct(@ModelAttribute("farmer") Farmer farmer, Model model, HttpSession session,
			Register register) {
		
					try {
						model.addAttribute("registerId", register.getId());
						int regId = ((Register) session.getAttribute("register")).getId();
						farmer.setReg_id(regId);

						Farmer result = this.farmRepository.save(farmer);
						model.addAttribute("farmer", result);

						return "farmer/addP";
					} catch (Exception e) {
						return "redirect:error";
					}
	}

	@GetMapping("/Showproducts")
	public String getProducts(Model model, HttpSession session) {

		try {
			int regId = ((Register) session.getAttribute("register")).getId();
			List<Farmer> list = farmerService.getAllProducts(regId);
			model.addAttribute("products", list);
			return "farmer/ShowProducts";
		} catch (Exception e) {
			return "redirect:error";
		}
	}

	@RequestMapping("/viewproduct/{id}")
	public String viewProduct(Model model, @PathVariable(value = "id") int id, HttpSession session, Farmer farmer) {
		try {
			model.addAttribute("title", "Product-Agri_Bazaar");
			List<Farmer> list = farmerService.viewProductById(id);
			session.setAttribute("products", list);
			model.addAttribute("products", list);
			return "farmer/viewProduct";
		} catch (Exception e) {
			return "redirect:error";
		}
	}

	@GetMapping("/deleteProduct/{id}")
	public String deleteProduct(Model model, @PathVariable(value = "id") int id) {
		try {
			System.out.println(id);
			farmerService.deleteProductById(id);
			System.out.println(id);
			return "redirect:/farmer/Showproducts";
		} catch (Exception e) {
			return "redirect:error";
		}
	}

	
}