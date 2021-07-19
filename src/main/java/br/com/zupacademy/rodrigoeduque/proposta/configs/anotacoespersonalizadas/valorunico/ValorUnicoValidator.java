package br.com.zupacademy.rodrigoeduque.proposta.configs.anotacoespersonalizadas.valorunico;

import br.com.zupacademy.rodrigoeduque.proposta.configs.validacoes.handler.errorpadrao.ApiErroException;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ValorUnicoValidator implements ConstraintValidator<ValorUnico, Object> {

    private String atributo;
    private Class<?> classe;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void initialize(ValorUnico valorUnico) {
        atributo = valorUnico.atributo();
        classe = valorUnico.classe();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        Query query = entityManager.createQuery("select 1 from " + classe.getName() + " where " + atributo + " =:value");
        query.setParameter("value", o);
        List<?> list = query.getResultList();

        if (list.size() > 0 ){
            throw new ApiErroException(HttpStatus.UNPROCESSABLE_ENTITY,"O solicitante com este documento já requisitou uma proposta");
        }
        return true;
    }
}
