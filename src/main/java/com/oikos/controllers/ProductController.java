package com.oikos.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oikos.models.Product;
import com.oikos.models.dtos.ProductDTO;
import com.oikos.repositories.ProductRepository;
import com.oikos.services.ProductService;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {
	
	
	@Autowired
	ProductService productService;
	
	@Autowired
	ProductRepository productRepository;
	
	@GetMapping("/all")
	public ResponseEntity<List<Product>> GetAll() {
		return ResponseEntity.ok(productRepository.findAll());
	}
	
	@PostMapping("/register-product")
	public ResponseEntity<?> registerProduct(@Valid @RequestBody ProductDTO productDto) {
		return productService.registerProduct(productDto).map(newProduct -> {
			return ResponseEntity.status(201).build();
		}).orElse(ResponseEntity.status(400).build());
	}
	
	@PutMapping("/edit-product")
	public ResponseEntity<Product> editProduct(@RequestBody Product product){
		return ResponseEntity.status(HttpStatus.OK).body(productRepository.save(product));
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		productRepository.deleteById(id);
	}	
	
	
}
