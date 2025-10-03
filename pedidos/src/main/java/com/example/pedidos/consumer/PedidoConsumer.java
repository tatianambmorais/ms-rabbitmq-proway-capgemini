package com.example.pedidos.consumer;

import com.example.pedidos.dto.PedidoEstoqueMessageDTO;
import com.example.pedidos.service.PedidoService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;


public class PedidoConsumer {

//    @Autowired
//    PedidoService pedidoService;

    @RabbitListener(queues = "${broker.queue.pedido.status}")
    public void alteraStatusPedido(PedidoEstoqueMessageDTO mensagem){
        System.out.println("Recebendo mensagem");
        pedidoService.alteraStatusPedido(mensagem);

    }

}
