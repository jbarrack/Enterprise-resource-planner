package com.inventory.Erp.Repository;

import com.inventory.Erp.model.GoodsReceiptNote;
import com.inventory.Erp.model.PurchaseDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodsReceiptNoteRepository extends JpaRepository<GoodsReceiptNote, Integer> {
}
