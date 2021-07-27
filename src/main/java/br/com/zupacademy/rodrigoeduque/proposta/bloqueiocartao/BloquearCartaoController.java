package br.com.zupacademy.rodrigoeduque.proposta.bloqueiocartao;

import br.com.zupacademy.rodrigoeduque.proposta.associarcartao.ClientCartoes;
import br.com.zupacademy.rodrigoeduque.proposta.associarcartao.NumeroDoCartaoRequest;
import br.com.zupacademy.rodrigoeduque.proposta.novaproposta.Proposta;
import br.com.zupacademy.rodrigoeduque.proposta.novaproposta.PropostaRepository;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.Map;
import java.util.Optional;

@RestController
public class BloquearCartaoController {

    Logger logger = LoggerFactory.getLogger(BloquearCartaoController.class);

    private PropostaRepository propostaRepository;
    private CartaoBloqueioRepository cartaoBloqueioRepository;
    private ClientCartoes clientCartoes;


    @Autowired
    public BloquearCartaoController(PropostaRepository propostaRepository, CartaoBloqueioRepository cartaoBloqueioRepository, ClientCartoes clientCartoes) {
        this.propostaRepository = propostaRepository;
        this.cartaoBloqueioRepository = cartaoBloqueioRepository;
        this.clientCartoes = clientCartoes;
    }


    @PostMapping("/bloqueio")
    @Transactional
    public ResponseEntity<?> bloquearCartao(@RequestParam String idCartao, @RequestHeader(value = "User-Agent") String userAgent, @RequestHeader(value = "Host") String host) {

        Optional<Proposta> cartao = propostaRepository.findBynumeroCartao(idCartao);
        boolean temBloqueio = cartaoBloqueioRepository.findByIdCartao(idCartao).isPresent();

        if (!cartao.isPresent() || cartao.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Numero Cartão inválido ou inexistente");
        } else if (temBloqueio) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Cartão já encontra-se bloqueado");
        }

        try {
            NumeroDoCartaoRequest retornaStatusBloqueio = clientCartoes.solicitaBloqueioCartao(idCartao, Map.of("sistemaResponsavel", "PropostaApplication"));
            CartaoBloqueio cartaoBloqueado = new CartaoBloqueio(idCartao, host, userAgent);
            cartaoBloqueioRepository.save(cartaoBloqueado);
            cartaoBloqueado.setStatus(StatusCartao.BLOQUEADO);
            return ResponseEntity.status(HttpStatus.CREATED).header("resultado").build();
        } catch (FeignException exception) {
            return ResponseEntity.status(exception.status()).body(exception.getMessage());
        }

    }
}
