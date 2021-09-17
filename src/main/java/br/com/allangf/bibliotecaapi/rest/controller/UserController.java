package br.com.allangf.bibliotecaapi.rest.controller;

import br.com.allangf.bibliotecaapi.domain.entity.User;
import br.com.allangf.bibliotecaapi.rest.dto.UserDTO;
import br.com.allangf.bibliotecaapi.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User saveUser (@RequestBody UserDTO userDTO) {
        return userService.save(userDTO);
    }

    @GetMapping
    public List<User> allUsers() {
        return userService.all();
    }




}
