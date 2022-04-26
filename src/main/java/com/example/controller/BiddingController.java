package com.example.controller;

import java.util.List;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;


import com.example.entity.Bidding;
import com.example.entity.Farmer;
import com.example.entity.Register;
import com.example.repository.FarmerRepository;
import com.example.repository.RegisterRepository;
import com.example.service.BiddingServiceImple;
import com.example.service.EmailService;
import com.example.service.FarmerService;

@Controller
@RequestMapping("/bidding")
public class BiddingController {

	@Autowired
	private BiddingServiceImple bidService;

	@Autowired
	private FarmerService farmerService;

	@Autowired
	private EmailService emailService;

 
	@RequestMapping("/viewproduct/{id}")
	public String viewProduct(Model model, @PathVariable(value = "id") int id, HttpSession session, Farmer farmer) {
		try {
			model.addAttribute("title", "Product-Agri_Bazaar");
			int regId = ((Register) session.getAttribute("register")).getId();
			List<Farmer> list = farmerService.viewProductById(id);
			session.setAttribute("products", list);
			model.addAttribute("products", list);
			return "buyer/BidView";
		} catch (Exception e) {
			return "redirect:error";
		}
	}

	@GetMapping("/bidUpdate/{id}")
	public String bidUpdate(@PathVariable(value = "id") int productId, Model model, Bidding bidding,
			HttpSession session) {
		try {
			int regId = ((Register) session.getAttribute("register")).getId();
			Farmer farmer = this.farmerService.getProductById(productId);
			model.addAttribute("farmer", farmer);
			return "buyer/updateBid";
		} catch (Exception e) {
			return "redirect:error";
		}
	}

	@PostMapping("/updatebid")
	public String addBid(@ModelAttribute Farmer farmer, Model model, HttpSession session, Register register,
			Bidding bidding) {
		try {
			int regId = ((Register) session.getAttribute("register")).getId();

			String email = ((Register) session.getAttribute("register")).getUsername();

			double minp = farmer.getMinPrice();
			double bid = farmer.getHighestBid();

			if (bid > minp) {
				bidding.setBuyerId(email);
				bidding.setBidPrice(farmer.getHighestBid());
				bidding.setProductId(farmer.getProductId());
				this.bidService.saveUpdate(bidding);

				this.farmerService.saveUpdate(farmer);
				model.addAttribute("farmer", farmer);
				model.addAttribute("bidding", bidding);

				return "redirect:/bidding/Showproducts";
			} else {
				return "redirect:/bidding/Showproducts";
			}
		} catch (Exception e) {
			return "redirect:error";
		}
	}

	@RequestMapping("/endbid/{id}")
	public String endBid(@ModelAttribute Farmer farmer, @PathVariable(value = "id") int id, Model model,
			HttpSession session, Register register, Bidding bidding) {

		String fname = ((Register) session.getAttribute("register")).getFirstName();
		String lname = ((Register) session.getAttribute("register")).getLastname();
		String mno = ((Register) session.getAttribute("register")).getMobileno();
		String femail = ((Register) session.getAttribute("register")).getUsername();

		double maxBid = bidService.findMaxBid(id);

		String email = bidService.findWinner(maxBid, id);

		{
			String subject = "Congratulation You Won The Bid";
			String message = "" + "<div style='border:1px solid #e2e2e2; padding:20px'>"
					+ "<h1 style='color:red;'>AGRI BAZAAR</h1>" + "<h1>" + " Buyer Id =" + (email) + "<br>"
					+ " Product Id =" + (id) + "<br>" + " Your Bid =" + (maxBid) + "<br>" + "</n>"
					+ " Farmer Contact Details:-" + "<br>" + "Farmer Name:-" + (fname) + " " + (lname) + "<br>"
					+ "Farmer Mobile No:" + (mno) + "<br>"
					+ "<span>Please Note that Transportation cost of Product is to be paid by Buyer</span>" + "<br>"
					+ "Thank You for using AgriBazaar Website !" + "<br>" + "</h1>" + "</div>";
			String to = email;
			boolean flag = this.emailService.sendEmail(subject, message, to);
			// email notification code end
		}

		{
			String subject = "Congrats Your Product is Sold";
			String message = "" + "<div style='border:1px solid #e2e2e2; padding:20px'>"
					+ "<h1 style='color:red;'>AGRI BAZAAR</h1>" + "<h1>" + " Buyer Contact =" + (email) + "<br>"
					+ " Product Id =" + (id) + "<br>" + " Sold At =" + (maxBid) + "<br>" + "</n>"
					+ "Thank You for using AgriBazaar Website !" + "<br>" + "</h1>" + "</div>";
			String to = femail;
			boolean flag = this.emailService.sendEmail(subject, message, to);
		}
		farmerService.deleteProductById(id);
		model.addAttribute("farmer", farmer);

		return "redirect:/farmer/fhome";
	}

}
