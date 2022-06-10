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
import com.example.demo.model.Piatto;
import com.example.demo.service.BuffetService;
import com.example.demo.service.IngredienteService;
import com.example.demo.service.PiattoService;
import com.example.demo.util.FileUploadUtil;
import com.example.demo.validator.PiattoValidator;

@Controller
public class PiattoController {
	@Autowired
	private PiattoService piattoService;
	
	@Autowired
	private BuffetService buffetService;
	
	@Autowired
	private IngredienteService ingredienteService;
	
	@Autowired
	private PiattoValidator piattoValidator;
	
	@GetMapping("/user/piatti")
	public String getPiatti(Model model) {
		List<Piatto> piatti = piattoService.findAll();
		model.addAttribute("piatti", piatti);
		return "/user/piatti.html";
	}
	
	@GetMapping("/user/piatto/{id}")
	public String getChef(@PathVariable("id")Long id, Model model) {
		Piatto piatto = piattoService.findById(id);
		model.addAttribute("piatto", piatto);
		return "/user/piatto.html";
	}
	
	@GetMapping("/admin/piatti")
	public String getPiattiAdmin(Model model) {
		List<Piatto> piatti = piattoService.findAll();
		model.addAttribute("piatti", piatti);
		return "/admin/piatti.html";
	}
	
	@GetMapping("/admin/piatto/{id}")
	public String getPiattoAdmin(@PathVariable("id")Long id, Model model) {
		Piatto piatto = this.piattoService.findById(id);
		model.addAttribute("piatto", piatto);
		return "/admin/piatto.html";
	}
	
	
	@GetMapping("/admin/cancellaPiatto/{id}")
	public String deletePiatto(@PathVariable("id")Long id, Model model) {
		Piatto piatto = this.piattoService.findById(id);
		List<Buffet> buffets = this.buffetService.findAll();
		for(Buffet b : buffets) {
			b.getPiatti().remove(piatto);
			this.buffetService.save(b);
		}
		
		this.piattoService.deleteById(id);
		model.addAttribute("piatti",this.piattoService.findAll());
		return "/admin/piatti.html";
	}
	
	@GetMapping("/admin/piattoForm")
	public String addPiattoForm(Model model) {
		model.addAttribute("piatto", new Piatto());
		model.addAttribute("ingredienti",this.ingredienteService.findAll());
		return "/admin/piattoForm.html";
	}
	
//	@PostMapping("/admin/piatto")
//	public String addPiatto(@Valid @ModelAttribute("piatto") Piatto piatto,BindingResult bindingResult, Model model) {
//
//		this.piattoValidator.validate(piatto, bindingResult);
//		
//		if (!bindingResult.hasErrors()){// se i dati sono corretti
//			this.piattoService.save(piatto); // salvo l'oggetto
//			
//			//model.addAttribute("piatto", piattoService.findById(piatto.getId()));
//			return "redirect:/admin/piatti";
//		} else {
//		
//			model.addAttribute("ingredienti",this.ingredienteService.findAll());
//			return "/admin/piattoForm.html"; // ci sono errori, torna alla form iniziale
//			
//		}
//	}
	
	@PostMapping("/admin/piatto")
    public String addChef(@ModelAttribute("piatto") Piatto piatto, @RequestParam("image") MultipartFile multipartFile,
    									Model model, BindingResult bindingResult) throws IOException {
    	this.piattoValidator.validate(piatto, bindingResult);
        if (!bindingResult.hasErrors()) {
        	
        	
        	/*UPLOAD FOTO*/
        	String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            piatto.setImg("/images/" + fileName);
            this.piattoService.save(piatto);
            String uploadDir = "src/main/resources/static/images/";
            if(fileName != null && multipartFile != null && !fileName.isEmpty())
            	FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
            
            return "redirect:/admin/piatti";
        }
        return "admin/piattoForm.html";
    }
	
	@GetMapping("/admin/modificaPiatto/{id}")
	public String modificaPiatto(@PathVariable("id")Long id, Model model) {
		model.addAttribute("piatto",this.piattoService.findById(id));
		model.addAttribute("ingredienti",this.ingredienteService.findAll());
		return "/admin/piattoFormMod.html";
	}
	
	@PostMapping("/admin/piattoMod/{id}")
	public String modificaPiattoForm(@PathVariable("id")Long vecchioId,@RequestParam("image") MultipartFile multipartFile,
			@Valid @ModelAttribute("chef") Piatto piatto,BindingResult bindingResult, Model model) throws IOException {
		
		if (!bindingResult.hasErrors()){// se i dati sono corretti
			
			Piatto vecchioPiatto = this.piattoService.findById(vecchioId);
			vecchioPiatto.setId(piatto.getId());
			vecchioPiatto.setNome(piatto.getNome());
			vecchioPiatto.setDescrizione(piatto.getDescrizione());
			vecchioPiatto.setIngredienti(piatto.getIngredienti());
			//vecchioPiatto.setImg(piatto.getImg());
			
        	/*UPLOAD FOTO*/
        	String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            String uploadDir = "src/main/resources/static/images/";
            if(fileName != null && multipartFile != null && !fileName.isEmpty()) {
            	FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
                vecchioPiatto.setImg("/images/" + fileName);
            }
            this.piattoService.save(vecchioPiatto);
            
			return "redirect:/admin/piatti";
		} else {

			model.addAttribute("piatto",this.piattoService.findById(vecchioId));
			model.addAttribute("ingredienti",this.ingredienteService.findAll());
			return "admin/piattoFormMod.html"; // ci sono errori, torna alla form iniziale
			
		}
	}
}
