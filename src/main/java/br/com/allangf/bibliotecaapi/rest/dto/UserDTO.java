package br.com.allangf.bibliotecaapi.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    @NotEmpty(message = "O campo nome é obrigatório")
    private String name;
    @NotEmpty(message = "Campo CPF é obrigatório")
    @CPF(message = "CPF invalido")
    private String cpf;

}
