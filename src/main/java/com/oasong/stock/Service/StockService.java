package com.oasong.stock.Service;

import com.oasong.stock.Model.Stock;
import com.oasong.stock.Repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    // Get by ID
    public Stock getStock(int id){
        return stockRepository.findById(id).get();
    }

    // Get all Stocks
    public ArrayList<Stock> getAll(){
        Iterator<Stock> dataSet = stockRepository.findAll().iterator();
        ArrayList<Stock> stocks = new ArrayList<>();

        while (dataSet.hasNext()){
            stocks.add(dataSet.next());
        }
        return stocks;
    }

    // Save new Stock
    public void saveStock(Stock stock){
        stockRepository.save(stock);
    }

    // Delete Stock
    public boolean deleteStock(int id){
        Optional<Stock> stock = stockRepository.findById(id);
        if (stock.isPresent()){
            stockRepository.delete(stock.get());
            return true;
        }else{
            return false;
        }
    }

    // Update Stock
    public boolean updateStock(int id, Stock stock){
        Optional<Stock> stockOptional = stockRepository.findById(id);
        if (stockOptional.isPresent()){
            stock.setId(id);
            stockRepository.save(stock);
            return true;
        }else{
            return false;
        }
    }
}
