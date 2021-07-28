package br.com.zupacademy.rodrigoeduque.proposta.associacaopaypal;

public class ResultadoCarteiraResponse {

    private String resultado;
    private String id;

    public ResultadoCarteiraResponse(String resultado, String id) {
        this.resultado = resultado;
        this.id = id;
    }

    public String getResultado() {
        return resultado;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "ResultadoCarteiraResponse{" +
                "resultado='" + resultado + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
