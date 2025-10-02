package com.example.pedidos.dto;

import lombok.Getter;

@Getter
public class ProdutoDTO {
    private Long id;
    private String nome;
    private Double preco;
    private Integer quantidadeEmEstoque;

}