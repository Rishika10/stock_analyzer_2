package com.example.stockanalyzerapi.repository;

import com.example.stockanalyzerapi.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {
	
	// Find stocks by stock symbol
    List<Stock> findBystockSymbol(String stockSymbol);

    // Find stocks within a specific date range
    @Query("SELECT s FROM Stock s WHERE s.date BETWEEN :startDate AND :endDate")
    List<Stock> findStocksBetweenDates(Date startDate, Date endDate);

	List<Stock> findStocksBySymbolAndDateRange(String stockSymbol, Date startDate, Date endDate);
}