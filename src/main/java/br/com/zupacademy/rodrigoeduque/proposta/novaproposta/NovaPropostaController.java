package br.com.zupacademy.rodrigoeduque.proposta.novaproposta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
public class NovaPropostaController {

    private PropostaRepository propostaRepository;

    @Autowired
    public NovaPropostaController(PropostaRepository propostaRepository) {
        this.propostaRepository = propostaRepository;
    }


    @PostMapping("/propostas")
    public ResponseEntity<?> criarNovaProposta(@RequestBody @Valid NovaPropostaRequest request, UriComponentsBuilder componentsBuilder){
        Proposta novaProposta = request.toModel();
        propostaRepository.save(novaProposta);
        URI urlEnderecoNovaProposta = componentsBuilder.path("/propostas/{id}").build(novaProposta.getId());
        return ResponseEntity.created(urlEnderecoNovaProposta).build();
    }

}
