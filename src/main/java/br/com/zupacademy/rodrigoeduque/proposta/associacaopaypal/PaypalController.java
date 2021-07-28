package br.com.zupacademy.rodrigoeduque.proposta.associacaopaypal;

import br.com.zupacademy.rodrigoeduque.proposta.novaproposta.Proposta;
import br.com.zupacademy.rodrigoeduque.proposta.novaproposta.PropostaRepository;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Map;
import java.util.Optional;

@RestController
public class PaypalController {

    Logger logger = LoggerFactory.getLogger(PaypalController.class);

    private ClientPayPal clientPayPal;
    private CarteiraPaypalRepository carteiraPaypalRepository;
    private PropostaRepository propostaRepository;

    @Autowired
    public PaypalController(ClientPayPal clientPayPal, CarteiraPaypalRepository carteiraPaypalRepository, PropostaRepository propostaRepository) {
        this.clientPayPal = clientPayPal;
        this.carteiraPaypalRepository = carteiraPaypalRepository;
        this.propostaRepository = propostaRepository;
    }

    @PostMapping("/carteiras/paypal/{idCartao}")
    public ResponseEntity<?> associarPaypal(@PathVariable(required = true) String idCartao, @Valid @RequestBody SolicitacaoInclusaoCarteiraRequest request, UriComponentsBuilder builder){

        Optional<Proposta> cartao = propostaRepository.findBynumeroCartao(idCartao);
        Optional<CarteiraPaypal> carteira = carteiraPaypalRepository.findByIdCartao(idCartao);
        /**
         * Tratando 404
         * **/

        if (!cartao.isPresent()){
            return ResponseEntity.notFound().build();
        }

        /**
         * Tratando 422
         * **/
        else if (carteira.isPresent()){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
        else {
            try {
                ResultadoCarteiraResponse resultadoCarteiraResponse = clientPayPal.associaCarteiraPaypal(idCartao, Map.of("email", request.getEmail(), "carteira", request.getCarteira()));
                CarteiraPaypal carteiraPaypal = request.toModel(idCartao, request.getEmail(), resultadoCarteiraResponse);
                carteiraPaypalRepository.save(carteiraPaypal);
                URI uri = builder.path("/carteiras/paypal/{idCartao}").buildAndExpand(carteiraPaypal.getId()).toUri();
                return ResponseEntity.created(uri).build();
            }
            catch (FeignException e){
                return ResponseEntity.status(e.status()).build();
            }
        }
    }
}
