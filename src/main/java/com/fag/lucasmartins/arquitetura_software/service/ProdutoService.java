package com.fag.lucasmartins.arquitetura_software.service;

import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    public double calcularPrecoFinal(String nome, double preco, Integer estoque) {
        // Regra do Premium
        if (nome != null && nome.toLowerCase().contains("premium") && preco < 100.0) {
            throw new RuntimeException("Produtos Premium não podem custar menos de R$ 100,00.");
        }

        if (estoque != null && estoque >= 50) {
            return preco - (preco * 0.10);
        }

        return preco;
    }
}