package br.com.zupacademy.rodrigoeduque.proposta.associacaosamsungpay;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarteiraSamsungPayRepository extends JpaRepository<CarteiraSamsungPay, Long> {
    Optional<CarteiraSamsungPay> findByIdCartao(String idCartao);
}
