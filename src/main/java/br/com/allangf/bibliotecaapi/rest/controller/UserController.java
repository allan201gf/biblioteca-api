package br.com.allangf.bibliotecaapi.rest.controller;

import br.com.allangf.bibliotecaapi.domain.entity.User;
import br.com.allangf.bibliotecaapi.rest.dto.UserDTO;
import br.com.allangf.bibliotecaapi.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation("Criar usuário")
    @PostMapping
    public User saveUser (@RequestBody @Valid UserDTO userDTO) {
        return userService.save(userDTO);
    }

    @ApiOperation("Listar todos os usuários")
    @GetMapping
    public List<User> allUsers() {
        return userService.all();
    }

    @ApiOperation("Deletar usuário por Id")
    @DeleteMapping
    public void deleteUserById (@RequestParam(value = "bookingId") int userId) {
        userService.deleteUserById(userId);
    }


}
