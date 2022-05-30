package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Ingrediente;
import com.example.demo.repository.IngredienteRepository;
@Service
public class IngredienteService {

	@Autowired
	private IngredienteRepository ingredienteRepository;
	
	public List<Ingrediente> findAll(){
		List<Ingrediente> ingredienti = new ArrayList<Ingrediente>();
		for (Ingrediente i : ingredienteRepository.findAll()) {
			ingredienti.add(i);
		}
		return ingredienti;
	}
	
	public Ingrediente findById(Long id) {
		return ingredienteRepository.findById(id).get();
	}
	
	@Transactional
	public void deleteById(Long id) {
		this.ingredienteRepository.deleteById(id);
	}

	@Transactional
	public void save(@Valid Ingrediente ingrediente) {
		this.ingredienteRepository.save(ingrediente);
	}

	public boolean alreadyExists(Ingrediente i) {
		return this.ingredienteRepository.existsByNome(i.getNome());
	}
}
