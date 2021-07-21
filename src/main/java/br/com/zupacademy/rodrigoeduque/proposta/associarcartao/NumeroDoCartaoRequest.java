package br.com.zupacademy.rodrigoeduque.proposta.associarcartao;

public class NumeroDoCartaoRequest {

    private String id;

    @Deprecated
    public NumeroDoCartaoRequest() {
    }

    public NumeroDoCartaoRequest(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "NumeroDoCartaoRequest{" +
                "id='" + id + '\'' +
                '}';
    }
}
