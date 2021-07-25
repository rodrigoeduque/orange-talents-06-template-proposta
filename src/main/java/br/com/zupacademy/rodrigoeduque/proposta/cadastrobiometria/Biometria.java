package br.com.zupacademy.rodrigoeduque.proposta.cadastrobiometria;

import br.com.zupacademy.rodrigoeduque.proposta.novaproposta.Proposta;
import org.bouncycastle.util.encoders.Base64Encoder;

import javax.persistence.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Base64;

@Entity
public class Biometria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String biometria;

    @ManyToOne
    private Proposta cartaoDono;

    private LocalDateTime dataCadastro;

    @Deprecated
    public Biometria() {
    }

    public Biometria(String biometria) {
        this.biometria = decodedString(biometria);
        this.dataCadastro = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getBiometria() {
        return biometria;
    }

    public Proposta getCartaoDono() {
        return cartaoDono;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setCartaoDono(Proposta cartaoDono) {
        this.cartaoDono = cartaoDono;
    }

    public String encodeBiometria(String valor){
        String encodedBiometria = Base64.getEncoder().encodeToString(valor.getBytes(StandardCharsets.UTF_8));
        return encodedBiometria;
    }

    public String decodedString(String valor){
        byte[] decodeBytesBiometria = Base64.getDecoder().decode(valor);
        String codedBiometria = new String(decodeBytesBiometria);

        return codedBiometria;
    }
}
