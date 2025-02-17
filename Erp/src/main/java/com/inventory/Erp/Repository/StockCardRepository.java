package com.inventory.Erp.Repository;

import com.inventory.Erp.model.Product;
import com.inventory.Erp.model.StockCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface StockCardRepository extends JpaRepository<StockCard, Integer> {
    StockCard findByProduct(Product product);
    Optional<StockCard> findByProductProductId(Long productId);
    StockCard findByProductProductId(int productId);

}
