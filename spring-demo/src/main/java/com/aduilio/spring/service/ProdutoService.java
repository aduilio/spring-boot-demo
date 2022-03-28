package com.aduilio.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aduilio.spring.entity.Produto;
import com.aduilio.spring.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repository;

	public Long create(Produto produto) {
		return repository.save(produto).getId();
	}

	public Produto read(Long id) {
		Optional<Produto> result = repository.findById(id);
		if (result.isPresent()) {
			return result.get();
		} else {
			return null;
		}
	}

	public void update(Long id, Produto produto) {
		produto.setId(id);
		repository.save(produto);
	}

	public List<Produto> list(String marca, String nome) {
		if (marca != null || nome != null) {
			return repository.find(marca, nome);
		} else {
			return repository.findAll();
		}
	}
}
