package com.bradesco.creditoimobiliario.controller;

import com.bradesco.creditoimobiliario.dto.SimulacaoRequestDTO;
import com.bradesco.creditoimobiliario.dto.SimulacaoResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/simulacoes")
public class SimulacaoController {

    @PostMapping
    public ResponseEntity<SimulacaoResponseDTO> simular(@RequestBody SimulacaoRequestDTO request) {
        // MOCK: Criando uma resposta falsa temporária só para a API compilar.
        // Substituiremos isso pela chamada da Service no próximo passo!
        SimulacaoResponseDTO mockResponse = new SimulacaoResponseDTO(
                1L,
                "APROVADO_MOCK",
                BigDecimal.valueOf(1500.00)
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(mockResponse);
    }
}
