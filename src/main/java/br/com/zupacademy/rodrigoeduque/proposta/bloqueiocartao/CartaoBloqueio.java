package br.com.zupacademy.rodrigoeduque.proposta.bloqueiocartao;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class CartaoBloqueio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String idCartao;

    @NotNull
    private LocalDateTime instanteBloqueio;

    @NotBlank
    private String IpClientBloqueio;

    @NotBlank
    private String userAgentBloqueio;

    @NotNull
    @Enumerated(EnumType.STRING)
    private StatusCartao status = StatusCartao.LIBERADO;

    @Deprecated
    public CartaoBloqueio() {
    }

    public CartaoBloqueio(String idCartao, String ipClientBloqueio, String userAgentBloqueio) {
        this.idCartao = idCartao;
        this.instanteBloqueio = LocalDateTime.now();
        this.IpClientBloqueio = ipClientBloqueio;
        this.userAgentBloqueio = userAgentBloqueio;
    }

    public Long getId() {
        return id;
    }

    public void setStatus(StatusCartao status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CartaoBloqueio{" +
                "id=" + id +
                ", idCartao='" + idCartao + '\'' +
                ", instanteBloqueio=" + instanteBloqueio +
                ", IpClientBloqueio='" + IpClientBloqueio + '\'' +
                ", userAgentBloqueio='" + userAgentBloqueio + '\'' +
                ", status=" + status +
                '}';
    }
}
