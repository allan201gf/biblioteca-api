package br.com.allangf.bibliotecaapi.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDTO {

    private int user;
    private String nameOfBook;
    private String startBooking;
    private String endBooking;
    private String openLibraryIdBook;

}
