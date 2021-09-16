package br.com.allangf.bibliotecaapi.rest.controller;

import br.com.allangf.bibliotecaapi.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

        private UserService userService;

        public UserController(UserService userService) {

            this.userService = userService;

        }

}
