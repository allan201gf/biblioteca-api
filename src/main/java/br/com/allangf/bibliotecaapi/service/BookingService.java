package br.com.allangf.bibliotecaapi.service;

import br.com.allangf.bibliotecaapi.domain.entity.Booking;
import br.com.allangf.bibliotecaapi.rest.dto.BookingDTO;

public interface BookingService {

    Booking save (BookingDTO bookingDTO);

}
