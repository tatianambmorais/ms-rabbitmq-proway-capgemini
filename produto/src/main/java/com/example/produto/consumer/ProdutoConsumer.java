package com.example.produto.consumer;

import com.example.produto.domain.Produto;
import com.example.produto.dto.PedidoEstoqueMessageDTO;
import com.example.produto.repository.ProdutoRepository;
import com.example.produto.service.ProdutoService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;



@Component
public class ProdutoConsumer {

    private final ProdutoService produtoService;

    public ProdutoConsumer(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @RabbitListener(queues = "${broker.queue.produto.estoque}")
    public void receberMensagemEstoque(PedidoEstoqueMessageDTO mensagem) {
        produtoService.processarAtualizacaoEstoque(mensagem);
    }
}