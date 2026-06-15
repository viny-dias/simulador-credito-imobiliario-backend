package com.bradesco.creditoimobiliario.repository;

import com.bradesco.creditoimobiliario.model.Simulacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SimulacaoRepository extends JpaRepository<Simulacao, Long> {
}
