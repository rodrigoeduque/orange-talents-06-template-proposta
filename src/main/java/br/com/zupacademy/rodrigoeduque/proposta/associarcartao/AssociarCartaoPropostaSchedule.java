package br.com.zupacademy.rodrigoeduque.proposta.associarcartao;

import br.com.zupacademy.rodrigoeduque.proposta.novaproposta.Proposta;
import br.com.zupacademy.rodrigoeduque.proposta.novaproposta.PropostaRepository;
import br.com.zupacademy.rodrigoeduque.proposta.novaproposta.StatusProposta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AssociarCartaoPropostaSchedule {

    private static Logger logger = LoggerFactory.getLogger(AssociarCartaoPropostaSchedule.class);

    private PropostaRepository propostaRepository;
    private ClientCartoes clientCartoes;

    @Autowired
    public AssociarCartaoPropostaSchedule(PropostaRepository propostaRepository, ClientCartoes clientCartoes) {
        this.propostaRepository = propostaRepository;
        this.clientCartoes = clientCartoes;
    }

    @Transactional
    @Scheduled(fixedDelayString = "${periodo.execucao-agendamento}", initialDelayString = "${periodo.delay-primeira-execucao}")
    public void buscarPropostasNaoElegiveisEAssociarNumeroCartao() {
        /**
         * Busco todas as propostas, filtro apenas as elegiveis para assim poder criar um cartão para cada uma
         */
        List<Proposta> todasPropostas = propostaRepository.findAll();
        List<Proposta> propostasElegiveis = todasPropostas.stream().filter(p -> p.getStatusProposta() == StatusProposta.ELEGIVEL).collect(Collectors.toList());
        logger.info("------------------AGENDAMENTO > BUSCANDO PROPOSTAS ELEGIVEIS E ASSOCIANDO CARTAO ------------------");

        for (Proposta proposta : propostasElegiveis) {
            NumeroDoCartaoRequest cartaoRequest = clientCartoes.solicitarNumeroCartao(proposta.getId());

            if ((cartaoRequest != null) && (proposta.getNumeroCartao() == null)) {
                proposta.atualizaCartaoRecebido(cartaoRequest);
                propostaRepository.save(proposta);
            }
        }
    }
}
