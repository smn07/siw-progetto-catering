package com.example.demo.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.demo.model.Ingrediente;
import com.example.demo.service.IngredienteService;

@Component
public class IngredienteValidator implements Validator{

	@Autowired
	private IngredienteService ingredienteService;

	@Override
	public boolean supports(Class<?> clazz) {
		return Ingrediente.class.equals(clazz);
	}

	@Override
	public void validate(Object o, Errors errors) {
		
		Ingrediente ingrediente = (Ingrediente)o;
		
		if(this.ingredienteService.alreadyExists((Ingrediente)o)) {
			errors.reject("ingrediente.duplicato");
		}
		
		if(ingrediente.getNome().isEmpty()) {
			errors.reject("NotBlank.ingrediente.nome");
		}
		
		if(ingrediente.getDescrizione().isEmpty()) {
			errors.reject("NotBlank.ingrediente.descrizione");
		}
		
		if(ingrediente.getOrigine().isEmpty()) {
			errors.reject("NotBlank.ingrediente.origine");
		}
	}

}
