package br.com.zupacademy.rodrigoeduque.proposta.novaproposta;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.Set;

public interface PropostaRepository extends JpaRepository<Proposta, Long> {
    Optional<Proposta> findBynumeroCartao(String numeroCartao);
}
