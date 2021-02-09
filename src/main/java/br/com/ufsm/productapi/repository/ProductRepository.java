package br.com.ufsm.productapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ufsm.productapi.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	Optional<Product> findByName(String name);

	Optional<Product> findByIdAndAmountGreaterThanAndAmountLessThan(Long id, Integer minAmount, Integer maxAmount);

	List<Product> findByNameContains(String name);

	List<Product> findByPriceGreaterThanAndPriceLessThan(Double min, Double max);

	List<Product> findByNameContainsAndPriceGreaterThanAndPriceLessThan(String name, Double min, Double max);

	List<Product> findByIdIn(List<Long> ids);
	
	
}
