package br.com.zupacademy.rodrigoeduque.proposta.novaproposta;

import br.com.zupacademy.rodrigoeduque.proposta.configs.anotacoespersonalizadas.cpfoucnpj.CpfOuCnpj;
import br.com.zupacademy.rodrigoeduque.proposta.configs.anotacoespersonalizadas.valorunico.ValorUnico;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class NovaPropostaRequest {

    @NotBlank
    @CpfOuCnpj
    @ValorUnico(classe = Proposta.class, atributo = "documento")
    private String documento;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String nome;

    @NotBlank
    private String endereco;

    @NotNull
    @Positive
    private BigDecimal salario;

    @Value("${password}")
    private String passwordCrypt;
    @Value("${key}")
    private String keyCrypt;


    public NovaPropostaRequest(String documento, String email, String nome, String endereco, BigDecimal salario) {
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }

    @Override
    public String toString() {
        return "NovaProposta{" +
                "documento='" + documento + '\'' +
                ", email='" + email + '\'' +
                ", nome='" + nome + '\'' +
                ", endereco='" + endereco + '\'' +
                ", salario=" + salario +
                '}';
    }

    public Proposta toModel(String passwordCrypt, String keyCrypt) {
        TextEncryptor textEncryptor = Encryptors.text(passwordCrypt, keyCrypt);
        String docCrypt = textEncryptor.encrypt(documento);
        return new Proposta(docCrypt,email,nome,endereco,salario);
    }
}
