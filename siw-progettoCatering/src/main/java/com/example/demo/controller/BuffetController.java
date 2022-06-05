package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.Buffet;
import com.example.demo.model.Chef;
import com.example.demo.model.Ingrediente;
import com.example.demo.model.Piatto;
import com.example.demo.service.BuffetService;
import com.example.demo.service.ChefService;
import com.example.demo.service.IngredienteService;
import com.example.demo.service.PiattoService;
import com.example.demo.validator.BuffetValidator;

@Controller
public class BuffetController {
	@Autowired
	private BuffetService buffetService;
	
	@Autowired
	private IngredienteService ingredienteService;
	
	@Autowired
	private PiattoService piattoService;
	
	@Autowired
	private ChefService chefService;
	
	@Autowired
	private BuffetValidator buffetValidator;

	
	@GetMapping("/user/buffets")
	public String getBuffets(Model model) {
		List<Buffet> buffets = this.buffetService.findAll();
		model.addAttribute("buffets", buffets);
		return "/user/buffets.html";
	}
	
	@GetMapping("/user/buffet/{id}")
	public String getBuffet(@PathVariable("id")Long id, Model model) {
		Buffet buffet = this.buffetService.findById(id);
		model.addAttribute("buffet", buffet);
		List<Chef> chefs = chefService.findAll();
		List<Buffet> buffets = buffetService.findAll();
		List<Piatto> piatti = piattoService.findAll();
		List<Ingrediente> ingredienti = ingredienteService.findAll();
		
		model.addAttribute("chefs", chefs);
		model.addAttribute("buffets", buffets);
		model.addAttribute("piatti", piatti);
		model.addAttribute("ingredienti", ingredienti);
		return "/user/buffet.html";
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
		Boolean conditionChef = buffet.getChef() != null;
		model.addAttribute("conditionChef",conditionChef);
		Boolean conditionPiatti = !buffet.getPiatti().isEmpty();
		model.addAttribute("conditionPiatti",conditionPiatti);
		model.addAttribute("buffet", buffet);
		return "/admin/buffet.html";
	}
	
	
	@GetMapping("/admin/cancellaBuffet/{id}")
	public String deleteBuffet(@PathVariable("id")Long id, Model model) {
		this.buffetService.deleteById(id);
		model.addAttribute("buffets",this.buffetService.findAll());
		return "/admin/buffets.html";
	}
	
	@GetMapping("/admin/buffetForm")
	public String addBuffetForm(Model model) {
		model.addAttribute("buffet", new Buffet());
		model.addAttribute("piatti",this.piattoService.findAll());
		model.addAttribute("chefs",this.chefService.findAll());
		return "/admin/buffetForm.html";
	}
	
	@PostMapping("/admin/buffet")
	public String addBuffet(@Valid @ModelAttribute("buffet") Buffet buffet,BindingResult bindingResult, Model model) {

		this.buffetValidator.validate(buffet, bindingResult);
		
		if (!bindingResult.hasErrors()){// se i dati sono corretti
			this.buffetService.save(buffet); // salvo l'oggetto
			
			Boolean conditionChef = buffet.getChef() != null;
			model.addAttribute("conditionChef",conditionChef);
			Boolean conditionPiatti = !buffet.getPiatti().isEmpty();
			model.addAttribute("conditionPiatti",conditionPiatti);
			return "admin/buffet.html";
		} else {
		
			model.addAttribute("piatti",this.piattoService.findAll());
			model.addAttribute("chefs",this.chefService.findAll());
			return "admin/buffetForm.html"; // ci sono errori, torna alla form iniziale
			
		}
	}
	
	
	@GetMapping("/admin/modificaBuffet/{id}")
	public String modificaBuffet(@PathVariable("id")Long id, Model model) {
		model.addAttribute("buffet",this.buffetService.findById(id));
		model.addAttribute("piatti",this.piattoService.findAll());
		model.addAttribute("chefs",this.chefService.findAll());
		return "/admin/buffetFormMod.html";
	}
	
	@PostMapping("/admin/buffetMod/{id}")
	public String modificaBuffetForm(@PathVariable("id")Long vecchioId,@Valid @ModelAttribute("buffet") Buffet buffet,BindingResult bindingResult, Model model) {
		
		if (!bindingResult.hasErrors()){// se i dati sono corretti
			
			Buffet vecchioBuffet = this.buffetService.findById(vecchioId);
			vecchioBuffet.setId(buffet.getId());
			vecchioBuffet.setNome(buffet.getNome());
			vecchioBuffet.setDescrizione(buffet.getDescrizione());
			vecchioBuffet.setChef(buffet.getChef());
			vecchioBuffet.setPiatti(buffet.getPiatti());
			
			this.buffetService.save(vecchioBuffet);
			
			model.addAttribute("buffet", vecchioBuffet);
			return "/admin/buffet.html";
		} else {

			model.addAttribute("buffet", this.buffetService.findById(vecchioId));
			model.addAttribute("piatti",this.piattoService.findAll());
			model.addAttribute("chefs",this.chefService.findAll());
			return "admin/buffetFormMod.html"; // ci sono errori, torna alla form iniziale
			
		}
	}
}
