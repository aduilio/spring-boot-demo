package com.aduilio.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aduilio.spring.entity.Produto;
import com.aduilio.spring.service.ProdutoService;

@RestController
@RequestMapping("/api/v1/produto")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	@PostMapping
	public ResponseEntity<Produto> create(@RequestBody Produto produto) {
		Long id = produtoService.create(produto);
		produto.setId(id);

		return ResponseEntity.status(HttpStatus.CREATED).body(produto);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Produto> read(@PathVariable Long id) {
		Produto produto = produtoService.read(id);
		if (produto != null) {
			return ResponseEntity.ok(produto);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping
	public List<Produto> listMarca(@RequestParam(required = false) String marca,
			@RequestParam(required = false) String nome) {
		return produtoService.list(marca, nome);
	}

	@PutMapping("/{id}")
	public Produto update(@PathVariable Long id, @RequestBody Produto produto) {
		produtoService.update(id, produto);
		produto.setId(id);
		return produto;
	}
}
