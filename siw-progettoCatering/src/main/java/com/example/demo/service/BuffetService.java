package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Buffet;
import com.example.demo.model.Piatto;
import com.example.demo.repository.BuffetRepository;
@Service
public class BuffetService {

	@Autowired
	private BuffetRepository buffetRepository;

	public List<Buffet> findAll(){
		List<Buffet> buffets = new ArrayList<Buffet>();
		for (Buffet c : this.buffetRepository.findAll()) {
			buffets.add(c);
		}
		return buffets;
	}
	
	public Buffet findById(Long id) {
		return this.buffetRepository.findById(id).get();
	}

	@Transactional
	public void deleteById(Long id) {
		this.buffetRepository.deleteById(id);
	}

	@Transactional
	public void save(@Valid Buffet buffet) {
		this.buffetRepository.save(buffet);
	}

	public boolean alreadyExists(Buffet b) {
		return this.buffetRepository.existsByNome(b.getNome());
	}

}
