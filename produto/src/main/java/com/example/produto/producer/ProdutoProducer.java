package com.example.produto.producer;

import com.example.produto.dto.PedidoEstoqueMessageDTO;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class ProdutoProducer {
    private final RabbitTemplate rabbitTemplate;

    @Value("${broker.queue.pedido.status}")
    private String routingKey;

    public ProdutoProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishStatusMessage(PedidoEstoqueMessageDTO messageDTO) {
        rabbitTemplate.convertAndSend(routingKey, messageDTO);
    }
}
