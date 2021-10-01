package br.com.allangf.bibliotecaapi.domain.repository;

import br.com.allangf.bibliotecaapi.domain.entity.BookBlocked;
import br.com.allangf.bibliotecaapi.domain.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookBlockedRepository extends JpaRepository<BookBlocked, Integer> {

    @Query("select b from BookBlocked b where b.nameOfBook like :nameOfBook%")
    Optional<BookBlocked> findByNameOfBook(@Param("nameOfBook") String nameOfBook);

}
