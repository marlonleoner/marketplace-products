package br.com.ufsm.produtoapi.controller;

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

import br.com.ufsm.produtoapi.controller.dto.ProdutoDto;
import br.com.ufsm.produtoapi.controller.dto.ProdutoPrecoDispDto;
import br.com.ufsm.produtoapi.controller.form.AtualizacaoDisponibilidadeForm;
import br.com.ufsm.produtoapi.controller.form.AtualizacaoProdutoForm;
import br.com.ufsm.produtoapi.controller.form.ProdutoForm;
import br.com.ufsm.produtoapi.modelo.Produto;
import br.com.ufsm.produtoapi.modelo.TipoProduto;
import br.com.ufsm.produtoapi.services.ProdutosService;

@RestController
@RequestMapping("/produtos")
public class ProdutosController {

	@Autowired
	private ProdutosService produtosService;
	
	
	@GetMapping
	public List<ProdutoDto> getAll(String nome, Double precoMin, 
			Double precoMax, TipoProduto tipo) {
		return ProdutoDto.converter(produtosService.findAll(nome, precoMin, precoMax, tipo));
	}
	
	/*
	@GetMapping("/{nome}")
	public ResponseEntity<ProdutoDto> getByNome(@PathVariable String nome) {
		return produtosService.findByNome(nome);
	}
	*/
	
	@GetMapping("/{id}")
	public ResponseEntity<ProdutoDto> getById(@PathVariable Long id) {
		return produtosService.findById(id);
	}
	
	@GetMapping("/precodisp")
	public List<ProdutoPrecoDispDto> getPrecoDisp(@RequestParam List<Long> ids) {
		return ProdutoPrecoDispDto.converter(produtosService.findPrecoDisp(ids));
		//TODO null na lista - produtos not found ou indisponiveis
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<ProdutoDto> create(@RequestBody @Valid ProdutoForm form, 
			UriComponentsBuilder uriBuilder) {
		Produto produto = produtosService.create(form);
		if (produto == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		URI uri = uriBuilder.path("/produtos/{id}").buildAndExpand(produto.getIdProduto()).toUri();
		return ResponseEntity.created(uri).body(new ProdutoDto(produto));
	}
	
	@PutMapping("/atualizarproduto/{id}")
	@Transactional
	public ResponseEntity<ProdutoDto> atualizarProduto(@PathVariable Long id, 
			@RequestBody @Valid AtualizacaoProdutoForm form) {
		ProdutoDto produtoDto = produtosService.atualizarProduto(id, form);
		if (produtoDto == null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(produtoDto);
	}
	
	@PutMapping("/atualizardisponibilidade/{id}")
	@Transactional
	public ResponseEntity<ProdutoDto> atualizarDisponibilidade(@PathVariable Long id, 
			@RequestBody @Valid AtualizacaoDisponibilidadeForm form) {
		ProdutoDto produtoDto = produtosService.atualizarDisp(id, form);
		if (produtoDto == null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(produtoDto);
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remove(@PathVariable Long id) {
		return produtosService.removerProduto(id);
	}
	
}
