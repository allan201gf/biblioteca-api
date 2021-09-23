package br.com.allangf.bibliotecaapi.service;

import br.com.allangf.bibliotecaapi.domain.entity.BookStatistics;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public interface BookStatisticsService {

    List<BookStatistics> allBooksStatistics();

    int totalBooking();

}
