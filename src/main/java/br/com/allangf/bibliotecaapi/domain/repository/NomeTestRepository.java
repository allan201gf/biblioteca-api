package br.com.allangf.bibliotecaapi.domain.repository;

import br.com.allangf.bibliotecaapi.domain.entity.NomeTest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NomeTestRepository extends JpaRepository<NomeTest, Integer> {
}
