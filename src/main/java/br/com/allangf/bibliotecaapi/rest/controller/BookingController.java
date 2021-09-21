package br.com.allangf.bibliotecaapi.rest.controller;

import br.com.allangf.bibliotecaapi.domain.entity.Booking;
import br.com.allangf.bibliotecaapi.rest.dto.BookingDTO;
import br.com.allangf.bibliotecaapi.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.awt.print.Book;

@RestController
@RequestMapping("/booking")
public class BookingController {

    private BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Booking saveBooking(@RequestBody BookingDTO bookingDTO) {
        Booking booking = bookingService.save(bookingDTO);
        return booking;
    }

}
