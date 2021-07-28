package br.com.zupacademy.rodrigoeduque.proposta.associacaosamsungpay;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CarteiraSamsungPay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String idCartao;
    private String email;
    private String idCarteira;
    private String resultado;

    @Deprecated
    public CarteiraSamsungPay() {
    }

    public CarteiraSamsungPay(String idCartao,
                              String email,
                              String id,
                              String resultado) {
        this.idCartao = idCartao;
        this.email = email;
        this.idCarteira = id;
        this.resultado = resultado;
    }

    public Long getId() {
        return id;
    }

    public String getIdCartao() {
        return idCartao;
    }

    public String getEmail() {
        return email;
    }

    public String getIdCarteira() {
        return idCarteira;
    }

    public String getResultado() {
        return resultado;
    }

}
