package com.inventory.Erp.Services;

import com.inventory.Erp.ExeceptionsHandler.CustomerDetailsNotFound;
import com.inventory.Erp.ExeceptionsHandler.ResourceNotFoundException;
import com.inventory.Erp.ExeceptionsHandler.SupplierDetailsAlreadyExist;
import com.inventory.Erp.Repository.SupplierRepository;
import com.inventory.Erp.model.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {
    @Autowired
    private SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }
    public Supplier getSupplierById(int id) {
        return supplierRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Supplier with id " + id + " not found"));
    }

    public Supplier createNewSupplier(Supplier supplier) {
        Optional<Supplier> checkkrapin = supplierRepository.findSupplierByKraPIN(supplier.getKraPIN());
        if(checkkrapin.isPresent()){
            throw new SupplierDetailsAlreadyExist("Supplier Already Exist!");
        }
        supplier.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        supplier.setName(supplier.getName());
        supplier.setActive(true);
        supplier.setEmail(supplier.getEmail());
        supplier.setContact(supplier.getContact());
        supplier.setRepresentative(supplier.getRepresentative());
        supplier.setAddress(supplier.getAddress());
        supplier.setPhone(supplier.getPhone());
        supplier.setKraPIN(supplier.getKraPIN());
        supplier.setLocation(supplier.getLocation());
        return supplierRepository.save(supplier);
    }
    public Supplier updateSupplier(int id, Supplier supplierDetails) {
        Optional<Supplier> updateSupplierDetails = supplierRepository.findById(id);
        if(!updateSupplierDetails.isPresent()){
            throw new RuntimeException("Record not found");
        }
        Supplier supplier = updateSupplierDetails.get();
        supplier.setRepresentative(supplierDetails.getRepresentative());
        supplier.setLocation(supplierDetails.getLocation());
        supplier.setCreatedAt(supplierDetails.getCreatedAt());
        supplier.setName(supplierDetails.getName());
        supplier.setAddress(supplierDetails.getAddress());
        supplier.setEmail(supplierDetails.getEmail());
        supplier.setPhone(supplierDetails.getPhone());
        return supplierRepository.save(supplier);
    }
    public void deleteSupplier(int id) {
        Supplier supplier = supplierRepository.findById(id) .orElseThrow(() -> new RuntimeException("Supplier with id " + id + " not found"));
           supplier.setActive(false);
           supplierRepository.save(supplier);
    }
}
