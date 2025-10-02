package com.example.produto.service;


import com.example.produto.domain.Produto;
import com.example.produto.dto.PedidoEstoqueMessageDTO;
import com.example.produto.repository.ProdutoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProdutoService {

    private final ProdutoRepository repository;

    public Produto salvar(Produto produto) {
        return repository.save(produto);
    }

    public Produto atualizarEstoque(Long id, int quantidadeVendida) {
        Produto produto = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        if (produto.getQuantidadeEmEstoque() < quantidadeVendida) {
            throw new RuntimeException("Estoque insuficiente");
        }

        produto.setQuantidadeEmEstoque(produto.getQuantidadeEmEstoque() - quantidadeVendida);
        return repository.save(produto);
    }

    public List<Produto> listarTodos() {
        return repository.findAll();
    }

    public void processarAtualizacaoEstoque(PedidoEstoqueMessageDTO mensagem) {
        for (Long idProduto : mensagem.getListaProdutos()) {
            try {
                atualizarEstoque(idProduto, 1);
            } catch (RuntimeException e) {
                System.out.println("Erro ao atualizar produto " + idProduto + ": " + e.getMessage());
            }
        }

        System.out.println("Estoque atualizado para pedido: " + mensagem.getIdPedido());
    }
}