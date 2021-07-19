package br.com.zupacademy.rodrigoeduque.proposta.configs.validacoes.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class PropostaExceptionHandler {

    private MessageSource messageSource;

    @Autowired
    public PropostaExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErroFormularioRequest> handler(MethodArgumentNotValidException exception){
        List<ErroFormularioRequest> request = new ArrayList<>();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        fieldErrors.forEach(e-> {
            String message = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            ErroFormularioRequest erro = new ErroFormularioRequest(e.getField(), message);
            request.add(erro);
        });

        return request;
    }
}
