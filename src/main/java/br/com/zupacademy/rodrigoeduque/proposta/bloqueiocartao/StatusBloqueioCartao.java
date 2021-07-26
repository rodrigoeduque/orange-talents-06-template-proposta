package br.com.zupacademy.rodrigoeduque.proposta.bloqueiocartao;

import br.com.zupacademy.rodrigoeduque.proposta.configs.validacoes.handler.errorpadrao.ApiErroException;
import org.springframework.http.HttpStatus;

public enum StatusBloqueioCartao {
        BLOQUEADO, FALHA;

        public StatusCartao verificaStatus() {
            if (this == FALHA) {
                return StatusCartao.LIBERADO;
            } else if (this == BLOQUEADO) {
                return StatusCartao.BLOQUEADO;
            }

            throw new ApiErroException(HttpStatus.BAD_REQUEST, "ALgo Errado ocorreu");

    }
}
