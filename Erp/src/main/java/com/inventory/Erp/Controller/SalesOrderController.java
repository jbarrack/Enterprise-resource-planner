package com.inventory.Erp.Controller;

import com.inventory.Erp.ExeceptionsHandler.ResourceNotFoundException;
import com.inventory.Erp.Services.SalesOrderService;
import com.inventory.Erp.model.SalesOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/salesOrders")
public class SalesOrderController {
    @Autowired  private SalesOrderService salesOrderService;

    public SalesOrderController(SalesOrderService salesOrderService) {
        this.salesOrderService = salesOrderService;
    }
    @RequestMapping(value = "/salesOrderList",method = RequestMethod.GET)
    public ResponseEntity<List<SalesOrder>> getAllSalesOrders() {
        return new ResponseEntity<>(salesOrderService.getAllSalesOrders(), HttpStatus.OK);
    }
    @RequestMapping(value = "/salesOrders/{id}", method=RequestMethod.GET)
    public ResponseEntity<SalesOrder> getSalesOrderById(@PathVariable int id) {
        SalesOrder fethOrderByid =salesOrderService.getSalesOrderById(id);
        return new ResponseEntity<>(fethOrderByid, HttpStatus.OK);
    }
    @RequestMapping(value = "/createNewOrders",method = RequestMethod.POST)
    public ResponseEntity<SalesOrder> createNewSalesOrder(@RequestBody SalesOrder salesOrder) {
            SalesOrder creatingNewSalesOrder = salesOrderService.createSalesOrder(salesOrder);
            return new ResponseEntity<>(creatingNewSalesOrder, HttpStatus.CREATED);
    }
    @RequestMapping(value = "/salesOrder/{id}",method = RequestMethod.PUT)
    public ResponseEntity<SalesOrder> updateSalesOrder(@PathVariable int id, @RequestBody SalesOrder salesOrderDetails) {
        SalesOrder updateOrder = salesOrderService.updateSalesOrder(id,salesOrderDetails);
        return new ResponseEntity<>(updateOrder,HttpStatus.OK);
    }
    @RequestMapping(value = "/deleteOrder/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteSalesOrder(@PathVariable int id){
        try{
            salesOrderService.deleteSalesOrder(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (RuntimeException e){
            System.out.println("Transaction ACID");
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }
    }
}
