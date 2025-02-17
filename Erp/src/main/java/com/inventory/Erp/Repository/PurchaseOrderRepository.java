package com.inventory.Erp.Repository;

import com.inventory.Erp.model.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Integer> {
    Optional<PurchaseOrder> findPurchaseOrderByPurchaseOrderId(int purchaseOrderId);
}
