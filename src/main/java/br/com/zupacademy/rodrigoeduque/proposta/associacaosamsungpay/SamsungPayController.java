package br.com.zupacademy.rodrigoeduque.proposta.associacaosamsungpay;

import br.com.zupacademy.rodrigoeduque.proposta.novaproposta.Proposta;
import br.com.zupacademy.rodrigoeduque.proposta.novaproposta.PropostaRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Map;
import java.util.Optional;

@RestController
public class SamsungPayController {

    private ClientSamsungPay clientSamsungPay;
    private PropostaRepository propostaRepository;
    private CarteiraSamsungPayRepository carteiraSamsungPayRepository;

    @Autowired
    public SamsungPayController(ClientSamsungPay clientSamsungPay, PropostaRepository propostaRepository, CarteiraSamsungPayRepository carteiraSamsungPayRepository) {
        this.clientSamsungPay = clientSamsungPay;
        this.propostaRepository = propostaRepository;
        this.carteiraSamsungPayRepository = carteiraSamsungPayRepository;
    }

    @PostMapping("/carteiras/samsungpay/{idCartao}")
    public ResponseEntity<?> associarSamsungPay(@PathVariable(required = true) String idCartao, @RequestBody SolicitacaoInclusaoCarteiraSamsungRequest request, UriComponentsBuilder builder){

        Optional<Proposta> verificaCartaoProposta = propostaRepository.findBynumeroCartao(idCartao);
        Optional<CarteiraSamsungPay> verificaCarteiraSamsungPay = carteiraSamsungPayRepository.findByIdCartao(idCartao);

        if (!verificaCartaoProposta.isPresent()){
            return ResponseEntity.notFound().build();
        }
        else if (verificaCarteiraSamsungPay.isPresent()){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Este Cartão já esta vinculado a essa proposta");
        }
        else {
                try {
                    ResultadoCarteiraSamsungResponse resultadoCarteiraSamsungResponse = clientSamsungPay.associaCarteiraSamsungPay(idCartao, Map.of("email", request.getEmail(), "carteira", request.getCarteira()));
                    CarteiraSamsungPay carteiraSamsungPay = request.toModel(idCartao, request.getEmail(), resultadoCarteiraSamsungResponse);
                    carteiraSamsungPayRepository.save(carteiraSamsungPay);
                    URI uri = builder.path("http://localhost:8080/carteiras/samsungpay/{id}").buildAndExpand(carteiraSamsungPay.getIdCarteira()).toUri(); ///ALTERAR AQUI
                    return ResponseEntity.created(uri).build();
                }
                catch (FeignException e){
                    return ResponseEntity.status(e.status()).body("Associação não finalizada");
                }

        }
    }
}
