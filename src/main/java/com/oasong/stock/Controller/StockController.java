package com.oasong.stock.Controller;

import com.oasong.stock.Model.Stock;
import com.oasong.stock.Service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class StockController {

    @Autowired
    StockService stockService;

    @GetMapping("/stocks")
    public ArrayList<Stock> getStocks() {return stockService.getAll();}

    @GetMapping("/stocks/{id}")
    public Stock getStock(@PathVariable int id) {return stockService.getStock(id);}

    @PostMapping("/stocks")
    public ResponseEntity createStock(@RequestBody Stock stock){
        stockService.saveStock(stock);
        return ResponseEntity.status(HttpStatus.CREATED).body(stock);
    }

    @PutMapping("/stocks/{id}")
    public ResponseEntity updateStock(@PathVariable int id,@RequestBody Stock stock){
        stockService.updateStock(id, stock);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/stocks/{id}")
    public ResponseEntity deleteStock(@PathVariable int id){
        boolean stockDeleted = stockService.deleteStock(id);
        if (stockDeleted){
            return new ResponseEntity("Stock deleted", HttpStatus.OK);
        }else{
            return new ResponseEntity("Fail", HttpStatus.NOT_FOUND);
        }
    }

}
