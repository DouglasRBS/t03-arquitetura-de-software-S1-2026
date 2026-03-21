package com.fag.lucasmartins.arquitetura_software.model.bo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.fag.lucasmartins.arquitetura_software.model.bo.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}