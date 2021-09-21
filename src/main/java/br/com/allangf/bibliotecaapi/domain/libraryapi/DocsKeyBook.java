package br.com.allangf.bibliotecaapi.domain.libraryapi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocsKeyBook {

    private List<KeyBook> docs;

}
