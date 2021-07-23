package br.com.zupacademy.rodrigoeduque.proposta.associarcartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "solicitaCartao", url = "${cartao.url}")
public interface ClientCartoes {

    @RequestMapping(method = RequestMethod.GET, value = "cartoes" )
    NumeroDoCartaoRequest solicitarNumeroCartao(@RequestParam Long idProposta);
}
