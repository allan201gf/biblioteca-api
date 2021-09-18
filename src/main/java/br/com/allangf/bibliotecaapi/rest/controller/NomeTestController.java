package br.com.allangf.bibliotecaapi.rest.controller;

import br.com.allangf.bibliotecaapi.domain.entity.NomeTest;
import br.com.allangf.bibliotecaapi.domain.repository.NomeTestRepository;
import br.com.allangf.bibliotecaapi.service.NomeTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teste")
public class NomeTestController {

    @Autowired
    private NomeTestService nomeTestService;

    @GetMapping("/{codigo}")
    public ResponseEntity<NomeTest> obterNome (@PathVariable String codigo) {

        NomeTest nomeTest = this.nomeTestService.obterNome(codigo);

        return ResponseEntity.ok(nomeTest);

    }

}
