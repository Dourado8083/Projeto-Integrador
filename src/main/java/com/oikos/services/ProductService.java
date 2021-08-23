package com.oikos.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oikos.models.Business;
import com.oikos.models.Product;
import com.oikos.models.dtos.ProductDTO;
import com.oikos.repositories.BusinessRepository;
import com.oikos.repositories.EcommerceRepository;
import com.oikos.repositories.ProductRepository;

@Service
public class ProductService {
	
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	BusinessRepository businessRepository;
	
	@Autowired
	EcommerceRepository ecommerceRepository;
	
	/**
	 * Método para cadastrar um novo produto
	 * 
	 * @param ProductDTO
	 * @return Um Optional contendo o produto cadastrado ou vázio para ser tratado como
	 *         erro.
	 */
	public Optional<Product> registerProduct(ProductDTO productDto) {
		return ecommerceRepository.findById(productDto.getEcommerceOnId()).map(ecommerce -> {
			
			Product product = new Product();
			product.setEcommerceOn(ecommerce);
			product.setProductName(productDto.getProductName());
			product.setProductPrice(productDto.getProductPrice());
			product.setProductDescription(product.getProductDescription());
			product.setProductAmount(product.getProductAmount());

			ecommerce.getProductList().add(product);
			
			productRepository.save(product);
			ecommerceRepository.save(ecommerce);
			
			return Optional.ofNullable(product);

		}).orElse(Optional.empty());
	}
	
	
}
