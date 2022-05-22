package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
