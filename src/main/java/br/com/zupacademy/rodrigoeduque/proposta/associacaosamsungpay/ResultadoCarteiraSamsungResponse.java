package br.com.zupacademy.rodrigoeduque.proposta.associacaosamsungpay;

public class ResultadoCarteiraSamsungResponse {

    private String resultado;
    private String id;

    public ResultadoCarteiraSamsungResponse(String resultado, String id) {
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
