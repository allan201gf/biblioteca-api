package br.com.allangf.bibliotecaapi.service.impl;

import br.com.allangf.bibliotecaapi.domain.entity.BookStatistics;
import br.com.allangf.bibliotecaapi.domain.entity.Booking;
import br.com.allangf.bibliotecaapi.domain.entity.User;
import br.com.allangf.bibliotecaapi.domain.exception.RuleOfException;
import br.com.allangf.bibliotecaapi.domain.repository.BookStatisticsRepository;
import br.com.allangf.bibliotecaapi.domain.repository.BookingRepository;
import br.com.allangf.bibliotecaapi.domain.repository.UserRepository;
import br.com.allangf.bibliotecaapi.rest.config.WebClientMetods;
import br.com.allangf.bibliotecaapi.rest.dto.BookingDTO;
import br.com.allangf.bibliotecaapi.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

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
                        new RuleOfException("O usuario de id " + idUser
                                + " não foi encontrado"));

        LocalDate startBookingFormated = stringForDate(bookingDTO.getStartBooking());
        LocalDate endBookingFormated = stringForDate(bookingDTO.getEndBooking());

        if (startBookingFormated.isBefore(LocalDate.now())) {
            throw new RuleOfException("A data de início não pode ser anterior a data atual");
        } else if (endBookingFormated.isBefore(startBookingFormated)) {
            throw new RuleOfException("A data final não pode ser antes da data de início");
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
                    throw new RuleOfException("O livro " + nameOfBook
                            + " está emprestado de " +
                            booking.getStartBooking() + " até "
                            + booking.getEndBooking());
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
            throw new RuleOfException("Não foi possível deletar a reserva de Id " + bookingId);
        }
    }

    public List<Booking> booksForReturnToday() {
        return bookingRepository.findByEndBooking(LocalDate.now());
    }

    public Booking putBooking(int bookingId, BookingDTO bookingDTO) {
        return bookingRepository.findById(bookingId)
                .map(booking -> {
                    booking.setUser(booking.getUser());

                    if (bookingDTO.getEndBooking() != null) {
                        booking.setEndBooking(stringForDate(bookingDTO.getEndBooking()));
                    } else {
                        booking.setEndBooking(booking.getEndBooking());
                    }

                    if (bookingDTO.getStartBooking() != null) {
                        booking.setStartBooking(stringForDate(bookingDTO.getStartBooking()));
                    } else {
                        booking.setStartBooking(booking.getStartBooking());
                    }

                    booking.setOpenLibraryIdBook(booking.getOpenLibraryIdBook());
                    booking.setNameOfBook(booking.getNameOfBook());

                    bookingRepository.save(booking);

                    return booking;
                }).orElseThrow(() -> new RuleOfException("A reserva de id " + bookingId + " não foi encontrada"));
    }

    // Métodos auxiliadores

    public LocalDate stringForDate (String dateString) {
        try {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return LocalDate.parse(dateString, format);
        } catch (Exception e) {
            throw new RuleOfException("Data invalida ou nula, formato correto: dd/mm/aaaa ");
        }

    }

}
