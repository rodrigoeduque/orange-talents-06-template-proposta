package br.com.zupacademy.rodrigoeduque.proposta.analisedeproposta;

import br.com.zupacademy.rodrigoeduque.proposta.novaproposta.Proposta;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AnaliseDePropostaRequest {

    @NotBlank
    private String documento;
    @NotBlank
    private String nome;
    @NotNull
    private Long idProposta;


    public AnaliseDePropostaRequest(@Valid Proposta proposta) {
        this.documento = proposta.getDocumento();
        this.nome = proposta.getNome();
        this.idProposta = proposta.getId();
    }

    @Deprecated
    public AnaliseDePropostaRequest() {
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public Long getIdProposta() {
        return idProposta;
    }

}
