package br.com.allangf.bibliotecaapi.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int bookingId;

    @Column(name = "open_library_id_book")
    private String openLibraryIdBook;

    @Column(name = "name_of_book")
    private String nameOfBook;

//    @Column(name = "code_of_book")
//    private String codeOfBook;

    @Column(name = "start_booking")
    private LocalDate startBooking;

    @Column(name = "end_booking")
    private LocalDate endBooking;

    @ManyToOne
    @JoinColumn(name = "User_Id")
    private User user;

}
