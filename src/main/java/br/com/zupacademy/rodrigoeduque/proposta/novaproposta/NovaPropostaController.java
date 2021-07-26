package br.com.zupacademy.rodrigoeduque.proposta.novaproposta;

import br.com.zupacademy.rodrigoeduque.proposta.analisedeproposta.AnaliseDePropostaRequest;
import br.com.zupacademy.rodrigoeduque.proposta.analisedeproposta.ClientAnaliseResource;
import br.com.zupacademy.rodrigoeduque.proposta.analisedeproposta.RetornoAnaliseRequest;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.security.RolesAllowed;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
public class NovaPropostaController {

    private PropostaRepository propostaRepository;
    private ClientAnaliseResource clientAnaliseResource;

    @Autowired
    public NovaPropostaController(PropostaRepository propostaRepository, ClientAnaliseResource clientAnaliseResource) {
        this.propostaRepository = propostaRepository;
        this.clientAnaliseResource = clientAnaliseResource;
    }

    @PostMapping("/propostas")
    @Transactional
    public ResponseEntity<?> criarNovaProposta(@RequestBody @Valid NovaPropostaRequest request, UriComponentsBuilder componentsBuilder) {

        Proposta proposta = request.toModel();
        Proposta novaProposta = propostaRepository.save(proposta);

        try {
            RetornoAnaliseRequest analise = clientAnaliseResource.enviarParaAnalise(new AnaliseDePropostaRequest(novaProposta));
            novaProposta.setStatusProposta(StatusProposta.ELEGIVEL);
        } catch (FeignException e) {
            novaProposta.setStatusProposta(StatusProposta.NAO_ELEGIVEL);
            return ResponseEntity.status(e.status()).body("Cliente Não Elegivel");
        }
        URI urlEnderecoNovaProposta = componentsBuilder.path("/propostas/{id}").build(novaProposta.getId());
        return ResponseEntity.created(urlEnderecoNovaProposta).build();
    }

}
