package com.bradesco.creditoimobiliario.controller;

import com.bradesco.creditoimobiliario.dto.SimulacaoRequestDTO;
import com.bradesco.creditoimobiliario.model.Simulacao;
import com.bradesco.creditoimobiliario.producer.SimulacaoProducer;
import com.bradesco.creditoimobiliario.service.SimulacaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/simulacoes")
public class SimulacaoController {

    private final SimulacaoProducer simulacaoProducer;
    private final SimulacaoService simulacaoService;

    // Construtor atualizado injetando o Producer (para o POST) e a Service (para o GET)
    public SimulacaoController(SimulacaoProducer simulacaoProducer, SimulacaoService simulacaoService) {
        this.simulacaoProducer = simulacaoProducer;
        this.simulacaoService = simulacaoService;
    }

    // Rota POST: Envia a proposta assincronamente para o RabbitMQ
    @PostMapping
    public ResponseEntity<Map<String, String>> simular(@RequestBody SimulacaoRequestDTO request) {
        simulacaoProducer.enviarPropostaParaFila(request);

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(Map.of(
                        "mensagem", "Proposta recebida com sucesso! O processamento de credito esta sendo realizado em segundo plano.",
                        "status", "PROCESSANDO"
                ));
    }

    // Rota GET: Puxa o histórico do banco de dados (e passa pelo Cache do Redis!)
    @GetMapping
    public ResponseEntity<List<Simulacao>> obterHistorico() {
        List<Simulacao> historico = simulacaoService.listarHistorico();
        return ResponseEntity.ok(historico);
    }
}
