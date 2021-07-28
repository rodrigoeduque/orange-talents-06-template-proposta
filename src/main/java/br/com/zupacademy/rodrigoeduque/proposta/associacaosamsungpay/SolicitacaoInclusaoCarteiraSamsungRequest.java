package br.com.zupacademy.rodrigoeduque.proposta.associacaosamsungpay;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class SolicitacaoInclusaoCarteiraSamsungRequest {

    @NotBlank
    @Email
    private String email;
    private String carteira;

    public SolicitacaoInclusaoCarteiraSamsungRequest(String email, String carteira) {
        this.email = email;
        this.carteira = carteira;
    }

    public String getEmail() {
        return email;
    }

    public String getCarteira() {
        return carteira;
    }

    public CarteiraSamsungPay toModel(String idCartao, String email, ResultadoCarteiraSamsungResponse resultadoCarteiraSamsungResponse) {
        return new CarteiraSamsungPay(idCartao, email, resultadoCarteiraSamsungResponse.getId(), resultadoCarteiraSamsungResponse.getResultado());
    }
}
