package br.com.allangf.bibliotecaapi.service;

import br.com.allangf.bibliotecaapi.domain.entity.BookStatistics;

import java.util.List;

public interface BookStatisticsService {

    List<BookStatistics> allBooksStatistics();

}
