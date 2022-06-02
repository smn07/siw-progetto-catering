package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Buffet;
import com.example.demo.model.Chef;
import com.example.demo.repository.ChefRepository;
@Service
public class ChefService {

	@Autowired
	private ChefRepository chefRepository;

	public List<Chef> findAll(){
		List<Chef> chefs = new ArrayList<Chef>();
		for (Chef c : chefRepository.findAll()) {
			chefs.add(c);
		}
		return chefs;
	}
	
	public Chef findById(Long id) {
		return chefRepository.findById(id).get();
	}

	public List<Buffet> findAllBuffet(Long id) {
		return this.chefRepository.findAllBuffet(id);
	}
	
	@Transactional
	public void deleteById(Long id) {
		this.chefRepository.deleteById(id);
	}
	
	@Transactional
	public void save(@Valid Chef chef) {
		this.chefRepository.save(chef);
	}

	public boolean alreadyExists(Chef c) {
		return this.chefRepository.existsByNomeAndCognome(c.getNome(),c.getCognome());
	}

}
