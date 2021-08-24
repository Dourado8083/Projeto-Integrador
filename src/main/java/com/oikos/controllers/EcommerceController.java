package com.oikos.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oikos.models.Ecommerce;
import com.oikos.models.dtos.EcommerceDTO;
import com.oikos.repositories.EcommerceRepository;
import com.oikos.services.EcommerceService;

@RestController
@RequestMapping("/ecommerce")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EcommerceController {
	
	@Autowired
	EcommerceRepository ecommerceRepository;

	@Autowired
	EcommerceService ecommerceService;
	
	@GetMapping("/all")
	public ResponseEntity<List<Ecommerce>> GetAll() {
		return ResponseEntity.ok(ecommerceRepository.findAll());
	}
	
	@GetMapping("/{ecommerceId}")
	public ResponseEntity<Ecommerce> GetEcommercetById(@PathVariable long ecommerceId) {
		return ecommerceRepository.findById(ecommerceId).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping("/create-ecommerce")
	public ResponseEntity<?> createEcommerce(@Valid @RequestBody EcommerceDTO ecommerceDto) {
		return ecommerceService.createEcommerce(ecommerceDto).map(newEcommerce -> {
			return ResponseEntity.status(201).build();
		}).orElse(ResponseEntity.status(400).build());
	}
	
	
}
