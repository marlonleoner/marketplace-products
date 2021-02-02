package br.com.ufsm.produtoapi.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.ufsm.produtoapi.controller.dto.ProdutoDto;
import br.com.ufsm.produtoapi.controller.form.AtualizacaoDisponibilidadeForm;
import br.com.ufsm.produtoapi.controller.form.AtualizacaoProdutoForm;
import br.com.ufsm.produtoapi.controller.form.ProdutoForm;
import br.com.ufsm.produtoapi.modelo.Produto;
import br.com.ufsm.produtoapi.modelo.TipoProduto;
import br.com.ufsm.produtoapi.repository.ProdutoRepository;

@Service
public class ProdutosService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	
	public List<Produto> findAll(String nome, Double precoMin, Double precoMax, TipoProduto tipo){
		List<Produto> produtos = produtoRepository.findAll();
		if(nome != null) {
			for (Produto p : produtos) {
				if(p.getNome() != nome) produtos.remove(p);
				//TODO muito ineficiente ???
			}
		}
		if(precoMin != null) {
			for (Produto p : produtos) {
				if(p.getPreco() < precoMin) produtos.remove(p);
			}
		}
		if(precoMax != null) {
			for (Produto p : produtos) {
				if(p.getPreco() > precoMax) produtos.remove(p);
			}
		}
		if(tipo != null) {
			for (Produto p : produtos) {
				if(p.getTipo() != tipo) produtos.remove(p);
			}
		}
		return produtos;
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
	
	public List<Produto> findPrecoDisp(List<Long> ids) {
		List<Produto> list = new ArrayList<>();
		for (Long id : ids) {
			Optional<Produto> produto = produtoRepository.findById(id);
			if (produto.isPresent() && produto.get().getDisponibilidade()>0) {
				list.add(produto.get());
			}
			else{
				list.add(null);
			}
		}
		return list;
	}
	
	public ResponseEntity<ProdutoDto> findById(Long id) {
		Optional<Produto> produto = produtoRepository.findById(id);
		if (produto.isPresent()) {
			return ResponseEntity.ok(new ProdutoDto(produto.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	public Produto create(ProdutoForm form) {
		Produto produto = form.converter();
		List<Produto> produtos = produtoRepository.findAll();
		for (Produto p : produtos) {
			if (p.equals(produto)) return null;
		}
		produtoRepository.save(produto);
		return produto;
	}
	
	public ProdutoDto atualizarProduto(Long id, @Valid AtualizacaoProdutoForm form) {
		Optional<Produto> optional = produtoRepository.findById(id);
		if (optional.isPresent()) {
			Produto p = optional.get();
			p.setDisponibilidade(form.getDisponibilidade());
			p.setNome(form.getNome());
			p.setPreco(form.getPreco());
			produtoRepository.save(p);
			return new ProdutoDto(p);
		}
		return null;
	}

	public ProdutoDto atualizarDisp(Long id, @Valid AtualizacaoDisponibilidadeForm form) {
		Optional<Produto> optional = produtoRepository.findById(id);
		if (optional.isPresent()) {
			Produto p = optional.get();
			p.setDisponibilidade(form.getDisponibilidade());
			produtoRepository.save(p);
			return new ProdutoDto(p);
		}
		return null;
	}
	
	public ResponseEntity<?> removerProduto(@PathVariable Long id) {
		Optional<Produto> optional = produtoRepository.findById(id);
		if (optional.isPresent()) {
			produtoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

	
	
}
