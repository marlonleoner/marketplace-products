package br.com.ufsm.productapi.controller;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import br.com.ufsm.productapi.controller.dto.DisponibiltyDto;
import br.com.ufsm.productapi.controller.dto.ProductDto;
import br.com.ufsm.productapi.controller.form.NewProductForm;
import br.com.ufsm.productapi.controller.form.UpdateProductForm;
import br.com.ufsm.productapi.controller.form.VerifyDisponibilityForm;
import br.com.ufsm.productapi.model.TypeEnum;
import br.com.ufsm.productapi.services.ProductsService;

@RestController
@RequestMapping("/products")
public class ProductsController {

	@Autowired
	private ProductsService productsService;

	@GetMapping
	public List<ProductDto> find(@RequestParam(defaultValue = "") String name,
			@RequestParam(defaultValue = "0.0") Double minPrice,
			@RequestParam(defaultValue = "999999.9") Double maxPrice, TypeEnum type) {
		return ProductDto.converter(productsService.find(name, minPrice, maxPrice, type));
	}

	@GetMapping("/available")
	public List<DisponibiltyDto> getPrecoDisp(@RequestBody VerifyDisponibilityForm form) {
		return productsService.verify(form);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductDto> get(@PathVariable Long id) {
		return ResponseEntity.ok(new ProductDto(productsService.findById(id)));
	}

	@PostMapping
	public ResponseEntity<ProductDto> create(@RequestBody @Valid NewProductForm form, UriComponentsBuilder uriBuilder) {
		ProductDto product = new ProductDto(productsService.create(form));
		URI uri = uriBuilder.path("/products/{id}").buildAndExpand(product.getId()).toUri();
		return ResponseEntity.created(uri).body(product);
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<ProductDto> update(@PathVariable Long id, @RequestBody @Valid UpdateProductForm form) {
		return ResponseEntity.ok(new ProductDto(productsService.update(id, form)));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> remove(@PathVariable Long id) {
		productsService.delete(id);
		return ResponseEntity.ok().build();
	}

}
