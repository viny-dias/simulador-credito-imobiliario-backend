package com.bradesco.creditoimobiliario.dto;

import java.math.BigDecimal;

public record SimulacaoRequestDTO(
        String cpfCliente,
        BigDecimal rendaMensal,
        Integer idadeCliente,
        BigDecimal valorImovel,
        BigDecimal valorFinanciado,
        Integer prazoMeses
) {}
