package com.inventory.Erp.Controller;

import com.inventory.Erp.Services.PurchaseOrderService;
import com.inventory.Erp.model.PurchaseOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/purchaseOrders")
public class PurchaseOrderController {
    @Autowired
    private PurchaseOrderService purchaseOrderService;

    public PurchaseOrderController(PurchaseOrderService purchaseOrderService) {
        this.purchaseOrderService = purchaseOrderService;
    }

    @RequestMapping(value = "/createPurhaseOrder",method = RequestMethod.POST)
    public ResponseEntity<PurchaseOrder> createPurchaseOrder(@RequestBody PurchaseOrder purchaseOrder) {
        PurchaseOrder purchasedOrder = purchaseOrderService.createPurchaseOrder(purchaseOrder);
        return new ResponseEntity<>(purchasedOrder,HttpStatus.CREATED);
    }

@RequestMapping(value = "/updatePurchaseOrder", method = RequestMethod.PUT)
public ResponseEntity<PurchaseOrder> editOrder(@PathVariable int purchaseOrderId,@RequestBody PurchaseOrder editpurchaseOrder) {
    PurchaseOrder editpurchaseorder = purchaseOrderService.updatePurchaseOrder(purchaseOrderId, editpurchaseOrder);

    return new ResponseEntity<>(editpurchaseOrder, HttpStatus.OK);
}

@RequestMapping(value = "/getPurchaseorderById/{id}", method = RequestMethod.GET)
    public ResponseEntity<PurchaseOrder>getById(@PathVariable("id") int id){
        PurchaseOrder getpurchasorderId = purchaseOrderService.getPurchaseOrderById(id);
        if (getpurchasorderId.getSupplier()==null || getpurchasorderId.isStatus()==false){
            throw new RuntimeException("record not found or suspended!");
        }
       return new ResponseEntity<>(getpurchasorderId, HttpStatus.OK);
}

@RequestMapping(value = "/getAllPurchaseOrder", method = RequestMethod.GET)
 public ResponseEntity<List<PurchaseOrder>> fetchAllOrder(){
        List<PurchaseOrder> orderlist = purchaseOrderService.getPurchaseOrderList();
        return new ResponseEntity<>(orderlist,HttpStatus.OK);
    }
}