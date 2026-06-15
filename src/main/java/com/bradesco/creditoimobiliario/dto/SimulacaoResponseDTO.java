package com.bradesco.creditoimobiliario.dto;

import java.math.BigDecimal;

public record SimulacaoResponseDTO(
        Long id,
        String statusResultado,
        BigDecimal valorParcela
) {}
