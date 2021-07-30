package br.com.zupacademy.rodrigoeduque.proposta.novaproposta;

import br.com.zupacademy.rodrigoeduque.proposta.analisedeproposta.RetornoAnaliseRequest;
import br.com.zupacademy.rodrigoeduque.proposta.associarcartao.NumeroDoCartaoRequest;
import br.com.zupacademy.rodrigoeduque.proposta.cadastrobiometria.Biometria;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Proposta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(unique = true)
    private String documento;
    @NotNull
    private String email;
    @NotNull
    private String nome;
    @NotNull
    private String endereco;
    @Positive
    @NotNull
    private BigDecimal salario;

    @Enumerated(EnumType.STRING)
    private StatusProposta statusProposta;

    private String numeroCartao;

    @OneToMany(mappedBy = "cartaoDono" , cascade = CascadeType.MERGE)
    private Set<Biometria> biometrias = new HashSet<>();

    @Deprecated
    public Proposta() {
    }

    public Proposta(@NotBlank String documento,
                    @NotBlank @Email String email,
                    @NotBlank String nome,
                    @NotBlank String endereco,
                    @NotNull @Positive BigDecimal salario) {
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
        this.statusProposta = StatusProposta.EM_PROCESSAMENTO;
    }

    public Long getId() {
        return id;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public StatusProposta getStatusProposta() {
        return statusProposta;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setStatusProposta(StatusProposta statusProposta) {
        this.statusProposta = statusProposta;
    }

    public void atualizaStatusProposta(RetornoAnaliseRequest retorno) {
        this.statusProposta = retorno.getResultadoSolicitacao().verificaPropostaElegibilidade();
    }

    public void atualizaCartaoRecebido(NumeroDoCartaoRequest numero){
        this.numeroCartao = numero.getId();
    }

    public void adicionaNovaBiometria(Biometria biometria){
        this.biometrias.add(biometria);
    }

    @Override
    public String toString() {
        return "Proposta{" +
                "id=" + id +
                ", documento='" + documento + '\'' +
                ", nome='" + nome + '\'' +
                ", statusProposta=" + statusProposta +
                '}';
    }
}
