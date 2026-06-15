package com.bradesco.creditoimobiliario.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "simulacoes")
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

    // --- MÉTODOS GETTERS E SETTERS EXPLICITOS ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCpfCliente() { return cpfCliente; }
    public void setCpfCliente(String cpfCliente) { this.cpfCliente = cpfCliente; }

    public BigDecimal getRendaMensal() { return rendaMensal; }
    public void setRendaMensal(BigDecimal rendaMensal) { this.rendaMensal = rendaMensal; }

    public Integer getIdadeCliente() { return idadeCliente; }
    public void setIdadeCliente(Integer idadeCliente) { this.idadeCliente = idadeCliente; }

    public BigDecimal getValorImovel() { return valorImovel; }
    public void setValorImovel(BigDecimal valorImovel) { this.valorImovel = valorImovel; }

    public BigDecimal getValorFinanciado() { return valorFinanciado; }
    public void setValorFinanciado(BigDecimal valorFinanciado) { this.valorFinanciado = valorFinanciado; }

    public Integer getPrazoMeses() { return prazoMeses; }
    public void setPrazoMeses(Integer prazoMeses) { this.prazoMeses = prazoMeses; }

    public BigDecimal getValorParcela() { return valorParcela; }
    public void setValorParcela(BigDecimal valorParcela) { this.valorParcela = valorParcela; }

    public String getStatusResultado() { return statusResultado; }
    public void setStatusResultado(String statusResultado) { this.statusResultado = statusResultado; }

    public LocalDateTime getDataSimulacao() { return dataSimulacao; }
    public void setDataSimulacao(LocalDateTime dataSimulacao) { this.dataSimulacao = dataSimulacao; }
}
