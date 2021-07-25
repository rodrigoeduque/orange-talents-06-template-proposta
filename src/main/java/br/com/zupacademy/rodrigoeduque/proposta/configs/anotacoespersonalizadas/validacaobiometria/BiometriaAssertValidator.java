package br.com.zupacademy.rodrigoeduque.proposta.configs.anotacoespersonalizadas.validacaobiometria;

import br.com.zupacademy.rodrigoeduque.proposta.configs.validacoes.handler.errorpadrao.ApiErroException;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class BiometriaAssertValidator implements ConstraintValidator <BiometriaAssert,String> {

    @Override
    public void initialize(BiometriaAssert constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {

        String encoded = Base64.getEncoder().encodeToString(s.getBytes(StandardCharsets.UTF_8));
        byte[] decodeEmByte = Base64.getDecoder().decode(encoded);
        String decodeFinal = new String(decodeEmByte);


        if(s.isEmpty() || s == null){
            return false;
        }

        if (s.equals(decodeFinal)){
            return false;
        }

        throw new ApiErroException(HttpStatus.UNAUTHORIZED, "Biometria inválida");
    }
}
