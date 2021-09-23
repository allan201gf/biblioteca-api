package br.com.allangf.bibliotecaapi.domain.repository;

import br.com.allangf.bibliotecaapi.domain.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Integer> {

    List<Booking> findByOpenLibraryIdBook(String nameOrIdOfBook);

    @Query("select b from Booking b where b.nameOfBook like :nameOrIdOfBook%")
    List<Booking> findByNameOfBook(@Param("nameOrIdOfBook") String nameOrIdOfBook);

    void deleteById(int bookingId);
}
