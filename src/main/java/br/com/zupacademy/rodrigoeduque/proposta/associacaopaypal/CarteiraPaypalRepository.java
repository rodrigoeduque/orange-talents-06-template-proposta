package br.com.zupacademy.rodrigoeduque.proposta.associacaopaypal;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarteiraPaypalRepository extends JpaRepository<CarteiraPaypal, Long> {
    Optional<CarteiraPaypal> findByIdCartao(String idCartao);
}
