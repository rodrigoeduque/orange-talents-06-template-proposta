package br.com.zupacademy.rodrigoeduque.proposta.configs.validacoes.handler;

public class ErroFormularioRequest {
    private String campoErro;
    private String mensagemErro;

    public ErroFormularioRequest(String campoErro, String mensagemErro) {
        this.campoErro = campoErro;
        this.mensagemErro = mensagemErro;
    }

    public String getCampoErro() {
        return campoErro;
    }

    public String getMensagemErro() {
        return mensagemErro;
    }
}
