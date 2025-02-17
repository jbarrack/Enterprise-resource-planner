package com.inventory.Erp.Services;

import com.inventory.Erp.Repository.StockCardRepository;
import com.inventory.Erp.model.Product;
import com.inventory.Erp.model.StockCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockCardService {
    @Autowired
    private StockCardRepository stockCardRepository;
    public StockCardService(StockCardRepository stockCardRepository) {
        this.stockCardRepository = stockCardRepository;
    }
    public StockCard createStockCard(Product product, int quantity) {
        StockCard stockCard = new StockCard();
        stockCard.setProduct(product);
        stockCard.setQuantity(quantity);
        stockCard.setLastUpdated(new java.sql.Timestamp(System.currentTimeMillis()));
        return stockCardRepository.save(stockCard);
    }
}
