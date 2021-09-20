package br.com.allangf.bibliotecaapi.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
@Entity
public class NomeTest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int titleId;

    private String title;

}
