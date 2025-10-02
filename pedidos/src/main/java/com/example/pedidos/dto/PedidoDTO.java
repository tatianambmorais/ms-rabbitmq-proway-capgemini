package com.example.pedidos.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PedidoDTO {
    private Long idPedido;
    private List<Long> listaProdutos;
    private Double valorTotal;
    private String status;

}