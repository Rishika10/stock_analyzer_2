package com.example.stockanalyzerapi.controller;

import com.example.stockanalyzerapi.entity.Stock;
import com.example.stockanalyzerapi.service.StockService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.OptionalDouble;


@RestController
@RequestMapping("/api/stocks")
public class StockController {

    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping
    public ResponseEntity<List<Stock>> getAllStocks() {
        return ResponseEntity.ok(stockService.getAllStocks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Stock> getStockById(@PathVariable int id) {
        return stockService.getStockById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Stock> addStock(@RequestBody Stock stock) {
        return ResponseEntity.ok(stockService.addStock(stock));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Stock> updateStock(@PathVariable int id, @RequestBody Stock stock) {
        return ResponseEntity.ok(stockService.updateStock(stock));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStock(@PathVariable int id) {
        stockService.deleteStock(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/symbol/{symbol}")
    public ResponseEntity<Object> findStocksBySymbol(@PathVariable String symbol) {
        return ResponseEntity.ok(stockService.findStocksBySymbol(symbol));
    }

    @GetMapping("/date")
    public ResponseEntity<Object> findStocksBetweenDates(@RequestParam Date startDate, @RequestParam Date endDate) {
        return ResponseEntity.ok(stockService.findStocksBetweenDates(startDate, endDate));
    }
    
    @GetMapping("/averageClosingPrice/{symbol}")
    public ResponseEntity<Double> getAverageClosingPrice(@PathVariable String symbol,
                                                         @RequestParam Date startDate,
                                                         @RequestParam Date endDate) {
        OptionalDouble average = stockService.calculateAverageClosingPrice(symbol, startDate, endDate);
        if (average.isPresent()) {
            return ResponseEntity.ok(average.getAsDouble());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}