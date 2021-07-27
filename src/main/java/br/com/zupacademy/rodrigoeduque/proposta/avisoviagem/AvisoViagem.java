package br.com.zupacademy.rodrigoeduque.proposta.avisoviagem;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class AvisoViagem {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String idCartao;
    @NotNull
    private String destino;
    @NotNull
    @Future
    private LocalDate dataFinal;
    private String hostName;
    private String userAgent;
    @Enumerated(EnumType.STRING)
    private StatusResultadoAvisoViagem status;

    public AvisoViagem(String destino,
                       LocalDate dataFinal,
                       String idCartao,
                       String hostName,
                       String userAgent) {

        this.destino = destino;
        this.dataFinal = dataFinal;
        this.idCartao = idCartao;
        this.hostName = hostName;
        this.userAgent = userAgent;
    }

    public void atualizaStatus() {
        this.status = StatusResultadoAvisoViagem.CRIADO;
    }
}
