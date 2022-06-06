package com.example.demo.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;



@Entity
public class Chef {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	private String nome;
	@NotBlank
	private String cognome;
	@NotBlank
	private String nazionalita;
	
	//@Column(length = 2000)
	@Column(nullable = true)
	private String img;
	
	@OneToMany(cascade = {CascadeType.ALL}, mappedBy= "chef")
	private List<Buffet> buffet;
	
	public Chef() {
		//this.buffet = new ArrayList<Buffet>();
	}
	
	public Chef(String nome, String cognome, String nazionalita, List<Buffet> buffet, String img) {
		this.nome = nome;
		this.cognome = cognome;
		this.nazionalita = nazionalita;
		this.buffet = buffet;
		this.img = img;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getNazionalita() {
		return nazionalita;
	}

	public void setNazionalita(String nazionalita) {
		this.nazionalita = nazionalita;
	}

	public List<Buffet> getBuffet() {
		return buffet;
	}

	public void setBuffet(List<Buffet> buffet) {
		this.buffet = buffet;
	}
	
	
	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	@Override
	public int hashCode() {
		return this.getId().hashCode() + 31;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Chef other = (Chef) obj;
		return this.getId() == other.getId();
	}

}
