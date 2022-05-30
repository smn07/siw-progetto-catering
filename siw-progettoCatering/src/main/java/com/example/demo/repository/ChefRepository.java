package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Buffet;
import com.example.demo.model.Chef;

public interface ChefRepository extends CrudRepository<Chef, Long> {

	@Query("SELECT buffet FROM Chef WHERE id = ?1")
	public List<Buffet> findAllBuffet(Long id);
}
