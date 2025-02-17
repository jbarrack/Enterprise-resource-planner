package com.inventory.Erp.Services;

import com.inventory.Erp.Repository.GoodsReceiptNoteItemRepository;
import com.inventory.Erp.Repository.GoodsReceiptNoteRepository;
import com.inventory.Erp.Repository.StockCardRepository;
import com.inventory.Erp.model.GoodsReceiptNote;
import com.inventory.Erp.model.GoodsReceiptNoteItem;
import com.inventory.Erp.model.Product;
import com.inventory.Erp.model.StockCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodsReceiptNoteService {
    @Autowired
    private GoodsReceiptNoteRepository goodsReceiptNoteRepository;
    @Autowired
    private GoodsReceiptNoteItemRepository goodsReceiptNoteItemRepository;
    @Autowired
    private StockCardRepository stockCardRepository;

    public GoodsReceiptNoteService(GoodsReceiptNoteRepository goodsReceiptNoteRepository,
                                   GoodsReceiptNoteItemRepository goodsReceiptNoteItemRepository, StockCardRepository stockCardRepository) {
        this.goodsReceiptNoteRepository = goodsReceiptNoteRepository;
        this.goodsReceiptNoteItemRepository = goodsReceiptNoteItemRepository;
        this.stockCardRepository = stockCardRepository;
    }

    public GoodsReceiptNote createGoodsReceiptNote(GoodsReceiptNote grn) {
        GoodsReceiptNote savedGrn = goodsReceiptNoteRepository.save(grn);
        updateStock(savedGrn);
        return savedGrn;
    }

    private void updateStock(GoodsReceiptNote grn) {
        for (GoodsReceiptNoteItem item : grn.getItems()) {
            Product product = item.getProduct();
            StockCard stockCard = stockCardRepository.findByProduct(product);
            stockCard.setQuantity(stockCard.getQuantity() + item.getQuantity());
            stockCardRepository.save(stockCard);
        }
    }
}
