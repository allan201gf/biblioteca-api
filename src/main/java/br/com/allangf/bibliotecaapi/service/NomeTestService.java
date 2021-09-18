package br.com.allangf.bibliotecaapi.service;

import br.com.allangf.bibliotecaapi.domain.entity.NomeTest;
import br.com.allangf.bibliotecaapi.domain.repository.NomeTestRepository;
import com.sun.tools.jconsole.JConsoleContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class NomeTestService {

    private NomeTestRepository nomeTestRepository;

    public NomeTestService(NomeTestRepository nomeTestRepository) {
        this.nomeTestRepository = nomeTestRepository;
    }

    @Autowired
    private WebClient webClientBooks;

    public NomeTest obterNome(String codigo) {

         Mono<NomeTest> nome = this.webClientBooks
                .method(HttpMethod.GET)
                .uri("/works/{codigo}.json", codigo)
                .retrieve()
                .bodyToMono(NomeTest.class);

        Mono<NomeTest> nome2 = this.webClientBooks
                .method(HttpMethod.GET)
                .uri("/works/{codigo}.json", codigo)
                .retrieve()
                .bodyToMono(NomeTest.class);

//         nome.block();

         NomeTest nomeFinal = Mono.zip(nome, nome2).map(tuple -> {
             return tuple.getT1();
         }).block();

//        nomeTestRepository.save(nome);

        return nomeFinal;

    }

}
