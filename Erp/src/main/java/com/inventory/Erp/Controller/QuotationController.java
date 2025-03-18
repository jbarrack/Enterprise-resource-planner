package com.inventory.Erp.Controller;

import com.inventory.Erp.Services.SalesQuotationService;
import com.inventory.Erp.model.SalesQuotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/quotations")
public class QuotationController {
    @Autowired
    private SalesQuotationService quotationService;
    public QuotationController(SalesQuotationService quotationService) {
        this.quotationService = quotationService;
    }

    @RequestMapping(value = "/quaotationLists", method = RequestMethod.GET)
    public ResponseEntity<List<SalesQuotation>> getAllQuotations() {
        List<SalesQuotation> salesQuotationList = quotationService.getAllQuotations();
        for(SalesQuotation i : salesQuotationList){
            return new ResponseEntity<>(salesQuotationList, HttpStatus.OK);
        }
        //return new ResponseEntity<>(salesQuotationList, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<SalesQuotation> getQuotationById(@PathVariable int id) {
        return new ResponseEntity<>(quotationService.getQuotationById(id), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<SalesQuotation> createNewQuotation(@RequestBody SalesQuotation quotation) {
        SalesQuotation salesQuoataion = quotationService.createNewQuotation(quotation);
        return new ResponseEntity<>(salesQuoataion, HttpStatus.CREATED);
    } @PutMapping("/{id}")
    public ResponseEntity<SalesQuotation> updateQuotation(@PathVariable int id, @RequestBody SalesQuotation quotationDetails) {
        SalesQuotation updatedSalesQuoation = quotationService.updateQuotation(id,quotationDetails);
        return new ResponseEntity<>(updatedSalesQuoation, HttpStatus.OK);
    }
    @RequestMapping(value = "/saleQuoation/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Void> deleteQuotation(@PathVariable int id) {
        quotationService.deleteQuotation(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
