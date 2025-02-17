package com.inventory.Erp.Services;

import com.inventory.Erp.Repository.CustomerRepository;
import com.inventory.Erp.Repository.SalesQuotationRepository;
import com.inventory.Erp.model.SalesQuotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalesQuotationService {
    @Autowired
    private SalesQuotationRepository salesQuotationRepository;
    @Autowired
    private CustomerRepository customerRepository;

    public SalesQuotationService(SalesQuotationRepository salesQuotationRepository, CustomerRepository customerRepository) {
        this.salesQuotationRepository = salesQuotationRepository;
        this.customerRepository = customerRepository;
    }

    public List<SalesQuotation> getAllQuotations() {
        return salesQuotationRepository.findAll();
    }

    public SalesQuotation getQuotationById(int id) {
        return salesQuotationRepository.findById(id) .orElseThrow(() -> new RuntimeException("Product not found!"));
    }
    public SalesQuotation createNewQuotation(SalesQuotation quotation) {
        quotation.setCreatedAt(new java.sql.Timestamp(System.currentTimeMillis()));
        quotation.setQuotationDate(new java.sql.Date(System.currentTimeMillis()));
        quotation.setStatus(true);
        quotation.setCustomer(quotation.getCustomer());
        quotation.setTotalAmount(quotation.getTotalAmount());
        quotation.setSalesquotationDetails(quotation.getSalesquotationDetails());
        return salesQuotationRepository.save(quotation);
    }
    public SalesQuotation updateQuotation(int id, SalesQuotation quotationDetails) {
        Optional<SalesQuotation> findQuotation = salesQuotationRepository.findById(id);
        if(!findQuotation.isPresent()){
            throw new RuntimeException("product specified does not exits");
        }
        SalesQuotation existingQuotation = findQuotation.get();
        existingQuotation.setCustomer(quotationDetails.getCustomer());
        existingQuotation.setQuotationDate(quotationDetails.getQuotationDate());
        existingQuotation.setTotalAmount(quotationDetails.getTotalAmount());
        existingQuotation.setStatus(true);
        return salesQuotationRepository.save(existingQuotation);
    }
    public void deleteQuotation(int id) {
        SalesQuotation quotation = salesQuotationRepository.findById(id).orElseThrow(() -> new RuntimeException("Quotation with id " + id + " not found"));
        quotation.setStatus(false);
        salesQuotationRepository.save(quotation);
    }
}
