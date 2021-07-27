package br.com.zupacademy.rodrigoeduque.proposta.avisoviagem;

import br.com.zupacademy.rodrigoeduque.proposta.novaproposta.PropostaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class AvisoViagemController {
    Logger logger = LoggerFactory.getLogger(AvisoViagemController.class);

    private PropostaRepository propostaRepository;
    private AvisoViagemRepository avisoViagemRepository;

    @Autowired
    public AvisoViagemController(PropostaRepository propostaRepository, AvisoViagemRepository avisoViagemRepository) {
        this.propostaRepository = propostaRepository;
        this.avisoViagemRepository = avisoViagemRepository;
    }

    @PostMapping("/viagens/{idCartao}")
    public ResponseEntity<?> novoAvisoViagem(@PathVariable(required = true) String idCartao,
                                             @Valid @RequestBody AvisoViagemRequest request,
                                             @RequestHeader(value = "Host") String hostName,
                                             @RequestHeader(value = "User-Agent") String userAgent) {

        boolean cartaoExiste = propostaRepository.findBynumeroCartao(idCartao).isPresent();

        if (cartaoExiste) {
            AvisoViagem avisoViagem = request.toModel(idCartao, hostName, userAgent);
            avisoViagemRepository.save(avisoViagem);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

}
