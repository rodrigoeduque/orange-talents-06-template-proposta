package br.com.zupacademy.rodrigoeduque.proposta.associarcartao;

import br.com.zupacademy.rodrigoeduque.proposta.avisoviagem.AvisoViagemSolicitacaoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@FeignClient(name = "solicitacoesCartao", url = "${cartao.url}")
public interface ClientCartoes {

    @RequestMapping(method = RequestMethod.GET, value = "/api/cartoes")
    NumeroDoCartaoRequest solicitarNumeroCartao(@PathVariable Long idProposta);

    @RequestMapping(method = RequestMethod.POST, value = "/api/cartoes/{idCartao}/bloqueios")
    NumeroDoCartaoRequest solicitaBloqueioCartao(@PathVariable String idCartao, @RequestBody Map<String, ?> body);

    @RequestMapping(method = RequestMethod.POST, value = "/api/cartoes/{idCartao}/avisos")
    AvisoViagemSolicitacaoResponse informaAvisoViagem(@PathVariable String idCartao, @RequestBody Map<String, ?> body);
}
