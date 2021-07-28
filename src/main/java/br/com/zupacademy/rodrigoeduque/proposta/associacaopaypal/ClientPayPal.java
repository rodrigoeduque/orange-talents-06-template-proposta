package br.com.zupacademy.rodrigoeduque.proposta.associacaopaypal;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "AssociarCartaoPayPal", url = "${cartao.url}")
public interface ClientPayPal {


    @PostMapping("/api/cartoes/{idCartao}/carteiras")
    ResultadoCarteiraResponse associaCarteiraPaypal(@PathVariable String idCartao, @RequestBody Map<String, ?> body);

}
