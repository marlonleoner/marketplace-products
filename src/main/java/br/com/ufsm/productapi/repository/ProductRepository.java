package br.com.ufsm.productapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ufsm.productapi.model.Product;


public interface ProductRepository extends JpaRepository<Product, Long> {

	Optional<Product> findByName(String name);
	
}