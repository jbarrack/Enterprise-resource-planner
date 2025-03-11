package com.inventory.Erp.Services;

import com.inventory.Erp.ExeceptionsHandler.InsufficientStockException;
import com.inventory.Erp.ExeceptionsHandler.ResourceNotFoundException;
import com.inventory.Erp.Repository.CustomerRepository;
import com.inventory.Erp.Repository.ProductRepository;
import com.inventory.Erp.Repository.SalesOrderRepository;
import com.inventory.Erp.Repository.StockCardRepository;
import com.inventory.Erp.model.SalesOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalesOrderService {
    @Autowired private SalesOrderRepository salesOrderRepository;
    @Autowired private StockCardRepository stockCardRepository;
    @Autowired private CustomerRepository customerRepository;
    @Autowired private StockCardService stockCardService;
    @Autowired private ProductRepository productRepository;

    public SalesOrderService(SalesOrderRepository salesOrderRepository, StockCardRepository stockCardRepository, CustomerRepository customerRepository, StockCardService stockCardService, ProductRepository productRepository) {
        this.salesOrderRepository = salesOrderRepository;
        this.stockCardRepository = stockCardRepository;
        this.customerRepository = customerRepository;
        this.stockCardService = stockCardService;
        this.productRepository = productRepository;
    }
    public List<SalesOrder> getAllSalesOrders() {
        List<SalesOrder> fetchOrderList = salesOrderRepository.findAll();
        if(fetchOrderList.isEmpty()){
            throw new ResourceNotFoundException("No record found");
        }
        return fetchOrderList;
    }

    public SalesOrder createSalesOrder(SalesOrder salesOrder){
        if (!isStockAvailable(salesOrder)){
            throw new InsufficientStockException("Insufficient stock to complete this Order.");
        }
        salesOrder.setCreatedAt(new java.sql.Timestamp(System.currentTimeMillis()));
        salesOrder.setOrderDate(new java.sql.Date(System.currentTimeMillis()));
        salesOrder.setProduct(salesOrder.getProduct());
        salesOrder.setCustomers(salesOrder.getCustomers());
        salesOrder.setDiscountAllowed(salesOrder.getDiscountAllowed());
        salesOrder.setOrderDate(salesOrder.getOrderDate());
        salesOrder.setStatus(salesOrder.getStatus());
        salesOrder.setItemsDescriptions(salesOrder.getItemsDescriptions());
        salesOrder.setTotalAmount(calculateTotalAmount(salesOrder));
        return salesOrderRepository.save(salesOrder);
    }

    public SalesOrder updateSalesOrder(long salesOrderId, SalesOrder salesOrderDetails) {
          Optional<SalesOrder> updateSalesOrder = salesOrderRepository.findById(salesOrderId);
          if(!updateSalesOrder.isPresent()){
              throw new ResourceNotFoundException("Transaction not found");
          }
            SalesOrder existingOrder = updateSalesOrder.get();
            existingOrder.setOrderDate(salesOrderDetails.getOrderDate());
            existingOrder.setTotalAmount(calculateTotalAmount(salesOrderDetails));
            existingOrder.setItemsDescriptions(salesOrderDetails.getItemsDescriptions());
            existingOrder.setQuantity(salesOrderDetails.getQuantity());
            existingOrder.setUnitprice(salesOrderDetails.getUnitprice());
            existingOrder.setVat(salesOrderDetails.getVat());
            existingOrder.setStatus(existingOrder.getStatus());
            calculateTotalAmount(existingOrder);
            isStockAvailable(existingOrder);
            return salesOrderRepository.save(existingOrder);

    }
    private boolean isStockAvailable(SalesOrder salesOrder) {
        return stockCardRepository.findByProductProductId(salesOrder.getProduct().getProductId()).map(stockCard ->
                stockCard.getQuantity() >= salesOrder.getQuantity()).orElse(true);
    }
    private double calculateTotalAmount(SalesOrder salesOrder) {
        double unitPrice = salesOrder.getUnitprice();
        int quantity = salesOrder.getQuantity();
        double vat = salesOrder.getVat();
        double discountAllowed = salesOrder.getDiscountAllowed();
        double subtotal = quantity * unitPrice;
        double totalWithVAT = subtotal + (subtotal * vat / 100);
        double price = totalWithVAT - discountAllowed;
        return price;
    }
    public void deleteSalesOrder(long id) {
        SalesOrder salesOrder = salesOrderRepository.findById(id).orElseThrow(() -> new RuntimeException("SalesOrder with id " + id + " not found"));
        salesOrderRepository.delete(salesOrder);
    }
    public SalesOrder getSalesOrderById(long id) {
        return salesOrderRepository.findById(id).orElseThrow(() -> new RuntimeException("No record With " + id + "  found"));
    }
}


