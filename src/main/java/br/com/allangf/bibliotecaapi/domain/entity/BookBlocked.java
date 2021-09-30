package br.com.allangf.bibliotecaapi.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class BookBlocked {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    @Column(name = "name_of_book")
    String nameOfBook;

    @Column(name = "open_library_id_book")
    String openLibraryIdBook;

    @Column(name = "book_blocked")
    Boolean bookBlocked;

}
