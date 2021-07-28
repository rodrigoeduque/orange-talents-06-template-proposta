package br.com.zupacademy.rodrigoeduque.proposta.associacaosamsungpay;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "AssociarCartaoSamsungPay", url = "${cartao.url}")
public interface ClientSamsungPay {


    @PostMapping("/api/cartoes/{idCartao}/carteiras")
    ResultadoCarteiraSamsungResponse associaCarteiraSamsungPay(@PathVariable String idCartao, @RequestBody Map<String, ?> body);
}

