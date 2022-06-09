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
import com.example.demo.validator.IngredienteValidator;

@Controller
public class IngredienteController {
	@Autowired
	private IngredienteService ingredienteService;
	
	@Autowired
	private ChefService chefService;
	
	@Autowired
	private BuffetService buffetService;
	
	@Autowired
	private PiattoService piattoService;
	
	@Autowired
	private IngredienteValidator ingredienteValidator;
	
	@GetMapping("/user/ingredienti")
	public String getIngredienti(Model model) {
		List<Ingrediente> ingredienti = ingredienteService.findAll();
		model.addAttribute("ingredienti", ingredienti);
		return "/user/ingredienti.html";
	}
	
	@GetMapping("/user/ingrediente/{id}")
	public String getChef(@PathVariable("id")Long id, Model model) {
		Ingrediente ingrediente = ingredienteService.findById(id);
		model.addAttribute("ingrediente", ingrediente);
		List<Chef> chefs = chefService.findAll();
		List<Buffet> buffets = buffetService.findAll();
		List<Piatto> piatti = piattoService.findAll();
		List<Ingrediente> ingredienti = ingredienteService.findAll();
		
		model.addAttribute("chefs", chefs);
		model.addAttribute("buffets", buffets);
		model.addAttribute("piatti", piatti);
		model.addAttribute("ingredienti", ingredienti);
		return "/user/ingrediente.html";
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
	
//	@PostMapping("/admin/ingrediente")
//	public String addIngrediente(@Valid @ModelAttribute("ingrediente") Ingrediente ingrediente,BindingResult bindingResult, Model model) {
//
//		this.ingredienteValidator.validate(ingrediente, bindingResult);
//		
//		if (!bindingResult.hasErrors()){// se i dati sono corretti
//			this.ingredienteService.save(ingrediente); // salvo l'oggetto
//			
//			//model.addAttribute("ingrediente", ingredienteService.findById(ingrediente.getId()));
//			return "redirect:/admin/ingredienti";
//		} else
//			return "/admin/ingredienteForm.html"; // ci sono errori, torna alla form iniziale
//	}
	
	@PostMapping("/admin/ingrediente")
    public String addChef(@ModelAttribute("ingrediente") Ingrediente ingrediente, @RequestParam("image") MultipartFile multipartFile,
    									Model model, BindingResult bindingResult) throws IOException {
    	this.ingredienteValidator.validate(ingrediente, bindingResult);
        if (!bindingResult.hasErrors()) {
        	
        	
        	/*UPLOAD FOTO*/
        	String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            ingrediente.setImg("/images/" + fileName);
            this.ingredienteService.save(ingrediente);
            String uploadDir = "src/main/resources/static/images/";
            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
            
            return "redirect:/admin/ingredienti";
        }
        return "admin/ingredienteForm.html";
    }
	
	@GetMapping("/admin/modificaIngrediente/{id}")
	public String modificaIngrediente(@PathVariable("id")Long id, Model model) {
		model.addAttribute("ingrediente",this.ingredienteService.findById(id));
		return "/admin/ingredienteFormMod.html";
	}
	
	@PostMapping("/admin/ingredienteMod/{id}")
	public String modificaIngredienteForm(@PathVariable("id")Long vecchioId,@RequestParam("image") MultipartFile multipartFile,
			@Valid @ModelAttribute("ingrediente") Ingrediente ingrediente,BindingResult bindingResult, Model model) throws IOException {
		
		if (!bindingResult.hasErrors()){// se i dati sono corretti
			
			Ingrediente vecchioIngrediente = this.ingredienteService.findById(vecchioId);
			vecchioIngrediente.setId(ingrediente.getId());
			vecchioIngrediente.setNome(ingrediente.getNome());
			vecchioIngrediente.setDescrizione(ingrediente.getDescrizione());
			vecchioIngrediente.setOrigine(ingrediente.getOrigine());
			vecchioIngrediente.setImg(ingrediente.getImg());
			
        	/*UPLOAD FOTO*/
        	String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            vecchioIngrediente.setImg("/images/" + fileName);
            this.ingredienteService.save(vecchioIngrediente);
            String uploadDir = "src/main/resources/static/images/";
            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
			
			//model.addAttribute("ingrediente", vecchioIngrediente);
			return "redirect:/admin/ingredienti";
		} else {

			model.addAttribute("ingrediente",this.ingredienteService.findById(vecchioId));
			return "admin/ingredienteFormMod.html"; // ci sono errori, torna alla form iniziale
			
		}
	}
}
