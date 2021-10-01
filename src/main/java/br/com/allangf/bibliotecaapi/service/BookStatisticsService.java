package br.com.allangf.bibliotecaapi.service;

import br.com.allangf.bibliotecaapi.domain.entity.BookStatistics;
import br.com.allangf.bibliotecaapi.domain.entity.TotalBookStatistics;
import org.json.JSONObject;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public interface BookStatisticsService {

    List<BookStatistics> allBooksStatistics();

    TotalBookStatistics totalBooking();

}
