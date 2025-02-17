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

    @GetMapping
    public ResponseEntity<List<SalesQuotation>> getAllQuotations() {
        return new ResponseEntity<>(quotationService.getAllQuotations(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<SalesQuotation> getQuotationById(@PathVariable int id) {
        return new ResponseEntity<>(quotationService.getQuotationById(id), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<SalesQuotation> createNewQuotation(@RequestBody SalesQuotation quotation) {
        return new ResponseEntity<>(quotationService.createNewQuotation(quotation), HttpStatus.CREATED);
    } @PutMapping("/{id}")
    public ResponseEntity<SalesQuotation> updateQuotation(@PathVariable int id, @RequestBody SalesQuotation quotationDetails) {
        return new ResponseEntity<>(quotationService.updateQuotation(id, quotationDetails), HttpStatus.OK);
    }
    @DeleteMapping("/{id}") public ResponseEntity<Void> deleteQuotation(@PathVariable int id) {
        quotationService.deleteQuotation(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
