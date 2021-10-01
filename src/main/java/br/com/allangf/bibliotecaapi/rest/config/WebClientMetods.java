package br.com.allangf.bibliotecaapi.rest.config;

import br.com.allangf.bibliotecaapi.domain.exception.RuleOfException;
import br.com.allangf.bibliotecaapi.domain.libraryapi.DocsKeyBook;
import br.com.allangf.bibliotecaapi.domain.libraryapi.NameBook;
import br.com.allangf.bibliotecaapi.domain.repository.BookStatisticsRepository;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
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

    public String getNameOfBook(String codeOfBook) {
        try {
            NameBook nameBook = this.webClientBooks
                    .method(HttpMethod.GET)
                    .uri("/works/{codeOfBook}.json", codeOfBook)
                    .retrieve()
                    .bodyToMono(NameBook.class)
                    .block();

            assert nameBook != null;
            return nameBook.getTitle();
        } catch (IndexOutOfBoundsException ex) {
            throw new RuleOfException("Não foi possível encontrar o livro");
        }


    }

    public String getCodeOfBook(String nameOfBooks) {
        try {
            DocsKeyBook books = this.webClientBooks
                    .method(HttpMethod.GET)
                    .uri("/search.json?q={nomeOfBook}", nameOfBooks)
                    .retrieve()
                    .bodyToMono(DocsKeyBook.class)
                    .block();

            assert books != null;

            JSONObject booksJson = new JSONObject(books);

            JSONArray booksDocs = booksJson.getJSONArray("docs");
            String bookKeyObject = JSONObject.valueToString(booksDocs.get(0));

            return bookKeyObject.replace("{\"key\":\"/works/", "").replace("\"}", "");

            // Outra forma de fazer o método acima, mais curto, porem menos intendivel.
            /*String idBookNotFormatted = books.getDocs().get(0).toString();
            int endStringIndex = idBookNotFormatted.indexOf(")");
            //"key": "/works/OL82563W",
            return idBookNotFormatted.substring(19, endStringIndex);*/

        } catch (IndexOutOfBoundsException ex) {
            throw new RuleOfException("Não foi possível encontrar o livro");
        }


    }


}
