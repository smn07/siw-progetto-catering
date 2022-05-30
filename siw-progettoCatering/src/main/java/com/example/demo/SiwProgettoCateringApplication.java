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
public class SiwProgettoCateringApplication implements CommandLineRunner{
	
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

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		
		//System.out.println("Popolazione DB");
		
		/*Ingrediente sale = new Ingrediente();
		sale.setNome("sale");
		sale.setDescrizione("sale desc");
		
		Ingrediente pepe = new Ingrediente();
		pepe.setNome("pepe");
		pepe.setDescrizione("pepe desc");
		
		Ingrediente pomodoro = new Ingrediente();
		pomodoro.setNome("pomodoro");
		pomodoro.setDescrizione("pomodoro desc");
		
		Ingrediente pesto = new Ingrediente();
		pesto.setNome("pesto");
		pesto.setDescrizione("pesto desc");
		
		this.ingredienteRepository.save(sale);
		this.ingredienteRepository.save(pepe);
		this.ingredienteRepository.save(pomodoro);
		this.ingredienteRepository.save(pesto);
		
		Piatto p1 = new Piatto();
		p1.setNome("p1");
		p1.setDescrizione("p1 desc");
		List<Ingrediente> ingredienti = new ArrayList<Ingrediente>();
		p1.setIngredienti(ingredienti);
		p1.getIngredienti().add(sale);
		p1.getIngredienti().add(pepe);
		p1.getIngredienti().add(pomodoro);
		p1.getIngredienti().add(pesto);
		
		Piatto p2 = new Piatto();
		p2.setNome("p2");
		p2.setDescrizione("p2 desc");
		List<Ingrediente> ingredienti1 = new ArrayList<Ingrediente>();
		p2.setIngredienti(ingredienti1);
		p2.getIngredienti().add(pepe);
		
		Piatto p3 = new Piatto();
		p3.setNome("p3");
		p3.setDescrizione("p3 desc");
		List<Ingrediente> ingredienti2 = new ArrayList<Ingrediente>();
		p3.setIngredienti(ingredienti2);
		p3.getIngredienti().add(pepe);
		
		Piatto p4 = new Piatto();
		p4.setNome("p4");
		p4.setDescrizione("p4 desc");
		List<Ingrediente> ingredienti3 = new ArrayList<Ingrediente>();
		p4.setIngredienti(ingredienti3);
		p4.getIngredienti().add(pepe);
		
		Piatto p5 = new Piatto();
		p5.setNome("p5");
		p5.setDescrizione("p5 desc");
		List<Ingrediente> ingredienti4 = new ArrayList<Ingrediente>();
		p5.setIngredienti(ingredienti4);
		p5.getIngredienti().add(pepe);
		
		Piatto p6 = new Piatto();
		p6.setNome("p6");
		p6.setDescrizione("p6 desc");
		List<Ingrediente> ingredienti5 = new ArrayList<Ingrediente>();
		p6.setIngredienti(ingredienti5);
		p6.getIngredienti().add(pepe);
		
		Piatto p7 = new Piatto();
		p7.setNome("p7");
		p7.setDescrizione("p7 desc");
		List<Ingrediente> ingredienti6 = new ArrayList<Ingrediente>();
		p7.setIngredienti(ingredienti6);
		p7.getIngredienti().add(pepe);
		
		this.piattoRepository.save(p1);
		this.piattoRepository.save(p2);
		this.piattoRepository.save(p3);
		this.piattoRepository.save(p4);
		this.piattoRepository.save(p5);
		this.piattoRepository.save(p6);
		this.piattoRepository.save(p7);
		
		
		Chef barbieri = new Chef();
		barbieri.setNome("Bruno");
		barbieri.setCognome("Barbieri");
		barbieri.setNazionalita("Italiana");
		List<Buffet> buffet = new ArrayList<Buffet>();
		barbieri.setBuffet(buffet);
		
		Chef cannavacciuolo = new Chef();
		cannavacciuolo.setNome("Antonino");
		cannavacciuolo.setCognome("Cannavacciuolo");
		cannavacciuolo.setNazionalita("Italiana");
		List<Buffet> buffet1 = new ArrayList<Buffet>();
		cannavacciuolo.setBuffet(buffet1);
		
		Chef cracco = new Chef();
		cracco.setNome("Carlo");
		cracco.setCognome("Cracco");
		cracco.setNazionalita("italiana");
		List<Buffet> buffet2 = new ArrayList<Buffet>();
		cracco.setBuffet(buffet2);
		
		this.chefRepository.save(barbieri);
		this.chefRepository.save(cannavacciuolo);
		this.chefRepository.save(cracco);
		
		Buffet b1 = new Buffet();
		b1.setNome("b1");
		b1.setChef(barbieri);
		b1.setDescrizione("b1 desc");
		List<Piatto> piatti = new ArrayList<Piatto>();
		b1.setPiatti(piatti);
		
		Buffet b2 = new Buffet();
		b2.setNome("b2");
		b2.setChef(cannavacciuolo);
		b2.setDescrizione("b2 desc");
		List<Piatto> piatti1 = new ArrayList<Piatto>();
		b2.setPiatti(piatti1);
		
		Buffet b3 = new Buffet();
		b3.setNome("b3");
		b3.setChef(barbieri);
		b3.setDescrizione("b3 desc");
		List<Piatto> piatti2 = new ArrayList<Piatto>();
		b3.setPiatti(piatti2);
		
		Buffet b4 = new Buffet();
		b4.setNome("b4");
		b4.setChef(barbieri);
		b4.setDescrizione("b4 desc");
		List<Piatto> piatti3 = new ArrayList<Piatto>();
		b4.setPiatti(piatti3);
		
		Buffet b5 = new Buffet();
		b5.setNome("b5");
		b5.setChef(barbieri);
		b5.setDescrizione("b5 desc");
		List<Piatto> piatti4 = new ArrayList<Piatto>();
		b5.setPiatti(piatti4);
		
		Buffet b6 = new Buffet();
		b6.setNome("b6");
		b6.setChef(cracco);
		b6.setDescrizione("b6 desc");
		List<Piatto> piatti5 = new ArrayList<Piatto>();
		b6.setPiatti(piatti5);
		
		Buffet b7 = new Buffet();
		b7.setNome("b7");
		b7.setChef(barbieri);
		b7.setDescrizione("b7 desc");
		List<Piatto> piatti6 = new ArrayList<Piatto>();
		b7.setPiatti(piatti6);
		
		b1.getPiatti().add(p1);
		b1.getPiatti().add(p2);
		b1.getPiatti().add(p2);
		b1.getPiatti().add(p3);
		b1.getPiatti().add(p4);
		b1.getPiatti().add(p5);
		b1.getPiatti().add(p6);
		b2.getPiatti().add(p7);
		b3.getPiatti().add(p3);
		b4.getPiatti().add(p4);
		b5.getPiatti().add(p5);
		b6.getPiatti().add(p6);
		b7.getPiatti().add(p7);

		this.buffetRepository.save(b1);
		this.buffetRepository.save(b2);
		this.buffetRepository.save(b3);
		this.buffetRepository.save(b4);
		this.buffetRepository.save(b5);
		this.buffetRepository.save(b6);
		this.buffetRepository.save(b7);
		
	
		barbieri.getBuffet().add(b3);
		cannavacciuolo.getBuffet().add(b2);
		barbieri.getBuffet().add(b1);
		barbieri.getBuffet().add(b4);
		barbieri.getBuffet().add(b5);
		cracco.getBuffet().add(b6);
		barbieri.getBuffet().add(b7);
		
	}*/
		
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
		/*Buffet palermo = new Buffet();
		palermo.setNome("Buffet Palermo");
		//palermo.setImg("sicilia.jpeg");
		buffetRepository.save(palermo);
		Buffet veggie = new Buffet();
		Piatto p1 = new Piatto();
		p1.setNome("bruschetta");
		piattoRepository.save(p1);
		veggie.setNome("Buffet Vegetariano");
		//veggie.setImg("veggie.jpg");
		List<Piatto> piattiV = new LinkedList<Piatto>();
		piattiV.add(p1);
		veggie.setPiatti(piattiV);
		buffetRepository.save(veggie);
*/
		
	}
}
