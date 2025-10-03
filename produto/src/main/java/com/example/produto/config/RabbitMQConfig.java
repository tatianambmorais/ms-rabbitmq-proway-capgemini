package com.example.produto.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${broker.queue.produto.estoque}")
    private String produtoEstoqueQueue;

    @Bean
    public Queue queueProdutoEstoque() {
        return new Queue(produtoEstoqueQueue, true);
    }

    @Value("${broker.queue.pedido.status}")
    private String pedidoStatusQueue;

    @Bean
    public Queue queuePedidoStatus() {
        return new Queue(pedidoStatusQueue, true);
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter(){
        ObjectMapper objectMapper = new ObjectMapper();
        return new Jackson2JsonMessageConverter(objectMapper);
    }
}