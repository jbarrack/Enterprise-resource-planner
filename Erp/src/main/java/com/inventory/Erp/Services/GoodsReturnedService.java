package com.inventory.Erp.Services;

import com.inventory.Erp.Repository.*;
import com.inventory.Erp.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class GoodsReturnedService {
    @Autowired private GoodsReturnedRepository goodsReturnedRepository;
    @Autowired private StockCardRepository stockCardRepository;
    @Autowired private SalesInvoiceRepository salesInvoiceRepository;
    @Autowired private CustomerRepository customerRepository;
    @Autowired private AccountService accountService;
    @Autowired private TransactionRepository transactionRepository;

    public GoodsReturnedService(GoodsReturnedRepository goodsReturnedRepository, StockCardRepository
        stockCardRepository,SalesInvoiceRepository salesInvoiceRepository, CustomerRepository customerRepository,
        AccountService accountService, TransactionRepository transactionRepository) {
        this.goodsReturnedRepository = goodsReturnedRepository;
        this.stockCardRepository = stockCardRepository;
        this.salesInvoiceRepository = salesInvoiceRepository;
        this.customerRepository = customerRepository;
        this.accountService = accountService;
        this.transactionRepository = transactionRepository;
    }
    public List<GoodsReturned> getGoodsReturnedList(){
        List<GoodsReturned> goodsReturnedList = goodsReturnedRepository.findAll();

        if(goodsReturnedList.isEmpty()){
          throw new RuntimeException("Record not found");
        }
        return goodsReturnedList;
    }
    public GoodsReturned creatNewRecord(GoodsReturned goodsReturned){
        try {
            GoodsReturned newGoodReturnNotes = new GoodsReturned();
            newGoodReturnNotes.setCreatedAt(newGoodReturnNotes.getCreatedAt());
            newGoodReturnNotes.setReturnId(newGoodReturnNotes.getReturnId());
            newGoodReturnNotes.setGoodsDescription(goodsReturned.getGoodsDescription());
            newGoodReturnNotes.setQuantity(newGoodReturnNotes.getQuantity());
            newGoodReturnNotes.setReturnDate(newGoodReturnNotes.getReturnDate());
            newGoodReturnNotes.setCondition(newGoodReturnNotes.getCondition());
            return goodsReturnedRepository.save(newGoodReturnNotes);
        }catch (Exception e){
            e.printStackTrace();
        }
        return goodsReturned;
    }
    public GoodsReturned findGoodsById(int id){
        GoodsReturned getGoodsReturned = goodsReturnedRepository.findById(id).orElseThrow(()->new UsernameNotFoundException("Record not found"));
        return getGoodsReturned;
    }
    public GoodsReturned updateGoodsReturned(int id,GoodsReturned updateGoodsReturned){
       

        Optional<GoodsReturned> fetchRecord = goodsReturnedRepository.findById(id);
        if(fetchRecord.isEmpty()) throw new RuntimeException("No records found.");

        GoodsReturned editRecorda = fetchRecord.get();
        editRecorda.setCondition(updateGoodsReturned.getCondition());
        editRecorda.setReturnId(updateGoodsReturned.getReturnId());
        editRecorda.setGoodsDescription(updateGoodsReturned.getGoodsDescription());
        editRecorda.setReturnDate(updateGoodsReturned.getReturnDate());
        editRecorda.setCustomer(updateGoodsReturned.getCustomer());
        editRecorda.setQuantity(updateGoodsReturned.getQuantity());
        editRecorda.setReason(updateGoodsReturned.getReason());
        editRecorda.setSalesOrder(updateGoodsReturned.getSalesOrder());
        editRecorda.setCreatedAt(updateGoodsReturned.getCreatedAt());
        editRecorda.setReturnDate(updateGoodsReturned.getReturnDate());
        return goodsReturnedRepository.save(editRecorda);
    }
    public void deleteRecord (int id){
        Optional<GoodsReturned> searchRecordGoodsReturned = Optional.ofNullable(goodsReturnedRepository.findById(id).orElseThrow(() -> new RuntimeException("not found")));
        if (searchRecordGoodsReturned.isEmpty()) System.out.println("Records found, Search again!");
        goodsReturnedRepository.deleteById(id);

    }

 }