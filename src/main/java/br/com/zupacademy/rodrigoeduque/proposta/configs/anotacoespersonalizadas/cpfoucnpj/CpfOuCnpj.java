package br.com.zupacademy.rodrigoeduque.proposta.configs.anotacoespersonalizadas.cpfoucnpj;

import org.hibernate.validator.constraints.CompositionType;
import org.hibernate.validator.constraints.ConstraintComposition;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@ConstraintComposition(CompositionType.OR)
@ReportAsSingleViolation
@Constraint(validatedBy = {})
@CPF
@CNPJ
public @interface CpfOuCnpj {
    String message () default "Número Documento Inválido. Informar documento do tipo CNPJ/CPF válido";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
