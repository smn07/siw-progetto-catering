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
public class SiwProgettoCateringApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(SiwProgettoCateringApplication.class, args);
	}

}
