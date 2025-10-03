package com.example.pedidos.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPedido;

    @ElementCollection
    private List<Long> listaProdutos;

    private Double valorTotal;
    private StatusPedido status;

}