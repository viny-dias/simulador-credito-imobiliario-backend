package com.bradesco.creditoimobiliario.controller;

import com.bradesco.creditoimobiliario.dto.SimulacaoRequestDTO;
import com.bradesco.creditoimobiliario.dto.SimulacaoResponseDTO;
import com.bradesco.creditoimobiliario.service.SimulacaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/simulacoes")
public class SimulacaoController {

    private final SimulacaoService simulacaoService;

    // Injetando a Service de negócio dentro do Controller
    public SimulacaoController(SimulacaoService simulacaoService) {
        this.simulacaoService = simulacaoService;
    }

    @PostMapping
    public ResponseEntity<SimulacaoResponseDTO> simular(@RequestBody SimulacaoRequestDTO request) {
        // Chamando a Service real que calcula o crédito e salva no Postgres
        SimulacaoResponseDTO response = simulacaoService.processarSimulacao(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<java.util.List<com.bradesco.creditoimobiliario.model.Simulacao>> obterHistorico() {
        java.util.List<com.bradesco.creditoimobiliario.model.Simulacao> historico = simulacaoService.listarHistorico();
        return ResponseEntity.ok(historico);
    }

}
