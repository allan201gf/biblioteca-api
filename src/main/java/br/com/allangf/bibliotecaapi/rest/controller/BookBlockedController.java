package br.com.allangf.bibliotecaapi.rest.controller;

import br.com.allangf.bibliotecaapi.domain.entity.BookBlocked;
import br.com.allangf.bibliotecaapi.domain.entity.BookStatistics;
import br.com.allangf.bibliotecaapi.rest.config.WebClientMetods;
import br.com.allangf.bibliotecaapi.rest.dto.BookBlockedDTO;
import br.com.allangf.bibliotecaapi.service.BookBlockedService;
import br.com.allangf.bibliotecaapi.service.BookStatisticsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.annotation.HttpConstraint;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/bookblocked")
public class BookBlockedController {

    private final BookBlockedService bookBlockedService;

    public BookBlockedController(BookBlockedService bookBlockedService) {
        this.bookBlockedService = bookBlockedService;
    }


    @ApiOperation("Bloquear um livro")
    @PostMapping("/lock")
    @ResponseStatus(HttpStatus.CREATED)
    public BookBlocked lockBook(@RequestBody @Valid BookBlockedDTO bookBlockedDTO) {
        return bookBlockedService.lockBook(bookBlockedDTO);
    }

    @ApiOperation("Desbloquear um livro")
    @PostMapping("/unlock")
    @ResponseStatus(HttpStatus.OK)
    public BookBlocked unlockBook(@RequestBody @Valid BookBlockedDTO bookBlockedDTO) {
        return bookBlockedService.unlockBook(bookBlockedDTO);
    }

    @ApiOperation("Lista todos os livros que estão travados para reservas")
    @GetMapping
    public List<BookBlocked> bookBlockeds() {
        return bookBlockedService.allBookBlocked();
    }


}