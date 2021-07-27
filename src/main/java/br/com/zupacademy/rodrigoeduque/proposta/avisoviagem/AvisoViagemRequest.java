package br.com.zupacademy.rodrigoeduque.proposta.avisoviagem;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

public class AvisoViagemRequest {

    @NotBlank
    private String destino;
    @Future
    private LocalDate dataFinal;


    public AvisoViagemRequest(String destino, LocalDate dataFinal) {
        this.destino = destino;
        this.dataFinal = dataFinal;
    }

    public AvisoViagem toModel(String idCartao, String hostName, String userAgent) {

        return new AvisoViagem(destino, dataFinal, idCartao, hostName, userAgent);
    }
}


