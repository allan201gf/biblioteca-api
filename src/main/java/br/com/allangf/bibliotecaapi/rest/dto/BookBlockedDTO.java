package br.com.allangf.bibliotecaapi.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookBlockedDTO {

    @NotEmpty(message = "O campo nameOfBook é obrigatório")
    private String nameOfBook;

}
