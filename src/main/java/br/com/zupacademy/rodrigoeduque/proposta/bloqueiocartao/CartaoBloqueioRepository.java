package br.com.zupacademy.rodrigoeduque.proposta.bloqueiocartao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartaoBloqueioRepository extends JpaRepository<CartaoBloqueio, Long> {
    Optional<CartaoBloqueio> findByIdCartao(String idCartao);
}
