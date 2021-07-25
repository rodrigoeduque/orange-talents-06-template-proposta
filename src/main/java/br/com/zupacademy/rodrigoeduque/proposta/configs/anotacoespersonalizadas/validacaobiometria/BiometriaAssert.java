package br.com.zupacademy.rodrigoeduque.proposta.configs.anotacoespersonalizadas.validacaobiometria;

import org.hibernate.validator.constraints.CompositionType;
import org.hibernate.validator.constraints.ConstraintComposition;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotBlank;
import java.lang.annotation.*;

@NotBlank
@ConstraintComposition(CompositionType.OR) // specifies OR as boolean operator instead of AND
@Constraint(validatedBy = {BiometriaAssertValidator.class})
@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface BiometriaAssert {

    String message() default "Biometria invalida";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
