package br.com.zupacademy.rodrigoeduque.proposta.acompanharproposta;

import br.com.zupacademy.rodrigoeduque.proposta.novaproposta.Proposta;

public class ConsultaPropostaResponse {

    private Long id;
    private String status;

    public ConsultaPropostaResponse(Proposta proposta) {
        this.id = proposta.getId();
        this.status = proposta.getStatusProposta().toString();
    }

    public Long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "ConsultaPropostaResponse{" +
                "id='" + id + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
