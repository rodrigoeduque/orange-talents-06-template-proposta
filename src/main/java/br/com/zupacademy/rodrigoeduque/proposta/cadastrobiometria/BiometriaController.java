package br.com.zupacademy.rodrigoeduque.proposta.cadastrobiometria;

import br.com.zupacademy.rodrigoeduque.proposta.novaproposta.Proposta;
import br.com.zupacademy.rodrigoeduque.proposta.novaproposta.PropostaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class BiometriaController {

    Logger logger = LoggerFactory.getLogger(BiometriaController.class);

    private PropostaRepository propostaRepository;


    @Autowired
    public BiometriaController(PropostaRepository propostaRepository) {
        this.propostaRepository = propostaRepository;
    }

    @PostMapping("/biometrias")
    public ResponseEntity<?> criarBiometria(@RequestBody @Valid BiometriaRequest request, @RequestParam String numeroCartao){

        Optional<Proposta> retornaNumeroCartao = propostaRepository.findBynumeroCartao(numeroCartao);

        if (retornaNumeroCartao.isPresent()) {
            Biometria novaBiometria = request.toModel();

            Proposta proposta = retornaNumeroCartao.get();
            proposta.adicionaNovaBiometria(novaBiometria);
            novaBiometria.setCartaoDono(retornaNumeroCartao.get());
            propostaRepository.save(proposta);

            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }
}
