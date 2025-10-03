package com.example.pedidos.dto;

import com.example.pedidos.domain.StatusPedido;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PedidoDTO {
    private Long idPedido;
    private List<Long> listaProdutos;
    private Double valorTotal;
    private StatusPedido status;

}