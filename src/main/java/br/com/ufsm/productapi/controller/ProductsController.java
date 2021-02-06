package br.com.ufsm.productapi.controller;

import java.net.URI;
import java.util.List;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.ufsm.productapi.controller.dto.ProductDto;
import br.com.ufsm.productapi.controller.dto.ProductPriceDispDto;
import br.com.ufsm.productapi.controller.form.ProductForm;
import br.com.ufsm.productapi.controller.form.UpdateDispForm;
import br.com.ufsm.productapi.controller.form.UpdateProductForm;
import br.com.ufsm.productapi.model.Product;
import br.com.ufsm.productapi.model.TypeEnum;
import br.com.ufsm.productapi.services.ProductsService;

@RestController
@RequestMapping("/products")
public class ProductsController {

	@Autowired
	private ProductsService productsService;
	
	
	@GetMapping
	public List<ProductDto> getAll(String name, Double minPrice, 
			Double maxPrice, TypeEnum type) {
		return ProductDto.converter(productsService.findAll(name, minPrice, maxPrice, type));
	}
	
	/*
	@GetMapping("/{nome}")
	public ResponseEntity<ProdutoDto> getByNome(@PathVariable String nome) {
		return produtosService.findByNome(nome);
	}
	*/
	
	@GetMapping("/{id}")
	public ResponseEntity<ProductDto> getById(@PathVariable Long id) {
		return productsService.findById(id);
	}
	
	@GetMapping("/precodisp")
	public List<ProductPriceDispDto> getPrecoDisp(@RequestParam List<Long> ids) {
		return ProductPriceDispDto.converter(productsService.findPrceDisp(ids));
		//TODO null na lista - produtos not found ou indisponiveis
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<ProductDto> create(@RequestBody @Valid ProductForm form, 
			UriComponentsBuilder uriBuilder) {
		Product product = productsService.create(form);
		if (product == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		URI uri = uriBuilder.path("/products/{id}").buildAndExpand(product.getIdProduct()).toUri();
		return ResponseEntity.created(uri).body(new ProductDto(product));
	}
	
	@PutMapping("/updateproduct/{id}")
	@Transactional
	public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id, 
			@RequestBody @Valid UpdateProductForm form) {
		ProductDto productDto = productsService.atualizarProduto(id, form);
		if (productDto == null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(productDto);
	}
	
	@PutMapping("/updatedisponibility/{id}")
	@Transactional
	public ResponseEntity<ProductDto> updateDisponibility(@PathVariable Long id, 
			@RequestBody @Valid UpdateDispForm form) {
		ProductDto productDto = productsService.atualizarDisp(id, form);
		if (productDto == null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(productDto);
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remove(@PathVariable Long id) {
		return productsService.removerProduto(id);
	}
	
}
