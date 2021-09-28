package br.com.allangf.bibliotecaapi.rest.controller;

import br.com.allangf.bibliotecaapi.domain.entity.BookStatistics;
import br.com.allangf.bibliotecaapi.service.BookStatisticsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/statistics")
public class BookStatisticsController {

    private final BookStatisticsService bookStatisticsService;

    public BookStatisticsController(BookStatisticsService bookStatisticsService) {
        this.bookStatisticsService = bookStatisticsService;
    }

    @ApiOperation("Retorna o número total de reservas de cada livro")
    @GetMapping
    public List<BookStatistics> allBooksStatistics() {
        return bookStatisticsService.allBooksStatistics();
    }

    @ApiOperation("Retorna o número total de reservas de todos os livros somadas")
    @GetMapping("/total")
    public int totalBooking() {
        return bookStatisticsService.totalBooking();
    }

}
