package com.example.demo.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;

@Entity
public class Ingrediente {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	@NotBlank
	private String nome;
	@NotBlank
	private String descrizione;
	@NotBlank
	private String origine;
	
	private String img;
	
	public Ingrediente() {}
	
	public Ingrediente(String nome, String descrizione, String origine, String img) {
		this.nome = nome;
		this.descrizione = descrizione;
		this.origine = origine;
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

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getOrigine() {
		return origine;
	}

	public void setOrigine(String origine) {
		this.origine = origine;
	}
	

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
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
		Ingrediente other = (Ingrediente) obj;
		return this.getNome().equals(other.getNome());
	}
}
