package com.inventory.Erp.Services;

import com.inventory.Erp.Repository.PurchaseDetailRepository;
import com.inventory.Erp.Repository.PurchaseOrderRepository;
import com.inventory.Erp.model.PurchaseDetail;
import com.inventory.Erp.model.PurchaseOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.RuntimeErrorException;
import java.util.List;
import java.util.Optional;

@Service
public class PurchaseOrderService {
    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;
    @Autowired
    private PurchaseDetailRepository purchaseDetailRepository;

    public PurchaseOrderService(PurchaseOrderRepository purchaseOrderRepository, PurchaseDetailRepository purchaseDetailRepository) {
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.purchaseDetailRepository = purchaseDetailRepository;
    }

    public PurchaseOrder createPurchaseOrder(PurchaseOrder purchaseOrder) {
        return purchaseOrderRepository.save(purchaseOrder);
    }

    public List<PurchaseOrder> getPurchaseOrderList(){
        return purchaseOrderRepository.findAll();
    }

    public PurchaseOrder getPurchaseOrderById(int purchaseOrderId){
        return purchaseOrderRepository.findById(purchaseOrderId).orElseThrow(()->new RuntimeException("Record not found"));
    }
    public PurchaseOrder createNewPuchaseOrder(PurchaseOrder purchaseOrder){
        purchaseOrder.setCreatedAt(purchaseOrder.getCreatedAt());
        purchaseOrder.setPurchaseOrderId(purchaseOrder.getPurchaseOrderId());
        purchaseOrder.setStatus(true);
        purchaseOrder.setVat(purchaseOrder.getVat());
        purchaseOrder.setItems(purchaseOrder.getItems());
        purchaseOrder.setOrderDate(purchaseOrder.getOrderDate());
        purchaseOrder.setTotalAmount(purchaseOrder.getTotalAmount());
        purchaseOrder.setSubtotal(purchaseOrder.getSubtotal());
        purchaseOrder.setSupplier(purchaseOrder.getSupplier());
        return purchaseOrderRepository.save(purchaseOrder);
    }
    public PurchaseOrder updatePurchaseOrder(int purchaseOrderId, PurchaseOrder updatePurchaseOrder){
        Optional<PurchaseOrder> updatedetails = purchaseOrderRepository.findPurchaseOrderByPurchaseOrderId(purchaseOrderId);

        if(!updatedetails.isPresent()){
            throw new RuntimeException("Records not found");
        }
        PurchaseOrder updatePurchaseorder = updatedetails.get();
        updatePurchaseorder.setSupplier(updatePurchaseorder.getSupplier());
        updatePurchaseorder.setOrderDate(updatePurchaseOrder.getOrderDate());
        updatePurchaseorder.setVat(updatePurchaseorder.getVat());
        updatePurchaseorder.setTotalAmount(updatePurchaseorder.getTotalAmount());
        updatePurchaseorder.setOrderDate(updatePurchaseOrder.getOrderDate());
        updatePurchaseorder.setStatus(true);
        updatePurchaseorder.setItems(updatePurchaseorder.getItems());
        return purchaseOrderRepository.save(updatePurchaseOrder);
    }

}
