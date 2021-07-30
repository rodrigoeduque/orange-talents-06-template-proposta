package br.com.zupacademy.rodrigoeduque.proposta.analisedeproposta;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.keygen.KeyGenerators;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class RetornoAnaliseRequest {

    private String documento;
    private String nome;
    private String idProposta;
    @Enumerated(EnumType.STRING)
    private StatusAnaliseProposta resultadoSolicitacao;

    @Value("${key}")
    private String keyCrypt;
    @Value("${password}")
    private String passwordCrypt;




    public RetornoAnaliseRequest(String documento, String nome, String idProposta, StatusAnaliseProposta resultadoSolicitacao) {
        this.documento = docDecrypt(documento);
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

    private String docDecrypt(String documento){
        TextEncryptor textEncryptor = Encryptors.text(passwordCrypt, keyCrypt);
        String documentoDecrypt = textEncryptor.decrypt(documento);
        return documentoDecrypt;
    }
}
