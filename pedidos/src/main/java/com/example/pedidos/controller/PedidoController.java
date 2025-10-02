package com.example.pedidos.controller;

import com.example.pedidos.service.PedidoService;
import com.example.pedidos.domain.Pedido;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/pedidos")
public class PedidoController {

    private PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<Pedido> criar(@RequestBody List<Long> idsProdutos) {
        return ResponseEntity.ok(pedidoService.criarPedido(idsProdutos));
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> listar() {
        return ResponseEntity.ok(pedidoService.listarPedidos());
    }
}