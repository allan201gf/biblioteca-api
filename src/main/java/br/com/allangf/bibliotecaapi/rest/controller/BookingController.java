package br.com.allangf.bibliotecaapi.rest.controller;

import br.com.allangf.bibliotecaapi.domain.entity.Booking;
import br.com.allangf.bibliotecaapi.rest.dto.BookingDTO;
import br.com.allangf.bibliotecaapi.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.awt.print.Book;
import java.util.List;

@RestController
@RequestMapping("/booking")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Booking saveBooking(@RequestBody BookingDTO bookingDTO) {
        return bookingService.save(bookingDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Booking> allBooking () {
        return bookingService.all();
    }

    @GetMapping("/whoWithIsTheBook")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Booking> whoWithIsTheBook(@RequestParam(value = "nameOrIdOfBook") String nameOrIdOfBook) {
        return bookingService.whoWithIsTheBook(nameOrIdOfBook);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteBookingById(@RequestParam(value = "bookingId") int bookingId) {
        bookingService.deleteBookingById(bookingId);
    }

}
