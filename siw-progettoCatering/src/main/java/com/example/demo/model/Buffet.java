package com.example.demo.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;


@Entity
public class Buffet {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	@NotBlank
	private String nome;
	@NotBlank
	private String descrizione;
	
	@ManyToMany(cascade = {CascadeType.PERSIST})
	//@JoinColumn(name = "buffet_id")
	private List<Piatto> piatti;
	
	@ManyToOne
	private Chef chef;
	
	public Buffet() {
		//this.piatti = new ArrayList<Piatto>();
	}

	public Buffet(String nome, String descrizione, List<Piatto> piatti, Chef chef) {
		this.nome = nome;
		this.descrizione = descrizione;
		this.piatti = piatti;
		this.chef = chef;
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

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public List<Piatto> getPiatti() {
		return piatti;
	}

	public void setPiatti(List<Piatto> piatti) {
		this.piatti = piatti;
	}

	public Chef getChef() {
		return chef;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	@Override
	public int hashCode() {
		return this.getNome().hashCode() + 31;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Buffet other = (Buffet) obj;
		return this.getNome().equals(other.getNome());
	}
}
