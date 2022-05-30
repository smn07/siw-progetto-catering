package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.Ingrediente;
import com.example.demo.service.IngredienteService;
import com.example.demo.validator.IngredienteValidator;

@Controller
public class IngredienteController {
	@Autowired
	private IngredienteService ingredienteService;
	
	@Autowired
	private IngredienteValidator ingredienteValidator;
	
	@GetMapping("/ingredienti")
	public String getIngredienti(Model model) {
		List<Ingrediente> ingredienti = ingredienteService.findAll();
		model.addAttribute("ingredienti", ingredienti);
		return "ingredienti.html";
	}
	
	@GetMapping("/ingrediente/{id}")
	public String getChef(@PathVariable("id")Long id, Model model) {
		Ingrediente ingrediente = ingredienteService.findById(id);
		model.addAttribute("ingrediente", ingrediente);
		return "ingrediente.html";
	}
	
	@GetMapping("/admin/ingredienti")
	public String getIngredientiAdmin(Model model) {
		List<Ingrediente> ingredienti = ingredienteService.findAll();
		model.addAttribute("ingredienti", ingredienti);
		return "/admin/ingredienti.html";
	}
	
	@GetMapping("/admin/ingrediente/{id}")
	public String getIngredienteAdmin(@PathVariable("id")Long id, Model model) {
		Ingrediente ingrediente = this.ingredienteService.findById(id);
		
		model.addAttribute("ingrediente", ingrediente);
		return "/admin/ingrediente.html";
	}
	
	@GetMapping("/admin/cancellaIngrediente/{id}")
	public String deleteIngrediente(@PathVariable("id")Long id, Model model) {
		this.ingredienteService.deleteById(id);
		model.addAttribute("ingredienti",this.ingredienteService.findAll());
		return "/admin/ingredienti.html";
	}
	
	@GetMapping("/admin/ingredienteForm")
	public String addIngredienteForm(Model model) {
		model.addAttribute("ingrediente", new Ingrediente());
		return "/admin/ingredienteForm.html";
	}
	
	@PostMapping("/admin/ingrediente")
	public String addIngrediente(@Valid @ModelAttribute("ingrediente") Ingrediente ingrediente,BindingResult bindingResult, Model model) {

		this.ingredienteValidator.validate(ingrediente, bindingResult);
		
		if (!bindingResult.hasErrors()){// se i dati sono corretti
			this.ingredienteService.save(ingrediente); // salvo l'oggetto
			
			model.addAttribute("ingrediente", ingredienteService.findById(ingrediente.getId()));
			return "/admin/ingrediente.html";
		} else
			return "/admin/ingredienteForm.html"; // ci sono errori, torna alla form iniziale
	}
}
