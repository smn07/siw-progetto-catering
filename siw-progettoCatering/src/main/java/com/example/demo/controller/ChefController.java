package com.example.demo.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Buffet;
import com.example.demo.model.Chef;
import com.example.demo.model.Ingrediente;
import com.example.demo.model.Piatto;
import com.example.demo.service.BuffetService;
import com.example.demo.service.ChefService;
import com.example.demo.service.IngredienteService;
import com.example.demo.service.PiattoService;
import com.example.demo.util.FileUploadUtil;
import com.example.demo.validator.ChefValidator;

@Controller
public class ChefController {
	@Autowired
	private ChefService chefService;
	
	@Autowired
	private PiattoService piattoService;
	
	@Autowired
	private IngredienteService ingredienteService;
	
	@Autowired
	private BuffetService buffetService;
	
	
	@Autowired
	private ChefValidator chefValidator;
	
	@GetMapping("/user/chefs")
	public String getChefs(Model model) {
		List<Chef> chefs = chefService.findAll();
		model.addAttribute("chefs", chefs);
		return "/user/chefs.html";
	}
	
	@GetMapping("/user/chef/{id}")
	public String getChef(@PathVariable("id")Long id, Model model) {
		Chef chef = chefService.findById(id);
		
		model.addAttribute("chef", chef);
		
		List<Chef> chefs = chefService.findAll();
		List<Buffet> buffets = buffetService.findAll();
		List<Piatto> piatti = piattoService.findAll();
		List<Ingrediente> ingredienti = ingredienteService.findAll();
		
		model.addAttribute("chefs", chefs);
		model.addAttribute("buffets", buffets);
		model.addAttribute("piatti", piatti);
		model.addAttribute("ingredienti", ingredienti);
		return "/user/chef.html";
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
	
	@GetMapping("/admin/chefForm")
	public String addChefForm(Model model) {
		model.addAttribute("chef", new Chef());
		model.addAttribute("buffets",this.buffetService.findAll());
		return "/admin/chefForm.html";
	}
	
//	@PostMapping("/admin/chef")
//	public String addChef(@Valid @ModelAttribute("chef") Chef chef,BindingResult bindingResult, Model model) {
//
//		this.chefValidator.validate(chef, bindingResult);
//		
//		if (!bindingResult.hasErrors()){// se i dati sono corretti
//			
//			
//			
//			this.chefService.save(chef); // salvo l'oggetto
//			
//			//model.addAttribute("chef", chefService.findById(chef.getId()));
//			return "redirect:/admin/chefs";
//		} else {
//
//			model.addAttribute("buffets",this.buffetService.findAll());
//			return "/admin/chefForm.html"; // ci sono errori, torna alla form iniziale
//			
//		}
//	}
	
	@PostMapping("/admin/chef")
    public String addChef(@ModelAttribute("chef") Chef chef, @RequestParam("image") MultipartFile multipartFile,
    									Model model, BindingResult bindingResult) throws IOException {
    	this.chefValidator.validate(chef, bindingResult);
        if (!bindingResult.hasErrors()) {
        	
        	
        	/*UPLOAD FOTO*/
        	if(multipartFile.getOriginalFilename() != null) {
        	String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            chef.setImg("/images/" + fileName);
            this.chefService.save(chef);
            String uploadDir = "src/main/resources/static/images/";
            if(fileName != null && multipartFile != null && !fileName.isEmpty())
            	FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        	}
            
            return "redirect:/admin/chefs";
        }
        return "admin/chefForm.html";
    }
	
	
	@GetMapping("/admin/modificaChef/{id}")
	public String modificaChef(@PathVariable("id")Long id, Model model) {
		model.addAttribute("chef",this.chefService.findById(id));
		return "/admin/chefFormMod.html";
	}
	
	@PostMapping("/admin/chefMod/{id}")
	public String modificaChefForm(@PathVariable("id")Long vecchioId,@RequestParam("image") MultipartFile multipartFile,
									@Valid @ModelAttribute("chef") Chef chef,BindingResult bindingResult, Model model) throws IOException {
		
		if (!bindingResult.hasErrors()){// se i dati sono corretti
			//this.chefService.save(chef); // salvo l'oggetto
			
			Chef vecchioChef = this.chefService.findById(vecchioId);
			vecchioChef.setId(chef.getId());
			vecchioChef.setNome(chef.getNome());
			vecchioChef.setCognome(chef.getCognome());
			vecchioChef.setNazionalita(chef.getNazionalita());
			
        	/*UPLOAD FOTO*/
        	String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            vecchioChef.setImg("/images/" + fileName);
            this.chefService.save(vecchioChef);
            String uploadDir = "src/main/resources/static/images/";
            if(fileName != null && multipartFile != null && !fileName.isEmpty())
            	FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
			
			
			//model.addAttribute("chef", vecchioChef);
			return "redirect:/admin/chefs";
		} else {

			model.addAttribute("chef",this.chefService.findById(vecchioId));
			return "admin/chefFormMod.html"; // ci sono errori, torna alla form iniziale
			
		}
	}
	
}
