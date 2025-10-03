package com.example.pedidos.service;

import com.example.pedidos.domain.Pedido;
import com.example.pedidos.domain.StatusPedido;
import com.example.pedidos.dto.PedidoEstoqueMessageDTO;
import com.example.pedidos.producer.PedidoProducer;
import com.example.pedidos.repository.PedidoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PedidoService {

    private PedidoProducer pedidoProducer;
    private PedidoRepository pedidoRepository;

    public Pedido criarPedido(List<Long> idsProdutos) {
        Pedido pedido = new Pedido();
        pedido.setListaProdutos(idsProdutos);
        pedido.setValorTotal(idsProdutos.size() * 100.0);
        pedido.setStatus(StatusPedido.EM_PROCESSAMENTO);

        Pedido pedidoSalvo = pedidoRepository.save(pedido);

        PedidoEstoqueMessageDTO messageDTO = new PedidoEstoqueMessageDTO();
        messageDTO.setIdPedido(pedidoSalvo.getIdPedido());
        messageDTO.setListaProdutos(idsProdutos);

        pedidoProducer.publishEstoqueMessage(messageDTO);

        return pedidoSalvo;
    }

    public void alteraStatusPedido(PedidoEstoqueMessageDTO mensagem){
        Optional<Pedido> pedido = pedidoRepository.findById(mensagem.getIdPedido());
        pedido.get().setStatus(mensagem.getStatusPedido());
        System.out.println("Status do pedido alterado");
    }


    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }


}