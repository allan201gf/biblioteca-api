package br.com.allangf.bibliotecaapi.domain.repository;

import br.com.allangf.bibliotecaapi.domain.entity.BookStatistics;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookStatisticsRepository extends JpaRepository<BookStatistics, Integer> {
}
