package br.com.zupacademy.rodrigoeduque.proposta.analisedeproposta;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class RetornoAnaliseRequest {
    private String documento;
    private String nome;
    private String idProposta;
    @Enumerated(EnumType.STRING)
    private StatusAnaliseProposta resultadoSolicitacao;

    public RetornoAnaliseRequest(String documento, String nome, String idProposta, StatusAnaliseProposta resultadoSolicitacao) {
        this.documento = documento;
        this.nome = nome;
        this.idProposta = idProposta;
        this.resultadoSolicitacao = resultadoSolicitacao;
    }

    public StatusAnaliseProposta getResultadoSolicitacao() {
        return resultadoSolicitacao;
    }

    @Override
    public String toString() {
        return "RetornoAnaliseRequest{" +
                "documento='" + documento + '\'' +
                ", nome='" + nome + '\'' +
                ", idProposta='" + idProposta + '\'' +
                ", resultadoSolicitacao=" + resultadoSolicitacao +
                '}';
    }
}
