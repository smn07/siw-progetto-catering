package com.example.demo.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.demo.model.Buffet;
import com.example.demo.model.Piatto;
import com.example.demo.service.BuffetService;

@Component
public class BuffetValidator implements Validator{

	@Autowired
	private BuffetService buffetService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Buffet.class.equals(clazz);
	}

	@Override
	public void validate(Object o, Errors errors) {
		
		Buffet buffet = (Buffet)o;
		
		if(this.buffetService.alreadyExists((Buffet)o)) {
			errors.reject("buffet.duplicato");
		}
		
		if(buffet.getNome().isEmpty()) {
			errors.reject("NotBlank.buffet.nome");
		}
		
		if(buffet.getDescrizione().isEmpty()) {
			errors.reject("NotBlank.buffet.descrizione");
		}
	}


}
