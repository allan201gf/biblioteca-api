package br.com.allangf.bibliotecaapi.domain.repository;

import br.com.allangf.bibliotecaapi.domain.entity.BookStatistics;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookStatisticsRepository extends JpaRepository<BookStatistics, Integer> {

    List<BookStatistics> findByOpenLibraryIdBook(String openLibraryIdBook);

}
