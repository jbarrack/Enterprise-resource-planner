package com.inventory.Erp.Controller;

import com.inventory.Erp.Services.SalesInvoiceService;
import com.inventory.Erp.model.SalesInvoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/salesInvoices")
public class SalesInvoiceController {
    @Autowired
    private SalesInvoiceService salesInvoiceService;

    public SalesInvoiceController(SalesInvoiceService salesInvoiceService) {
        this.salesInvoiceService = salesInvoiceService;
    }
    @RequestMapping(value = "/allsalesInvoice", method = RequestMethod.GET)
    public ResponseEntity<List<SalesInvoice>> getAllSalesInvoices() {
        List<SalesInvoice> fetchSalesinvoice = salesInvoiceService.getAllSalesInvoices();
        return new ResponseEntity<>(fetchSalesinvoice, HttpStatus.OK);
    }
    @RequestMapping(value = "/getsalesInvoiceBy/{id}",method = RequestMethod.GET)
    public ResponseEntity<SalesInvoice> getSalesInvoiceById(@PathVariable int id) {
        SalesInvoice getSalesInvoiveById = salesInvoiceService.getSalesInvoiceById(id);
        return new ResponseEntity<>(getSalesInvoiveById, HttpStatus.OK);
    }
    @RequestMapping(value = "/customer/{customerId}", method = RequestMethod.GET)
    public ResponseEntity<List<SalesInvoice>> getInvoicesByCustomerId(@PathVariable int customerId) {
        List<SalesInvoice> invoices = salesInvoiceService.getInvoicesByCustomerId(customerId);
        return new ResponseEntity<>(invoices, HttpStatus.OK);
    }

    @RequestMapping(value = "/getInvoiceBydateRange", method = RequestMethod.GET)
    public ResponseEntity<List<SalesInvoice>> getInvoicesByDateRange(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {
        List<SalesInvoice> invoices = salesInvoiceService.getInvoicesByDateRange(startDate, endDate);
        return new ResponseEntity<>(invoices, HttpStatus.OK);
    }

    @RequestMapping(value = "/createSalesInvoice",method = RequestMethod.POST)
    public ResponseEntity<SalesInvoice> createNewSalesInvoice(@RequestBody SalesInvoice salesInvoice) {
        SalesInvoice createNewInvoice = salesInvoiceService.createNewSalesInvoice(salesInvoice);
        return new ResponseEntity<>(createNewInvoice, HttpStatus.CREATED);
    }
    @RequestMapping(value = "/updateInvoice/{id}")
    public ResponseEntity<SalesInvoice> updateSalesInvoice(@PathVariable int id, @RequestBody SalesInvoice salesInvoiceDetails) {
        SalesInvoice updateinvoiceRecord = salesInvoiceService.updateSalesInvoice(id,salesInvoiceDetails);
        return new ResponseEntity<>(updateinvoiceRecord,HttpStatus.OK);
    }
    @RequestMapping(value = "/deleteinvoice/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteSalesInvoice(@PathVariable int id) {
        salesInvoiceService.deleteSalesInvoice(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
