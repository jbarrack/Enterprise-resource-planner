package com.inventory.Erp.Controller;

import com.inventory.Erp.Services.SupplierService;
import com.inventory.Erp.model.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {
    @Autowired
    private SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @RequestMapping(value = "/getAllSuppliers", method = RequestMethod.GET)
    public ResponseEntity<List<Supplier>> getAllSuppliers() {
        List<Supplier> fetchAllSupplier = supplierService.getAllSuppliers();
        return new ResponseEntity<>(fetchAllSupplier, HttpStatus.OK);
    }
    @RequestMapping(value = "/searchRecord/{id}",method = RequestMethod.GET)
    public ResponseEntity<Supplier> getSupplierById(@PathVariable("id") int id) {
        Supplier getSupplierById = supplierService.getSupplierById(id);
        return new ResponseEntity<>(getSupplierById, HttpStatus.OK);
    }
    @RequestMapping(value = "/createNewSupplier", method = RequestMethod.POST)
    public ResponseEntity<Supplier> createNewSupplier(@RequestBody Supplier supplier) {
        Supplier createNewSuppler = supplierService.createNewSupplier(supplier);
        return new ResponseEntity<>(createNewSuppler, HttpStatus.CREATED);
    }
    @RequestMapping(value = "/updateRecord/{id}",method = RequestMethod.PUT)
    public ResponseEntity<Supplier> updateSupplier(@PathVariable("id") int id, @RequestBody Supplier supplierDetails) {
        return new ResponseEntity<>(supplierService.updateSupplier(id, supplierDetails), HttpStatus.OK);
    }

    @RequestMapping(value = "/deactivate/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteSupplier(@PathVariable int id){
        supplierService.deleteSupplier(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
