package com.example.demo;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.model.Buffet;
import com.example.demo.model.Chef;
import com.example.demo.model.Ingrediente;
import com.example.demo.model.Piatto;
import com.example.demo.repository.BuffetRepository;
import com.example.demo.repository.ChefRepository;
import com.example.demo.repository.IngredienteRepository;
import com.example.demo.repository.PiattoRepository;

@SpringBootApplication
public class SiwProgettoCateringApplication /*implements CommandLineRunner*/{
	
	@Autowired
	private ChefRepository chefRepository;
	
	@Autowired 
	private PiattoRepository piattoRepository;
	
	@Autowired
	private BuffetRepository buffetRepository;
	
	@Autowired
	private IngredienteRepository ingredienteRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(SiwProgettoCateringApplication.class, args);
	}

/*	@Override
	public void run(String... args) throws Exception {
	
		Buffet carne = new Buffet();
		carne.setNome("Buffet Carne");
		carne.setDescrizione("Buffet costituito da piatti di montagna con carne deliziosa e di alta qualità.");
		
		//roma.setImg("roma.jpg");
		Ingrediente pepeNero = new Ingrediente();
		pepeNero.setNome("Pepe nero");
		Ingrediente olio = new Ingrediente();
		pepeNero.setNome("Olio extra vergine di oliva");
		List<Ingrediente> ingredienti = new LinkedList<Ingrediente>();
		ingredienti.add(pepeNero);
		ingredienti.add(olio);
		
		Piatto p = new Piatto();
		p.setNome("Bistecca alla fiorentina");
		p.setIngredienti(ingredienti);
		List<Piatto> piatti = new LinkedList<Piatto>();
		piatti.add(p);
		carne.setPiatti(piatti);
		
		Chef chef = new Chef();
		chef.setNome("Simone");
		chef.setCognome("Di Ienno");
		chef.setNazionalita("Italia");
		carne.setChef(chef);
		
		List<Buffet> buffet = new LinkedList<Buffet>();
		buffet.add(carne);
		
		Buffet roma = new Buffet();
		roma.setNome("Cucina Romana");
		//napoli.setImg("napoli.jpg");
		roma.setChef(chef);
		buffet.add(roma);
		chef.setBuffet(buffet);
		Ingrediente guanciale = new Ingrediente();
		pepeNero.setNome("Guanciale");
		Ingrediente uovo = new Ingrediente();
		pepeNero.setNome("Uovo");
		List<Ingrediente> ingredienti1 = new LinkedList<Ingrediente>();
		ingredienti1.add(guanciale);
		ingredienti1.add(uovo);
		Piatto p1 = new Piatto();
		p1.setNome("Carbonara");
		p1.setIngredienti(ingredienti1);
		List<Piatto> piatti1 = new LinkedList<Piatto>();
		piatti.add(p1);
		roma.setPiatti(piatti1);
		
		
		ingredienteRepository.save(pepeNero);
		ingredienteRepository.save(olio);
		ingredienteRepository.save(guanciale);
		ingredienteRepository.save(uovo);
		chefRepository.save(chef);
		piattoRepository.save(p);
		piattoRepository.save(p1);
		buffetRepository.save(carne);
		
		buffetRepository.save(roma);
		
		
		Buffet sushi = new Buffet();
		sushi.setNome("Buffet sushi");
		sushi.setDescrizione("cucina a base di sushi");
		
		//sushi.setImg("roma.jpg");
		Piatto p1 = new Piatto();
		p1.setNome("roll");
		List<Piatto> piatti1 = new LinkedList<Piatto>();
		piatti1.add(p1);
		sushi.setPiatti(piatti1);
		Chef chef1 = new Chef();
		chef1.setNome("chef1");
		chef1.setCognome("cognome chef1");
		chef1.setNazionalita("Italia");
		sushi.setChef(chef1);
		List<Buffet> buffet1 = new LinkedList<Buffet>();
		buffet1.add(sushi);
		Buffet veg = new Buffet();
		veg.setNome("Buffet vegetariano");
		veg.setDescrizione("Buffet vegetariano wow");
		//veg.setImg("napoli.jpg");
		veg.setChef(chef1);
		buffet1.add(veg);
		chef1.setBuffet(buffet1);
		chefRepository.save(chef1);
		piattoRepository.save(p1);
		buffetRepository.save(sushi);
		
		buffetRepository.save(veg);
	}*/
}
