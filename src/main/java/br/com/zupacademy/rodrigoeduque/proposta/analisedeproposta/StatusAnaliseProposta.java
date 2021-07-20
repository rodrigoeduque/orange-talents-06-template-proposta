package br.com.zupacademy.rodrigoeduque.proposta.analisedeproposta;

import br.com.zupacademy.rodrigoeduque.proposta.configs.validacoes.handler.errorpadrao.ApiErroException;
import br.com.zupacademy.rodrigoeduque.proposta.novaproposta.StatusProposta;
import org.springframework.http.HttpStatus;

public enum StatusAnaliseProposta {
    COM_RESTRICAO, SEM_RESTRICAO;

    public StatusProposta verificaPropostaElegibilidade() {
        if (this == COM_RESTRICAO) {
            return StatusProposta.NAO_ELEGIVEL;
        } else if (this == SEM_RESTRICAO) {
            return StatusProposta.ELEGIVEL;
        }

        throw new ApiErroException(HttpStatus.BAD_REQUEST, "ALgo Errado ocorreu");
    }
}
