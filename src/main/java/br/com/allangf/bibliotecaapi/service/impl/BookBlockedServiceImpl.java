package br.com.allangf.bibliotecaapi.service.impl;

import br.com.allangf.bibliotecaapi.domain.entity.BookBlocked;
import br.com.allangf.bibliotecaapi.domain.exception.RuleOfException;
import br.com.allangf.bibliotecaapi.domain.repository.BookBlockedRepository;
import br.com.allangf.bibliotecaapi.rest.config.WebClientMetods;
import br.com.allangf.bibliotecaapi.rest.dto.BookBlockedDTO;
import br.com.allangf.bibliotecaapi.service.BookBlockedService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookBlockedServiceImpl implements BookBlockedService {

    private final BookBlockedRepository bookBlockedRepository;

    @Autowired
    WebClientMetods webClientMetods;

    @Override
    public BookBlocked lockBook(BookBlockedDTO bookBlockedDTO) {

        String openLibraryIdBook = webClientMetods.getCodeOfBook(bookBlockedDTO.getNameOfBook());
        String nameOfBook = webClientMetods.getNameOfBook(openLibraryIdBook);

        long jaEmprestado = bookBlockedRepository.findAll()
                .stream()
                .filter(b -> Objects.equals(b.getNameOfBook(), nameOfBook))
                .count();

        if (jaEmprestado == 0) {

        BookBlocked lockBook = new BookBlocked();
        lockBook.setNameOfBook(nameOfBook);
        lockBook.setOpenLibraryIdBook(openLibraryIdBook);
        lockBook.setBookBlocked(true);
        bookBlockedRepository.save(lockBook);
        return lockBook;

        } else {

            return bookBlockedRepository.findByNameOfBook(nameOfBook).map(book -> {
                book.setBookBlocked(true);
                bookBlockedRepository.save(book);
                return book;

            }).orElseThrow(() -> {
                throw new RuleOfException("Erro ao localizar o livro");
            });
        }
    }

    @Override
    public BookBlocked unlockBook(BookBlockedDTO bookBlockedDTO) {
        return bookBlockedRepository.findByNameOfBook(bookBlockedDTO.getNameOfBook())
                .map(bookBlocked -> {
                    bookBlocked.setBookBlocked(false);
                    bookBlockedRepository.save(bookBlocked);
                    return bookBlocked;
                }).orElseThrow( () -> new RuleOfException("O livro informado nunca foi travado"));
    }

    @Override
    public List<BookBlocked> allBookBlocked() {
        return bookBlockedRepository.findAll()
                .stream().filter(book -> book.getBookBlocked().equals(true))
                .collect(Collectors.toList());
    }

}
