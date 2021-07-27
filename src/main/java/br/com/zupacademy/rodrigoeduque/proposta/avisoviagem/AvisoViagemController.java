package br.com.zupacademy.rodrigoeduque.proposta.avisoviagem;

import br.com.zupacademy.rodrigoeduque.proposta.associarcartao.ClientCartoes;
import br.com.zupacademy.rodrigoeduque.proposta.novaproposta.PropostaRepository;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Map;

@RestController
public class AvisoViagemController {
    Logger logger = LoggerFactory.getLogger(AvisoViagemController.class);

    private PropostaRepository propostaRepository;
    private AvisoViagemRepository avisoViagemRepository;
    private ClientCartoes clientCartoes;

    @Autowired
    public AvisoViagemController(PropostaRepository propostaRepository, AvisoViagemRepository avisoViagemRepository, ClientCartoes clientCartoes) {
        this.propostaRepository = propostaRepository;
        this.avisoViagemRepository = avisoViagemRepository;
        this.clientCartoes = clientCartoes;
    }

    @PostMapping("/viagens/{idCartao}")
    @Transactional
    public ResponseEntity<?> novoAvisoViagem(@PathVariable(required = true) String idCartao,
                                             @Valid @RequestBody AvisoViagemRequest request,
                                             @RequestHeader(value = "Host") String hostName,
                                             @RequestHeader(value = "User-Agent") String userAgent) {

        boolean cartaoExiste = propostaRepository.findBynumeroCartao(idCartao).isPresent();

            if (cartaoExiste) {
                try {
                    AvisoViagemSolicitacaoResponse avisoViagemSolicitacaoResponse = clientCartoes.informaAvisoViagem(idCartao, Map.of("destino", request.getDestino(), "validoAte", request.getDataFinal()));
                }
                catch (FeignException e){
                    return ResponseEntity.status(e.status()).body(e.getMessage());
                }
                AvisoViagem avisoViagem = request.toModel(idCartao, hostName, userAgent);
                avisoViagemRepository.save(avisoViagem);
                avisoViagem.atualizaStatus();
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.notFound().build();
    }

}
