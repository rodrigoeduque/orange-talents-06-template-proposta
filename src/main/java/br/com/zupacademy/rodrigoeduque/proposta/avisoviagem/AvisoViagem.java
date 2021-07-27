package br.com.zupacademy.rodrigoeduque.proposta.avisoviagem;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class AvisoViagem {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private final String idCartao;
    @NotNull
    private String destino;
    @NotNull @Future
    private final LocalDate dataFinal;
    private final String hostName;
    private final String userAgent;

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
}
