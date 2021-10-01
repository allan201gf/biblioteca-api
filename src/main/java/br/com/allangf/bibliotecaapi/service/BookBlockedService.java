package br.com.allangf.bibliotecaapi.service;

import br.com.allangf.bibliotecaapi.domain.entity.BookBlocked;
import br.com.allangf.bibliotecaapi.domain.entity.Booking;
import br.com.allangf.bibliotecaapi.rest.dto.BookBlockedDTO;
import br.com.allangf.bibliotecaapi.rest.dto.BookingDTO;

import java.util.List;

public interface BookBlockedService {

    BookBlocked lockBook(BookBlockedDTO bookBlockedDTO);

    BookBlocked unlockBook(BookBlockedDTO bookBlockedDTO);

    List<BookBlocked> allBookBlocked();

}
