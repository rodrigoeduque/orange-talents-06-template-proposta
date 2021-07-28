package br.com.zupacademy.rodrigoeduque.proposta.associacaopaypal;

import br.com.zupacademy.rodrigoeduque.proposta.configs.anotacoespersonalizadas.valorunico.ValorUnico;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class SolicitacaoInclusaoCarteiraRequest {

    @NotBlank
    @Email
    private String email;
    @ValorUnico(classe = CarteiraPaypal.class,atributo = "id_cartao",message = "Cartão já vinculado a uma carteira Paypal")
    private String carteira;

    public SolicitacaoInclusaoCarteiraRequest(String email, String carteira) {
        this.email = email;
        this.carteira = carteira;
    }

    public String getEmail() {
        return email;
    }

    public String getCarteira() {
        return carteira;
    }

    public CarteiraPaypal toModel(String idCartao, String email, ResultadoCarteiraResponse resultadoCarteiraResponse) {
        return new CarteiraPaypal(email,idCartao,resultadoCarteiraResponse.getResultado(),resultadoCarteiraResponse.getId());
    }
}
