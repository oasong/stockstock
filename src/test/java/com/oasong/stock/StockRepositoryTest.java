package com.oasong.stock;

import com.oasong.stock.Model.Stock;
import com.oasong.stock.Repository.StockRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class StockRepositoryTest {

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
    public void findById(){
        Stock stock = stockRepository.findById(2).get();
        assertEquals(2, stock.getId());
        assertEquals("Android Jumkad", stock.getName());
    }


}
