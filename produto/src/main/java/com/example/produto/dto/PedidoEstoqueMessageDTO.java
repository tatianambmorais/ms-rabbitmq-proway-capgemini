package com.example.produto.dto;


import com.example.produto.domain.StatusPedido;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PedidoEstoqueMessageDTO {
    private Long idPedido;
    private List<Long> listaProdutos;
    private StatusPedido statusPedido;

}