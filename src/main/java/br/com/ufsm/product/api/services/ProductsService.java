package br.com.ufsm.product.api.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.ufsm.product.api.controller.dto.DisponibiltyDto;
import br.com.ufsm.product.api.controller.form.NewProductForm;
import br.com.ufsm.product.api.controller.form.ProductDisponibilityForm;
import br.com.ufsm.product.api.controller.form.UpdateProductForm;
import br.com.ufsm.product.api.controller.form.VerifyDisponibilityForm;
import br.com.ufsm.product.api.exceptions.ObjectAlreadyExistsException;
import br.com.ufsm.product.api.exceptions.ObjectNotFoundException;
import br.com.ufsm.product.api.exceptions.UnavailableProductException;
import br.com.ufsm.product.api.model.Product;
import br.com.ufsm.product.api.model.TypeEnum;
import br.com.ufsm.product.api.model.UnavailableProductError;
import br.com.ufsm.product.api.repository.ProductRepository;

@Service
public class ProductsService {

	@Autowired
	private ProductRepository productRepository;

	public List<Product> find(String name, Double minPrice, Double maxPrice, TypeEnum type) {
		List<Product> products = productRepository.findByNameContainsAndPriceGreaterThanAndPriceLessThan(name, minPrice,
				maxPrice);

//		if (type != null) {
//			for (Product p : products) {
//				if (p.getType() != type)
//					products.remove(p);
//			}
//		}

		return products;
	}

	public List<DisponibiltyDto> verify(VerifyDisponibilityForm form) {
		List<DisponibiltyDto> products = new ArrayList<>();

		List<UnavailableProductError> errors = new ArrayList<>();

		List<ProductDisponibilityForm> formProducts = form.getProducts();
		for (ProductDisponibilityForm p : formProducts) {
			Optional<Product> productExists = productRepository.findById(p.getId());
			if (productExists.isPresent()) {
				Product product = productExists.get();
				if (product.isAvailable(p.getAmount())) {
					products.add(new DisponibiltyDto(product, p.getAmount()));
				} else {
					errors.add(new UnavailableProductError("InvalidAmount", "Quantidade indisponivel.", p.getId()));
				}
			} else {
				errors.add(new UnavailableProductError("ObjectNotFound", "Produto não encontrado.", p.getId()));
			}
		}

		if (!errors.isEmpty()) {
			UnavailableProductException ex = new UnavailableProductException("");
			ex.setErrors(errors);
			throw ex;
		}

		return products;
	}

	public Product findById(Long id) {
		Optional<Product> product = productRepository.findById(id);
		if (!product.isPresent()) {
			throw new ObjectNotFoundException("Produto não encontrado.");
		}

		return product.get();
	}

	public Product create(NewProductForm form) {
		Optional<Product> productExists = productRepository.findByName(form.getName());
		if (productExists.isPresent()) {
			throw new ObjectAlreadyExistsException("Produto já cadastrado.");
		}

		Product product = form.converter();
		productRepository.save(product);

		return product;
	}

	public Product update(Long id, @Valid UpdateProductForm form) {
		Optional<Product> productExists = productRepository.findById(id);
		if (!productExists.isPresent()) {
			throw new ObjectNotFoundException("Produto não encontrado.");
		}

		Product product = productExists.get();
		product.setName(form.getName());
		product.setDescription(form.getDescription());
		product.setPrice(form.getPrice());
		product.setAmount(form.getAmount());
		product.setUpdatedAt(LocalDateTime.now());

		return product;
	}

	public ResponseEntity<?> delete(@PathVariable Long id) {
		Optional<Product> productExists = productRepository.findById(id);
		if (!productExists.isPresent()) {
			throw new ObjectNotFoundException("Produto não encontrado.");
		}

		productRepository.deleteById(id);

		return ResponseEntity.ok().build();
	}

}
