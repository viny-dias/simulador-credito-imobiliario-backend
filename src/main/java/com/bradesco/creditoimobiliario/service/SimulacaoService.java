package com.bradesco.creditoimobiliario.service;

import com.bradesco.creditoimobiliario.dto.SimulacaoRequestDTO;
import com.bradesco.creditoimobiliario.dto.SimulacaoResponseDTO;
import com.bradesco.creditoimobiliario.model.Simulacao;
import com.bradesco.creditoimobiliario.repository.SimulacaoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class SimulacaoService {

    private final SimulacaoRepository simulacaoRepository;

    public SimulacaoService(SimulacaoRepository simulacaoRepository) {
        this.simulacaoRepository = simulacaoRepository;
    }

    @org.springframework.cache.annotation.CacheEvict(value = "historicoSimulacoes", allEntries = true)
    public SimulacaoResponseDTO processarSimulacao(SimulacaoRequestDTO request) {
        // Passo 1: Calcular o valor bruto da parcela mensal
        BigDecimal prazoMeses = BigDecimal.valueOf(request.prazoMeses());
        BigDecimal valorParcela = request.valorFinanciado().divide(prazoMeses, 2, RoundingMode.HALF_UP);

        // Passo 2: Validar Regra 1 - Comprometimento de Renda (Máximo 30%)
        BigDecimal limiteComprometimento = request.rendaMensal().multiply(BigDecimal.valueOf(0.30));
        boolean rendaAprovada = valorParcela.compareTo(limiteComprometimento) <= 0;

        // Passo 3: Validar Regra 2 - Idade Limite (Idade + Prazo em anos <= 80)
        int prazoAnos = request.prazoMeses() / 12;
        int idadeFinalCliente = request.idadeCliente() + prazoAnos;
        boolean idadeAprovada = idadeFinalCliente <= 80;

        // Passo 4: Definir o Status Final
        String statusResultado = (rendaAprovada && idadeAprovada) ? "APROVADO" : "REPROVADO";

        // Passo 5: Montar o Model para salvar no PostgreSQL
        Simulacao simulacao = new Simulacao();
        simulacao.setCpfCliente(request.cpfCliente());
        simulacao.setRendaMensal(request.rendaMensal());
        simulacao.setIdadeCliente(request.idadeCliente());
        simulacao.setValorImovel(request.valorImovel());
        simulacao.setValorFinanciado(request.valorFinanciado());
        simulacao.setPrazoMeses(request.prazoMeses());
        simulacao.setValorParcela(valorParcela);
        simulacao.setStatusResultado(statusResultado);

        // Salvando no banco de dados e recuperando o objeto atualizado com o ID gerado
        Simulacao simulacaoSalva = simulacaoRepository.save(simulacao);

        // Passo 6: Retornar o DTO de resposta limpo para o Front-end
        return new SimulacaoResponseDTO(
                simulacaoSalva.getId(),
                simulacaoSalva.getStatusResultado(),
                simulacaoSalva.getValorParcela()
        );
    }

    // Novo método para buscar o histórico de simulações do banco
    @org.springframework.cache.annotation.Cacheable("historicoSimulacoes")
    public java.util.List<Simulacao> listarHistorico() {
        return simulacaoRepository.findAll();
    }

}
