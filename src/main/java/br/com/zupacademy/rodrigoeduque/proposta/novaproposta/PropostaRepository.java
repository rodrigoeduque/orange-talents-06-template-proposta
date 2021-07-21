package br.com.zupacademy.rodrigoeduque.proposta.novaproposta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface PropostaRepository extends JpaRepository<Proposta, Long> {
}
