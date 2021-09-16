package br.com.allangf.bibliotecaapi.service.impl;

import br.com.allangf.bibliotecaapi.domain.entity.User;
import br.com.allangf.bibliotecaapi.domain.repository.UserRepository;
import br.com.allangf.bibliotecaapi.rest.dto.UserDTO;
import br.com.allangf.bibliotecaapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User save(UserDTO dto) {

        User user = new User();
        user.setName(dto.getName());
        user.setCpf(dto.getCpf());

        return userRepository.save(user);

    }
}
