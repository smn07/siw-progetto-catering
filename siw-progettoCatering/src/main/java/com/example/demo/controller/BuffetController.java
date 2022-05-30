package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.model.Buffet;
import com.example.demo.model.Chef;
import com.example.demo.service.BuffetService;
import com.example.demo.validator.BuffetValidator;

@Controller
public class BuffetController {
	@Autowired
	private BuffetService buffetService;
	
	@Autowired
	private BuffetValidator buffetValidator;
	
	@GetMapping("/buffets")
	public String getBuffets(Model model) {
		List<Buffet> buffets = this.buffetService.findAll();
		model.addAttribute("buffets", buffets);
		return "buffets.html";
	}
	
	@GetMapping("/buffet/{id}")
	public String getBuffet(@PathVariable("id")Long id, Model model) {
		Buffet buffet = this.buffetService.findById(id);
		model.addAttribute("buffet", buffet);
		return "buffet.html";
	}
	
	@GetMapping("/admin/buffets")
	public String getBuffetsAdmin(Model model) {
		List<Buffet> buffets = this.buffetService.findAll();
		model.addAttribute("buffets", buffets);
		return "/admin/buffets.html";
	}
	
	@GetMapping("/admin/buffet/{id}")
	public String getBuffetAdmin(@PathVariable("id")Long id, Model model) {
		Buffet buffet = this.buffetService.findById(id);
		model.addAttribute("buffet", buffet);
		return "/admin/buffet.html";
	}
	
	
	@GetMapping("/admin/cancellaBuffet/{id}")
	public String deleteBuffet(@PathVariable("id")Long id, Model model) {
		this.buffetService.deleteById(id);
		model.addAttribute("buffets",this.buffetService.findAll());
		return "/admin/buffets.html";
	}
}
