package br.com.allangf.bibliotecaapi.service;

import br.com.allangf.bibliotecaapi.domain.entity.Booking;
import br.com.allangf.bibliotecaapi.rest.dto.BookingDTO;

import java.util.List;

public interface BookingService {

    Booking save (BookingDTO bookingDTO);

    List<Booking> all ();

    List<Booking> whoWithIsTheBook (String nameOrIdOfBook);

    void deleteBookingById(int bookingId);

}
