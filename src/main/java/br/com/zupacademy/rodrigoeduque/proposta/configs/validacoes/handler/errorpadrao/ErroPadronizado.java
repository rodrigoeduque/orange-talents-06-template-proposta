package br.com.zupacademy.rodrigoeduque.proposta.configs.validacoes.handler.errorpadrao;

import java.util.Collection;

public class ErroPadronizado {

    private Collection<String> mensagem;

    public ErroPadronizado( Collection<String> mensagem) {
        this.mensagem = mensagem;
    }

    public Collection<String> getMensagem() {
        return mensagem;
    }
}
