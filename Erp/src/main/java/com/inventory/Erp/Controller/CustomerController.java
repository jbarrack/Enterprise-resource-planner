package com.inventory.Erp.Controller;

import ch.qos.logback.core.joran.spi.HttpUtil;
import com.inventory.Erp.Services.CustomerService;
import com.inventory.Erp.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    @RequestMapping(value = "/allCustomer",method = RequestMethod.GET)
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> findallCustomer = customerService.getAllCustomers();
        return new ResponseEntity<>(findallCustomer, HttpStatus.OK);
    }
    @RequestMapping(value = "/fetchCustomerByid/{id}",method = RequestMethod.GET)
    public ResponseEntity<Customer> getCustomerById(@PathVariable int id) {
        Customer findcustomer = customerService.getCustomerById(id);
        return new ResponseEntity<>(findcustomer, HttpStatus.OK);
    }
    @RequestMapping(value = "/createCustomer",method = RequestMethod.POST)
    public ResponseEntity<Customer> createNewCustomer(@RequestBody Customer customer) {
        Customer createNewCustomer = customerService.createNewCustomer(customer);
        return new ResponseEntity<>(createNewCustomer, HttpStatus.CREATED);
    }
    @RequestMapping(value = "/updateCustomer/{id}",method = RequestMethod.PUT)
    public ResponseEntity<Customer> updateCustomer(@PathVariable("id") int id, @RequestBody Customer customerDetails) {
        Customer upateCustomerRecords = customerService.updateCustomer(id,customerDetails);
        return new ResponseEntity<>(upateCustomerRecords, HttpStatus.OK);
    }
    @RequestMapping(value = "/deleteCustomer/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteCustomer(@PathVariable int id) {
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
