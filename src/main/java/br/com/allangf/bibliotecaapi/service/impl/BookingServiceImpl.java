package br.com.allangf.bibliotecaapi.service.impl;

import br.com.allangf.bibliotecaapi.domain.entity.BookStatistics;
import br.com.allangf.bibliotecaapi.domain.entity.Booking;
import br.com.allangf.bibliotecaapi.domain.entity.User;
import br.com.allangf.bibliotecaapi.domain.repository.BookStatisticsRepository;
import br.com.allangf.bibliotecaapi.domain.repository.BookingRepository;
import br.com.allangf.bibliotecaapi.domain.repository.UserRepository;
import br.com.allangf.bibliotecaapi.rest.config.WebClientMetods;
import br.com.allangf.bibliotecaapi.rest.dto.BookingDTO;
import br.com.allangf.bibliotecaapi.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final UserRepository userRepository;
    private final BookingRepository bookingRepository;
    private final BookStatisticsRepository bookStatisticsRepository;

    @Autowired
    private WebClient webClientBooks;

    @Autowired WebClientMetods webClientMetods;


    @Override
    public Booking save(BookingDTO bookingDTO) {

        int idUser = bookingDTO.getUser();

        User user = userRepository
                .findById(idUser)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "O usuario de id " + idUser + " não foi encontrado"));

        LocalDate startBookingFormated = stringForDate(bookingDTO.getStartBooking());
        LocalDate endBookingFormated = stringForDate(bookingDTO.getEndBooking());

        if (endBookingFormated.isBefore(startBookingFormated)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "A data final não pode ser antes da data de início");
        }

        String openLibraryIdBook;
        String nameOfBook;

        if (bookingDTO.getOpenLibraryIdBook() == null) {
            openLibraryIdBook = webClientMetods.getCodeOfBook(bookingDTO.getNameOfBook());
        } else {
            openLibraryIdBook = bookingDTO.getOpenLibraryIdBook();
        }
        nameOfBook = webClientMetods.getNameOfBook(openLibraryIdBook);

        List<Booking> allBooking = bookingRepository.findAll();

        for (Booking booking: allBooking) {
            if (booking.getOpenLibraryIdBook().equals(openLibraryIdBook)) {

                if (!((startBookingFormated.isBefore(booking.getStartBooking()) && endBookingFormated.isBefore(booking.getStartBooking())) || (startBookingFormated.isAfter(booking.getEndBooking())))) {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                            "O livro " + nameOfBook + " está emprestado de " +
                                    booking.getStartBooking() + " até " + booking.getEndBooking());
                }
            }
        }

        if (bookStatisticsRepository.findByOpenLibraryIdBook(openLibraryIdBook).isEmpty()) {
            BookStatistics bookStatistics = new BookStatistics();
            bookStatistics.setNameOfBook(nameOfBook);
            bookStatistics.setOpenLibraryIdBook(openLibraryIdBook);
            bookStatistics.setNumbersOfBooking(1);
            bookStatisticsRepository.save(bookStatistics);
        } else {
            BookStatistics bookStatistics = bookStatisticsRepository.findByOpenLibraryIdBook(openLibraryIdBook).get(0);
            bookStatistics.setNumbersOfBooking(bookStatistics.getNumbersOfBooking() + 1);
            bookStatisticsRepository.save(bookStatistics);
        }

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setStartBooking(startBookingFormated);
        booking.setEndBooking(endBookingFormated);
        booking.setOpenLibraryIdBook(openLibraryIdBook);
        booking.setNameOfBook(nameOfBook);
        bookingRepository.save(booking);

        return booking;

    }

    @Override
    public List<Booking> all() {
        return bookingRepository.findAll();
    }

    @Override
    public List<Booking> whoWithIsTheBook (String nameOrIdOfBook) {

        List<Booking> allBooking = bookingRepository.findAll();
        for (Booking booking: allBooking) {
            if (booking.getOpenLibraryIdBook().equals(nameOrIdOfBook)) {
                return bookingRepository.findByOpenLibraryIdBook(nameOrIdOfBook);
            } else {
                return bookingRepository.findByNameOfBook(nameOrIdOfBook);
            }
        }
        return null;
    }

    public void deleteBookingById(int bookingId) {
        try {
            bookingRepository.deleteById(bookingId);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi possível deletar a reserva de Id " + bookingId + ", talvez ela não exista");
        }
    }

    // Metodos auxiliadores

    public LocalDate stringForDate (String dateString) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(dateString, format);
    }

}
