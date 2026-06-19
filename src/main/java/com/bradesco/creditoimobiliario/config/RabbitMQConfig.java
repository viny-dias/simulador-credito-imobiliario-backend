package com.bradesco.creditoimobiliario.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String FILA_PROPOSTAS = "propostas-credito-queue";

    @Bean
    public Queue filaPropostas() {
        // Cria uma fila durável (se o RabbitMQ reiniciar, as mensagens não somem)
        return new Queue(FILA_PROPOSTAS, true);
    }

    @Bean
    public MessageConverter messageConverter() {
        // SOLUÇÃO OFICIAL SPRING 4 / JAVA 25: Usar a classe atualizada para Jackson 3
        return new JacksonJsonMessageConverter();
    }
}
