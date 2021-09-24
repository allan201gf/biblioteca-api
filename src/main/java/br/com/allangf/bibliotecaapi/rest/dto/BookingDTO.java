package br.com.allangf.bibliotecaapi.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDTO {

    @NotNull(message = "Obrigatório informar um usuário")
    private int user;
    private String nameOfBook;
    @NotEmpty(message = "Obrigatório informar uma data de início da reserva")
    private String startBooking;
    @NotEmpty(message = "Obrigatório informa uma data de término da reserva")
    private String endBooking;
    private String openLibraryIdBook;

}
