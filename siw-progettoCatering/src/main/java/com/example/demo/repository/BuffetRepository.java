package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Buffet;
import com.example.demo.model.Piatto;

public interface BuffetRepository extends CrudRepository<Buffet, Long> {

	boolean existsByNome(String string);

}
