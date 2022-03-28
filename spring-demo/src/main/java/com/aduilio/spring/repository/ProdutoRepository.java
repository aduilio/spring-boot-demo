package com.aduilio.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.aduilio.spring.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	@Query("SELECT p FROM Produto p WHERE p.marca =:marca OR p.nome =:nome")
	List<Produto> find(@Param("marca") String marca, @Param("nome") String nome);

}
