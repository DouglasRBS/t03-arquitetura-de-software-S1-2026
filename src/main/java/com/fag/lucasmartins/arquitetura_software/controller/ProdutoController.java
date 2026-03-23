package com.fag.lucasmartins.arquitetura_software.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fag.lucasmartins.arquitetura_software.model.bo.entity.Produto;
import com.fag.lucasmartins.arquitetura_software.model.bo.repository.ProdutoRepository;
import com.fag.lucasmartins.arquitetura_software.service.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository; 

    @Autowired
    private ProdutoService produtoService; 

    @GetMapping
    public ResponseEntity<List<Produto>> listarTodos() {
        try {
            // CORREÇÃO AQUI: Mudamos de List<Object> para List<Produto>
            List<Produto> lista = repository.findAll();
            return ResponseEntity.ok(lista);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<Object> cadastrarProduto(@RequestBody Produto produto) {
        try {
            double precoFinal = produtoService.calcularPrecoFinal(
                produto.getNome(), 
                produto.getPreco(), 
                produto.getEstoque()
            );

            produto.setPrecoFinal(precoFinal);
            Produto produtoSalvo = repository.save(produto);

            return ResponseEntity.status(HttpStatus.CREATED).body(produtoSalvo);
            
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body("Erro interno: " + e.getMessage());
        }
    }
}