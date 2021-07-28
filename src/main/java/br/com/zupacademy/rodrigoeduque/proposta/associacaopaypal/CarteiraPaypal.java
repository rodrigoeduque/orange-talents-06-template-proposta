package br.com.zupacademy.rodrigoeduque.proposta.associacaopaypal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CarteiraPaypal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cod;
    private String idCartao;
    private String email;
    private String resultado;
    private String id;

    @Deprecated
    public CarteiraPaypal() {
    }

    public CarteiraPaypal(String email, String idCartao, String resultado, String id) {
        this.idCartao = idCartao;
        this.email = email;
        this.resultado = resultado;
        this.id = id;
    }

    public String getIdCartao() {
        return idCartao;
    }

    public String getEmail() {
        return email;
    }

    public String getResultado() {
        return resultado;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "CarteiraPaypal{" +
                "cod=" + cod +
                ", idCartao='" + idCartao + '\'' +
                ", email='" + email + '\'' +
                ", resultado='" + resultado + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
