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

	/*@Override
	public void run(String... args) throws Exception {
	
		Buffet roma = new Buffet();
		roma.setNome("Buffet cucina romana");
		roma.setDescrizione("cucina tipica romana!");
		
		//roma.setImg("roma.jpg");
		Ingrediente uovo = new Ingrediente();
		uovo.setNome("uovo");
		List<Ingrediente> ingredienti = new LinkedList<Ingrediente>();
		ingredienti.add(uovo);
		
		Piatto p = new Piatto();
		p.setNome("carbonara");
		p.setIngredienti(ingredienti);
		List<Piatto> piatti = new LinkedList<Piatto>();
		piatti.add(p);
		roma.setPiatti(piatti);
		Chef chef = new Chef();
		chef.setNome("chef");
		chef.setCognome("cognome chef");
		chef.setNazionalita("Italia");
		roma.setChef(chef);
		List<Buffet> buffet = new LinkedList<Buffet>();
		buffet.add(roma);
		Buffet napoli = new Buffet();
		napoli.setNome("Buffet Napoli");
		//napoli.setImg("napoli.jpg");
		napoli.setChef(chef);
		buffet.add(napoli);
		chef.setBuffet(buffet);
		ingredienteRepository.save(uovo);
		chefRepository.save(chef);
		piattoRepository.save(p);
		buffetRepository.save(roma);
		
		buffetRepository.save(napoli);
		
		
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
