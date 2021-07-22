package br.com.zupacademy.rodrigoeduque.proposta.acompanharproposta;


import br.com.zupacademy.rodrigoeduque.proposta.novaproposta.Proposta;
import br.com.zupacademy.rodrigoeduque.proposta.novaproposta.PropostaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
public class AcompanharPropostaController {

    Logger logger = LoggerFactory.getLogger(AcompanharPropostaController.class);

    private PropostaRepository propostaRepository;

    @Autowired
    public AcompanharPropostaController(PropostaRepository propostaRepository) {
        this.propostaRepository = propostaRepository;
    }

    @GetMapping("/propostas/{idProposta}")
    public ResponseEntity<ConsultaPropostaResponse> acompanharProposta(@PathVariable (value = "idProposta",required = true) Long idProposta){
        logger.info("IdProposta ------->" + idProposta);
        Optional<Proposta> proposta = propostaRepository.findById(idProposta);

        if (proposta.isPresent()){
            return ResponseEntity.ok(new ConsultaPropostaResponse(proposta.get()));
        }

        return ResponseEntity.notFound().build();
    }

}
