package com.bradesco.creditoimobiliario.producer;

import com.bradesco.creditoimobiliario.config.RabbitMQConfig;
import com.bradesco.creditoimobiliario.dto.SimulacaoRequestDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class SimulacaoProducer {

    private final RabbitTemplate rabbitTemplate;

    // Injeção da ferramenta do Spring usada para enviar mensagens para o RabbitMQ
    public SimulacaoProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void enviarPropostaParaFila(SimulacaoRequestDTO request) {
        // Envia o DTO transformado em JSON direto para a nossa fila
        rabbitTemplate.convertAndSend(RabbitMQConfig.FILA_PROPOSTAS, request);
        System.out.println(">>> [PRODUCER] Proposta do CPF " + request.cpfCliente() + " enviada com sucesso para a fila!");
    }
}
