package br.com.zupacademy.rodrigoeduque.proposta.bloqueiocartao;

import br.com.zupacademy.rodrigoeduque.proposta.novaproposta.Proposta;
import br.com.zupacademy.rodrigoeduque.proposta.novaproposta.PropostaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class BloquearCartaoController {

    Logger logger = LoggerFactory.getLogger(BloquearCartaoController.class);

    private PropostaRepository propostaRepository;
    private CartaoBloqueioRepository cartaoBloqueioRepository;


    @Autowired
    public BloquearCartaoController(PropostaRepository propostaRepository, CartaoBloqueioRepository cartaoBloqueioRepository) {
        this.propostaRepository = propostaRepository;
        this.cartaoBloqueioRepository = cartaoBloqueioRepository;
    }


    @PostMapping("/bloqueio")
    public ResponseEntity<?> bloquearCartao(@RequestParam String idCartao, @RequestHeader(value = "User-Agent") String userAgent, @RequestHeader(value = "Host") String host) {

        Optional<Proposta> cartao = propostaRepository.findBynumeroCartao(idCartao);
        boolean temBloqueio = cartaoBloqueioRepository.findByIdCartao(idCartao).isPresent();

        if (!cartao.isPresent() || cartao.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Numero Cartão inválido ou inexistente");
        } else if (temBloqueio) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Cartão já encontra-se bloqueado");
        }

        CartaoBloqueio cartaoBloqueado = new CartaoBloqueio(idCartao, host, userAgent);

        cartaoBloqueioRepository.save(cartaoBloqueado);

        return ResponseEntity.ok(cartaoBloqueado.toString());
    }
}
