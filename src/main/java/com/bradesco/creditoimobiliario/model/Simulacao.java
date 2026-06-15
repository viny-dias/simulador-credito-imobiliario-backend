package com.bradesco.creditoimobiliario.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "simulacoes")
@Data
public class Simulacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cpf_cliente", nullable = false)
    private String cpfCliente;

    @Column(name = "renda_mensal", nullable = false)
    private BigDecimal rendaMensal;

    @Column(name = "idade_cliente", nullable = false)
    private Integer idadeCliente;

    @Column(name = "valor_imovel", nullable = false)
    private BigDecimal valorImovel;

    @Column(name = "valor_financiado", nullable = false)
    private BigDecimal valorFinanciado;

    @Column(name = "prazo_meses", nullable = false)
    private Integer prazoMeses;

    @Column(name = "valor_parcela", nullable = false)
    private BigDecimal valorParcela;

    @Column(name = "status_resultado", nullable = false)
    private String statusResultado;

    @Column(name = "data_simulacao", nullable = false)
    private LocalDateTime dataSimulacao;

    @PrePersist
    protected void onCreate() {
        this.dataSimulacao = LocalDateTime.now();
    }
}
