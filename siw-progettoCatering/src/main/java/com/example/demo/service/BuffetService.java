package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.BuffetRepository;
@Service
public class BuffetService {

	@Autowired
	private BuffetRepository buffetRepository;
}
