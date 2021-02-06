package br.com.ufsm.productapi.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.ufsm.productapi.controller.dto.ProductDto;
import br.com.ufsm.productapi.controller.form.ProductForm;
import br.com.ufsm.productapi.controller.form.UpdateDispForm;
import br.com.ufsm.productapi.controller.form.UpdateProductForm;
import br.com.ufsm.productapi.model.Product;
import br.com.ufsm.productapi.model.TypeEnum;
import br.com.ufsm.productapi.repository.ProductRepository;

@Service
public class ProductsService {

	@Autowired
	private ProductRepository productRepository;
	
	
	public List<Product> findAll(String name, Double minPrice, Double maxPrice, TypeEnum type){
		List<Product> products = productRepository.findAll();
		if(name != null) {
			for (Product p : products) {
				if(p.getName() != name) products.remove(p);
				//TODO muito ineficiente ???
			}
		}
		if(minPrice != null) {
			for (Product p : products) {
				if(p.getPrice() < minPrice) products.remove(p);
			}
		}
		if(maxPrice != null) {
			for (Product p : products) {
				if(p.getPrice() > maxPrice) products.remove(p);
			}
		}
		if(type != null) {
			for (Product p : products) {
				if(p.getTypeEnum() != type) products.remove(p);
			}
		}
		return products;
	}
	
	/*
	public ResponseEntity<ProdutoDto> findByNome(String nome) {
		Optional<Produto> produto = produtoRepository.findByNome(nome);
		if (produto.isPresent()) {
			return ResponseEntity.ok(new ProdutoDto(produto.get()));
		}
		return ResponseEntity.notFound().build();
	}
	*/
	
	public List<Product> findPrceDisp(List<Long> ids) {
		List<Product> list = new ArrayList<>();
		for (Long id : ids) {
			Optional<Product> product = productRepository.findById(id);
			if (product.isPresent() && product.get().getDisponibility()>0) {
				list.add(product.get());
			}
			else{
				list.add(null);
			}
		}
		return list;
	}
	
	public ResponseEntity<ProductDto> findById(Long id) {
		Optional<Product> product = productRepository.findById(id);
		if (product.isPresent()) {
			return ResponseEntity.ok(new ProductDto(product.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	public Product create(ProductForm form) {
		Product product = form.converter();
		List<Product> products = productRepository.findAll();
		for (Product p : products) {
			if (p.equals(product)) return null;
		}
		productRepository.save(product);
		return product;
	}
	
	public ProductDto atualizarProduto(Long id, @Valid UpdateProductForm form) {
		Optional<Product> optional = productRepository.findById(id);
		if (optional.isPresent()) {
			Product p = optional.get();
			p.setDisponibility(form.getDisponibilidade());
			p.setName(form.getNome());
			p.setPrice(form.getPreco());
			productRepository.save(p);
			return new ProductDto(p);
		}
		return null;
	}

	public ProductDto atualizarDisp(Long id, @Valid UpdateDispForm form) {
		Optional<Product> optional = productRepository.findById(id);
		if (optional.isPresent()) {
			Product p = optional.get();
			p.setDisponibility(form.getDisponibilidade());
			productRepository.save(p);
			return new ProductDto(p);
		}
		return null;
	}
	
	public ResponseEntity<?> removerProduto(@PathVariable Long id) {
		Optional<Product> optional = productRepository.findById(id);
		if (optional.isPresent()) {
			productRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

	
	
}
