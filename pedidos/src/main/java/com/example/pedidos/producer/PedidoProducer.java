package com.example.pedidos.producer;

import com.example.pedidos.dto.PedidoEstoqueMessageDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PedidoProducer {

    private final RabbitTemplate rabbitTemplate;

    public PedidoProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value("${broker.queue.produto.estoque}")
    private String routingKey;

    public void publishEstoqueMessage(PedidoEstoqueMessageDTO messageDTO) {
        rabbitTemplate.convertAndSend("", routingKey, messageDTO);
    }

}
