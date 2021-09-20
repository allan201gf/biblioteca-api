package br.com.allangf.bibliotecaapi.service;

import br.com.allangf.bibliotecaapi.domain.entity.NomeTest;
import br.com.allangf.bibliotecaapi.domain.repository.NomeTestRepository;
import com.sun.tools.jconsole.JConsoleContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
@Slf4j
@Service
public class NomeTestService {

    private NomeTestRepository nomeTestRepository;

    public NomeTestService(NomeTestRepository nomeTestRepository) {
        this.nomeTestRepository = nomeTestRepository;
    }

    @Autowired
    private WebClient webClientBooks;

    public NomeTest obterNome(String codigo) {

        //TODO
        // Criar tratamento de erro
//    try {
//
//    } catch(WebClientResponseException e) {
//        log.error("ERROR ...{}", e.getMessage());
//    }
        NomeTest block = this.webClientBooks
                .method(HttpMethod.GET)
                .uri("/works/{codigo}.json", codigo)
                .retrieve()
                .bodyToMono(NomeTest.class)
                .block();

        assert block != null;
        nomeTestRepository.save(block);

        return block;

    }

}
