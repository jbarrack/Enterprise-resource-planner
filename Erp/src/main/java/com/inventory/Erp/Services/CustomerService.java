package com.inventory.Erp.Services;

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
        return customerRepository.findAll();
    }

    public List<Customer> getActiveCustomers() {
        return customerRepository.findByActive(true);
    }

    public List<Customer> getInactiveCustomers() {
        return customerRepository.findByActive(false);
    }

    public Customer getCustomerById(int id) {
        return customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer with id " + id + " not found"));
    }

    public Customer createNewCustomer(Customer customer) {
        Optional<Customer> existingCustomerByEmail = customerRepository.findCustomerByEmail(customer.getEmail());
        Optional<Customer> existingCustomerByContact = customerRepository.findCustomerByContact(customer.getContact());
        if (existingCustomerByEmail.isPresent()) {
            throw new RuntimeException("Email is already used by another Entity.");
        }
        if (existingCustomerByContact.isPresent()) {
            throw new RuntimeException("Contact is already used by another Customer.");
        }

        customer.setCreatedAt(customer.getCreatedAt());
        customer.setName(customer.getName());
        customer.setEmail(customer.getEmail());
        customer.setContact(customer.getContact());
        customer.setKraPIN(customer.getKraPIN());
        customer.setSalesRepresentative(customer.getSalesRepresentative());
        customer.setLocalDate(customer.getLocalDate());
        customer.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        return customerRepository.save(customer);
    }
    public Customer updateCustomer(int id, Customer customerDetails) {
        Optional<Customer> checkifCustomerExist = customerRepository.findById(id);
        if (!checkifCustomerExist.isPresent()) {
            throw new RuntimeException("No Record found");
        }
        Customer existingCustomer = checkifCustomerExist.get();
        Optional<Customer> existingCustomerByEmail = customerRepository.findCustomerByEmail(customerDetails.getEmail());
        if (existingCustomerByEmail.isPresent() && existingCustomerByEmail.get().getCustomerId() != existingCustomer.getCustomerId()) {
            throw new RuntimeException("Email is already used by another Entity.");
        }
        Optional<Customer> existingCustomerByContact = customerRepository.findCustomerByContact(customerDetails.getContact());
        if (existingCustomerByContact.isPresent() && existingCustomerByContact.get().getCustomerId() != existingCustomer.getCustomerId()) {
            throw new RuntimeException("Contact is already used by another Customer.");
        }
        existingCustomer.setActive(true);
        existingCustomer.setContact(customerDetails.getContact());
        existingCustomer.setEmail(customerDetails.getEmail());
        existingCustomer.setName(customerDetails.getName());
        existingCustomer.setKraPIN(customerDetails.getKraPIN());
        existingCustomer.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        existingCustomer.setAddress(customerDetails.getAddress());
        existingCustomer.setSalesRepresentative(customerDetails.getSalesRepresentative());
        existingCustomer.setPhone(customerDetails.getPhone());
        return customerRepository.save(existingCustomer);
    }

    public void deleteCustomer(int id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer with id " + id + " not found"));
        customer.setActive(false);
        customerRepository.save(customer);
    }

    public List<Customer> getCustomersByActiveStatus(boolean active) {
        List<Customer> findingActiveCustomer = customerRepository.findByActive(active);
        return findingActiveCustomer;
    }
}
