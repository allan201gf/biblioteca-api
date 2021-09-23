package br.com.allangf.bibliotecaapi.service.impl;

import br.com.allangf.bibliotecaapi.domain.entity.BookStatistics;
import br.com.allangf.bibliotecaapi.domain.repository.BookStatisticsRepository;
import br.com.allangf.bibliotecaapi.service.BookStatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookStatisticsImp implements BookStatisticsService {

    private final BookStatisticsRepository bookStatisticsRepository;

    @Override
    public List<BookStatistics> allBooksStatistics() {
        List<BookStatistics> allBooksStatistics = bookStatisticsRepository.findAll();
        if(allBooksStatistics.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não existe estatisticas para apresentar");
        }
        return allBooksStatistics;
    }
}