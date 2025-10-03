package com.example.pedidos.dto;

import com.example.pedidos.domain.StatusPedido;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.util.List;

@Setter
@Getter
public class PedidoEstoqueMessageDTO {
    private Long idPedido;
    private List<Long> listaProdutos;
    private StatusPedido statusPedido;

}