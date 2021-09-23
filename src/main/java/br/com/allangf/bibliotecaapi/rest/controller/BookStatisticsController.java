package br.com.allangf.bibliotecaapi.rest.controller;

import br.com.allangf.bibliotecaapi.domain.entity.BookStatistics;
import br.com.allangf.bibliotecaapi.service.BookStatisticsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/statistics")
public class BookStatisticsController {

    private final BookStatisticsService bookStatisticsService;

    public BookStatisticsController(BookStatisticsService bookStatisticsService) {
        this.bookStatisticsService = bookStatisticsService;
    }

    @GetMapping
    public List<BookStatistics> allBooksStatistics() {
        return bookStatisticsService.allBooksStatistics();
    }

    @GetMapping("/total")
    public int totalBooking() {
        return bookStatisticsService.totalBooking();
    }

}
