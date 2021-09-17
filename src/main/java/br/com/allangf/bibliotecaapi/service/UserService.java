package br.com.allangf.bibliotecaapi.service;

import br.com.allangf.bibliotecaapi.domain.entity.User;
import br.com.allangf.bibliotecaapi.rest.dto.UserDTO;

import java.util.List;

public interface UserService {

    User save (UserDTO dto);

    List<User> all();
}
