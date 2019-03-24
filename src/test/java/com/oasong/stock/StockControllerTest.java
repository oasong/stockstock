package com.oasong.stock;

import com.oasong.stock.Model.Stock;
import com.oasong.stock.Repository.StockRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;
import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest (webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StockControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private StockRepository stockRepository;

    @Before
    public void initData(){
        Stock data1 = new Stock();
        data1.setDate("19/4/98");
        data1.setName("Apple Jumkad");
        data1.setPrice(25000.25);
        data1.setStatus("OK");
        data1.setQuantity(4000);

        Stock data2 = new Stock();
        data2.setDate("24/8/97");
        data2.setName("Android Jumkad");
        data2.setPrice(15000.75);
        data2.setStatus("NOT OK");
        data2.setQuantity(2000);

        stockRepository.save(data1);
        stockRepository.save(data2);
    }

    @Test
    public void getStock(){
        Stock stock = testRestTemplate.getForObject("/stocks/1", Stock.class);
//        assertEquals(1, stock.getId());
        assertEquals("Apple Jumkad", stock.getName());
        assertEquals("19/4/98", stock.getDate());
        assertEquals(25000.25, stock.getPrice(), 0.00);
        assertEquals("OK", stock.getStatus());
        assertEquals(4000, stock.getQuantity());
    }

    @Test
    public void getStocks(){
        ArrayList<Stock> stocks = testRestTemplate.getForObject("/stocks", ArrayList.class);
        assertEquals(2, stocks.size());
    }

    @Test
    public void createNewStock(){
        Stock newStock = new Stock();
        newStock.setName("Tomatoes");
        newStock.setDate("4/11/99");
        newStock.setQuantity(1000);
        newStock.setPrice(4500.50);
        newStock.setStatus("OK");

        ResponseEntity<Stock> responseEntity = testRestTemplate.postForEntity("/stocks", newStock, Stock.class);

        assertEquals("Tomatoes", responseEntity.getBody().getName());
        assertEquals("4/11/99", responseEntity.getBody().getDate());
        assertEquals(1000, responseEntity.getBody().getQuantity());
        assertEquals(4500.50, responseEntity.getBody().getPrice(), 0.00);
        assertEquals("OK", responseEntity.getBody().getStatus());
    }

    @Test
    public void deleteStock(){
        ArrayList<Stock> stocks = stockRepository.findAll();
        int stockSize = stocks.size();
        int stockLastId = stocks.get(stockSize - 1).getId();
        testRestTemplate.delete("/stocks/" + stockLastId);

        assertEquals(1, stockRepository.count());
    }

    @After
    public void clearData(){
        stockRepository.deleteAll();
    }

}
