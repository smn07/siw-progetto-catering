package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.model.Chef;
import com.example.demo.service.ChefService;
import com.example.demo.validator.ChefValidator;

@Controller
public class ChefController {
	@Autowired
	private ChefService chefService;
	
	
	@Autowired
	private ChefValidator chefValidator;
	
	@GetMapping("/chefs")
	public String getChefs(Model model) {
		List<Chef> chefs = chefService.findAll();
		model.addAttribute("chefs", chefs);
		return "chefs.html";
	}
	
	@GetMapping("/chef/{id}")
	public String getChef(@PathVariable("id")Long id, Model model) {
		Chef chef = chefService.findById(id);
		
		model.addAttribute("chef", chef);
		return "chef.html";
	}
	
	@GetMapping("/admin/chefs")
	public String getChefsAdmin(Model model) {
		List<Chef> chefs = chefService.findAll();
		model.addAttribute("chefs", chefs);
		return "/admin/chefs.html";
	}
	
	@GetMapping("/admin/chef/{id}")
	public String getChefAdmin(@PathVariable("id")Long id, Model model) {
		Chef chef = chefService.findById(id);
		
		model.addAttribute("chef", chef);
		return "/admin/chef.html";
	}
	

	@GetMapping("/admin/cancellaChef/{id}")
	public String deleteChef(@PathVariable("id")Long id, Model model) {
		this.chefService.deleteById(id);
		model.addAttribute("chefs",this.chefService.findAll());
		return "/admin/chefs.html";
	}
	
	
}
