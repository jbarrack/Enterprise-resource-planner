package com.inventory.Erp.Services;

import com.inventory.Erp.Repository.*;
import com.inventory.Erp.model.Account;
import com.inventory.Erp.model.SalesInvoice;
import com.inventory.Erp.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SalesInvoiceService {
    @Autowired
    private SalesInvoiceRepository salesInvoiceRepository;
    @Autowired
    private SalesOrderRepository salesOrderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountService accountService;

    public SalesInvoiceService(SalesInvoiceRepository salesInvoiceRepository, SalesOrderRepository salesOrderRepository, CustomerRepository customerRepository, AccountRepository accountRepository, TransactionRepository transactionRepository, AccountService accountService) {
        this.salesInvoiceRepository = salesInvoiceRepository;
        this.salesOrderRepository = salesOrderRepository;
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
        this.accountService = accountService;
    }

    public List<SalesInvoice> getAllSalesInvoices() {
        return salesInvoiceRepository.findAll();
    }

    public List<SalesInvoice> getInvoicesByCustomerId(int customerId) {
        return salesInvoiceRepository.findByCustomerCustomerId(customerId);
    }
    public List<SalesInvoice> getInvoicesByDateRange(Date startDate, Date endDate) {
        return salesInvoiceRepository.findByInvoiceDateBetween(startDate, endDate);
    }

    public SalesInvoice getSalesInvoiceById(int id) {
      return salesInvoiceRepository.findById(id).orElseThrow(() -> new RuntimeException("SalesInvoice with id " + id + " not found"));
    }

    public SalesInvoice createNewSalesInvoice(SalesInvoice salesInvoice) {
        if (!salesOrderRepository.existsById(salesInvoice.getSalesOrder().getSalesOrderId())) {
            throw new RuntimeException("SalesOrder with id " + salesInvoice.getSalesOrder().getSalesOrderId() + " not found");
        }

        if (!customerRepository.existsById(salesInvoice.getCustomer().getCustomerId())) {
            throw new RuntimeException("Customer with id " + salesInvoice.getCustomer().getCustomerId() + " not found");

        }
        salesInvoice.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        SalesInvoice savedInvoice = salesInvoiceRepository.save(salesInvoice);

        Account customerAccount = accountRepository.findById(salesInvoice.getCustomer().getCustomerId()).orElseThrow(() -> new RuntimeException("Customer account not found"));
        createTransaction(customerAccount, salesInvoice.getTotalAmount(), "debit", "Sales Invoice created");
        Account businessAccount = accountService.getBusinessAccount();
        createTransaction(businessAccount, salesInvoice.getTotalAmount(), "credit", "Sales Invoice created");
        return savedInvoice;
    }

    public SalesInvoice updateSalesInvoice(int id, SalesInvoice salesInvoiceDetails) {
        Optional<SalesInvoice> findSalesinvoice = salesInvoiceRepository.findById(id);
        if(findSalesinvoice.isEmpty()){
            throw new RuntimeException("invoice not found");
        }
        SalesInvoice salesInvoice = findSalesinvoice.get();
        salesInvoice.setSalesOrder(salesInvoiceDetails.getSalesOrder());
        salesInvoice.setInvoiceDate(salesInvoiceDetails.getInvoiceDate());
        salesInvoice.setTotalAmount(salesInvoiceDetails.getTotalAmount());
        salesInvoice.setStatus(true);
        salesInvoice.setCustomer(salesInvoiceDetails.getCustomer());
        salesInvoice.setQuantityDelivered(salesInvoiceDetails.getQuantityDelivered());
        return salesInvoiceRepository.save(salesInvoice);
    }
    public void deleteSalesInvoice(int id) {
        SalesInvoice salesInvoice = salesInvoiceRepository.findById(id).orElseThrow(() -> new RuntimeException("SalesInvoice with id " + id + " not found"));
        salesInvoice.setStatus(false);
        salesInvoiceRepository.save(salesInvoice);
    }

 private void createTransaction(Account account, double amount, String type, String description) {
    Transaction transaction = new Transaction();
    transaction.setAccount(account);
    transaction.setAmount(amount);
    transaction.setTransactionType(type);
    transaction.setDescription(description);
    transaction.setCreatedAt(new Timestamp(System.currentTimeMillis()));
    transactionRepository.save(transaction);
    }

    private Account getBusinessAccount(int id) {
        return accountRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Business account not found"));
    }
}
