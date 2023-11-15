package com.example.stockanalyzerapi.service;

import com.example.stockanalyzerapi.entity.Stock;
import com.example.stockanalyzerapi.repository.StockRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

@Service
public class StockService {

    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    // Method to add a new stock
    public Stock addStock(Stock stockSymbol) {
        return stockRepository.save(stockSymbol);
    }

    // Method to update an existing stock
    public Stock updateStock(Stock stockSymbol) {
        return stockRepository.save(stockSymbol);
    }

    // Method to retrieve all stocks
    public List<Stock> getAllStocks() {
        return stockRepository.findAll();
    }

    // Method to retrieve a single stock by ID
    public Optional<Stock> getStockById(Integer id) {
        return stockRepository.findById(id);
    }

    // Method to delete a stock
    public void deleteStock(Integer id) {
        stockRepository.deleteById(id);
    }

	public List<Stock> findStocksBySymbol(String stockSymbol) {
		return stockRepository.findBystockSymbol(stockSymbol);
	}

	public List<Stock> findStocksBetweenDates(Date startDate, Date endDate) {
		return findStocksBetweenDates(startDate,endDate) ;
	}
	
	public OptionalDouble calculateAverageClosingPrice(String stockSymbol, Date startDate, Date endDate) {
	    List<Stock> stocks = stockRepository.findStocksBySymbolAndDateRange(stockSymbol, startDate, endDate);
	    
	    return stocks.stream()
	                 .mapToDouble(stock -> stock.getClose().doubleValue()) // Convert BigDecimal to double
	                 .average();
	}
}