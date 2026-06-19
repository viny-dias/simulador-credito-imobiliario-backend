package com.bradesco.creditoimobiliario.consumer;

import com.bradesco.creditoimobiliario.config.RabbitMQConfig;
import com.bradesco.creditoimobiliario.dto.SimulacaoRequestDTO;
import com.bradesco.creditoimobiliario.service.SimulacaoService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class SimulacaoConsumer {

    private final SimulacaoService simulacaoService;

    // Injetando a Service para poder usar as regras de negócio de aprovação
    public SimulacaoConsumer(SimulacaoService simulacaoService) {
        this.simulacaoService = simulacaoService;
    }

    // A anotação abaixo faz o Java ficar vigiando a fila em tempo real 24/7
    @RabbitListener(queues = RabbitMQConfig.FILA_PROPOSTAS)
    public void consumirProposta(SimulacaoRequestDTO request) {
        System.out.println(">>> [CONSUMER] Nova proposta capturada da fila para o CPF: " + request.cpfCliente());

        // Dispara a lógica real: calcula as regras do Bradesco e salva no PostgreSQL
        simulacaoService.processarSimulacao(request);

        System.out.println(">>> [CONSUMER] Proposta do CPF " + request.cpfCliente() + " processada e persistida com sucesso!");
    }
}
