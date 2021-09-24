package br.com.allangf.bibliotecaapi.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    @NotEmpty(message = "O nome não pode ser nulo")
    private String name;
    private String cpf;

}
