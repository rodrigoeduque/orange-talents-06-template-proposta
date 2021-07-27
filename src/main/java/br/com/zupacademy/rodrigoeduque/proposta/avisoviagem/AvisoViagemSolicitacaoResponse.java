package br.com.zupacademy.rodrigoeduque.proposta.avisoviagem;

public class AvisoViagemSolicitacaoResponse {

    private StatusResultadoAvisoViagem status;

    @Deprecated
    public AvisoViagemSolicitacaoResponse() {
    }

    public AvisoViagemSolicitacaoResponse(StatusResultadoAvisoViagem status) {
        this.status = status;
    }

    public StatusResultadoAvisoViagem getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "AvisoViagemSolicitacaoResponse{" +
                "status=" + status +
                '}';
    }
}
