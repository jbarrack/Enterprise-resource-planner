package com.inventory.Erp.Services;

import com.inventory.Erp.ExeceptionsHandler.CustomerAlreadyExistException;
import com.inventory.Erp.ExeceptionsHandler.CustomerDetailsNotFound;
import com.inventory.Erp.ExeceptionsHandler.ResourceNotFoundException;
import com.inventory.Erp.Repository.CustomerRepository;
import com.inventory.Erp.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    public List<Customer> getAllCustomers() {
        for(Customer i : getAllCustomers()){
            System.out.println(i);
        }
        return customerRepository.findAll();
    }
    public List<Customer> getActiveCustomers() {
        return customerRepository.findByActive(true);
    }
    public List<Customer> getInactiveCustomers() {
        return customerRepository.findByActive(false);
    }
    public Customer getCustomerById(int id) {
        return customerRepository.findById(id).orElseThrow(() -> new CustomerDetailsNotFound("Customer with id " + id + " not found"));
    }
    public Customer createNewCustomer(Customer customer) {
        Optional<Customer> existingCustomerByEmail = customerRepository.findCustomerByEmail(customer.getEmail());
        Optional<Customer> existingCustomerByContact = customerRepository.findCustomerByContact(customer.getContact());
        if (existingCustomerByEmail.isPresent() || existingCustomerByContact.isPresent()){
            throw new CustomerAlreadyExistException("Details Already exist!");
        }
        customer.setCreatedAt(customer.getCreatedAt());
        customer.setAddress(customer.getAddress());
        customer.setPhone(customer.getPhone());
        customer.setActive(false);
        customer.setName(customer.getName());
        customer.setEmail(customer.getEmail());
        customer.setContact(customer.getContact());
        customer.setKraPIN(customer.getKraPIN());
        customer.setSalesRepresentative(customer.getSalesRepresentative());
        customer.setLocalDate(customer.getLocalDate());
        customer.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        return customerRepository.save(customer);
    }
    public Customer updateCustomer(int id, Customer customerDetails) throws Exception {
        Customer existingCustomer = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer with ID " + id + " not found"));
        if (!existingCustomer.getEmail().equals(customerDetails.getEmail())) {
            Optional<Customer> emailExists = customerRepository.findCustomerByEmail(customerDetails.getEmail());
            if (emailExists.isPresent() && emailExists.get().getCustomerId() != id) {
                throw new IllegalArgumentException("Email already in use by another customer");
            }
        }
        if (!existingCustomer.getContact().equals(customerDetails.getContact())) {
            Optional<Customer> contactExists = customerRepository.findCustomerByContact(customerDetails.getContact());
            if (contactExists.isPresent() && contactExists.get().getCustomerId() != id) {
                throw new IllegalArgumentException("Contact already in use by another customer");
            }
        }
        existingCustomer.setActive(customerDetails.isActive());
        existingCustomer.setName(customerDetails.getName());
        existingCustomer.setEmail(customerDetails.getEmail());
        existingCustomer.setContact(customerDetails.getContact());
        existingCustomer.setAddress(customerDetails.getAddress());
        existingCustomer.setKraPIN(customerDetails.getKraPIN());
        existingCustomer.setLocalDate(customerDetails.getLocalDate());
        return customerRepository.save(existingCustomer);
    }
    public void deleteCustomer(int id) {
        customerRepository.findById(id).ifPresentOrElse(customerRepository:: delete,()->{
            throw new ResourceNotFoundException("Customer not found");
        });
    }
    public List<Customer> getCustomersByActiveStatus(boolean active) {
        List<Customer> findingActiveCustomer = customerRepository.findByActive(active);
        return findingActiveCustomer;
    }
}
