package com.example.produto.controller;

import com.example.produto.service.ProdutoService;
import com.example.produto.domain.Produto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/produtos")
public class ProdutoController {

    private ProdutoService service;

    @PostMapping
    public ResponseEntity<Produto> cadastrar(@RequestBody Produto produto) {
        return ResponseEntity.ok(service.salvar(produto));
    }
//
//    @PutMapping("/{id}/vender")
//    public ResponseEntity<Produto> vender(@PathVariable Long id, @RequestParam int quantidade) {
//        return ResponseEntity.ok(service.atualizarEstoque(id, quantidade));
//    }

    @GetMapping
    public ResponseEntity<List<Produto>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }
}
