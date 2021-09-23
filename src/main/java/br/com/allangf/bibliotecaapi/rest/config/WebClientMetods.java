package br.com.allangf.bibliotecaapi.rest.config;

import br.com.allangf.bibliotecaapi.domain.entity.BookStatistics;
import br.com.allangf.bibliotecaapi.domain.libraryapi.DocsKeyBook;
import br.com.allangf.bibliotecaapi.domain.libraryapi.NameBook;
import br.com.allangf.bibliotecaapi.domain.repository.BookStatisticsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class WebClientMetods {

    @Autowired
    private WebClient webClientBooks;

    private final BookStatisticsRepository bookStatisticsRepository;

    public String getNameOfBook (String codeOfBook) {

        NameBook nameBook = this.webClientBooks
                .method(HttpMethod.GET)
                .uri("/works/{codeOfBook}.json", codeOfBook)
                .retrieve()
                .bodyToMono(NameBook.class)
                .block();

        assert nameBook != null;
        return nameBook.getTitle();

    }

    public String getCodeOfBook (String nameOfBooks) {

        DocsKeyBook books = this.webClientBooks
                .method(HttpMethod.GET)
                .uri("/search.json?q={nomeOfBook}", nameOfBooks)
                .retrieve()
                .bodyToMono(DocsKeyBook.class)
                .block();

        assert books != null;
        String idBookNotFormatted = books.getDocs().get(1).toString();
        int endIdBook = idBookNotFormatted.indexOf(")");
        return idBookNotFormatted.substring(19, endIdBook);

    }


}
