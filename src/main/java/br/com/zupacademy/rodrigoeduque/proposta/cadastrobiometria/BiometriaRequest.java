package br.com.zupacademy.rodrigoeduque.proposta.cadastrobiometria;

import br.com.zupacademy.rodrigoeduque.proposta.configs.anotacoespersonalizadas.validacaobiometria.BiometriaAssert;

import javax.validation.constraints.NotBlank;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class BiometriaRequest {

    @NotBlank

    @BiometriaAssert
    private String biometria;



    public BiometriaRequest() {
    }

    public BiometriaRequest(String biometria) {
        this.biometria = biometria;
    }

    public String getBiometria() {
        return biometria;
    }

    public String encodaBiometria(){
        String biometriaEncoder = Base64.getDecoder().toString();
        return biometriaEncoder;
    }


    @Override
    public String toString() {
        return "BiometriaRequest{" +
                "biometria=" + this.encodaBiometria() +
                '}';
    }

    public Biometria toModel() {

     return new Biometria(biometria);
    }
}
