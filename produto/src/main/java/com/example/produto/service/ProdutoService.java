package com.example.produto.service;


import com.example.produto.consumer.ProdutoConsumer;
import com.example.produto.domain.Produto;
import com.example.produto.domain.StatusPedido;
import com.example.produto.dto.PedidoEstoqueMessageDTO;
import com.example.produto.producer.ProdutoProducer;
import com.example.produto.repository.ProdutoRepository;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProdutoService {

    private final ProdutoRepository repository;
    private final ProdutoProducer producer;

    public Produto salvar(Produto produto) {
        return repository.save(produto);
    }

    public Produto atualizarEstoque(Long id, int quantidadeVendida, PedidoEstoqueMessageDTO messageDTO) {
        Produto produto = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        if (produto.getQuantidadeEmEstoque() < quantidadeVendida) {
            throw new RuntimeException("Estoque insuficiente");
        }

        produto.setQuantidadeEmEstoque(produto.getQuantidadeEmEstoque() - quantidadeVendida);
        messageDTO.setStatusPedido(StatusPedido.CONFIRMADO);
        return repository.save(produto);
    }

    public List<Produto> listarTodos() {
        return repository.findAll();
    }

    public void processarAtualizacaoEstoque(PedidoEstoqueMessageDTO mensagem) {
        for (Long idProduto : mensagem.getListaProdutos()) {
            try {
                atualizarEstoque(idProduto, 1, mensagem);
                System.out.println("Estoque atualizado para pedido: " + mensagem.getIdPedido());
            } catch (RuntimeException e) {
                mensagem.setStatusPedido(StatusPedido.CANCELADO);
                System.out.println("Erro ao atualizar produto " + idProduto + ": " + e.getMessage());
                break;
            }

            producer.publishStatusMessage(mensagem);
            System.out.println("Status enviado para pedido: " + mensagem.getIdPedido() + " - " + mensagem.getStatusPedido());

        }

    }
}