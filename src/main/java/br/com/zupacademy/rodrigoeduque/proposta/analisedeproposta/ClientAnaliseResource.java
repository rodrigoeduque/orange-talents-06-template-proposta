package br.com.zupacademy.rodrigoeduque.proposta.analisedeproposta;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(url = "http://localhost:9999/api", name = "analiseDeCreditoCliente")
public interface ClientAnaliseResource {

    @RequestMapping(method = RequestMethod.POST, value = "/solicitacao", consumes = "application/json")
    RetornoAnaliseRequest enviarParaAnalise(@RequestBody AnaliseDePropostaRequest request);
}
