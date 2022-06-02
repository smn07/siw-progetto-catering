package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Piatto;
import com.example.demo.repository.PiattoRepository;
@Service
public class PiattoService {

	@Autowired
	private PiattoRepository piattoRepository;
	
	public List<Piatto> findAll(){
		List<Piatto> piatti = new ArrayList<Piatto>();
		for (Piatto p : this.piattoRepository.findAll()) {
			piatti.add(p);
		}
		return piatti;
	}
	
	public Piatto findById(Long id) {
		return this.piattoRepository.findById(id).get();
	}

	@Transactional
	public void deleteById(Long id) {
		this.piattoRepository.deleteById(id);
	}

	@Transactional
	public void save(@Valid Piatto piatto) {
		this.piattoRepository.save(piatto);
	}

	public boolean alreadyExists(Piatto p) {
		return this.piattoRepository.existsByNome(p.getNome());
	}
}
