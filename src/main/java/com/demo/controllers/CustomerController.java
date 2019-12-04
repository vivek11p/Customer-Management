package com.demo.controllers;

import com.demo.exceptions.ApplicationException;
import com.demo.models.Customer;
import com.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/customers/{id}")
    public Customer getCustomerData(@PathVariable("id") Long id){
        Customer customer = customerService.getCustomerData(id);
        return customer;
    }

    @GetMapping("/customers")
    public List<Customer> getAllCustomers() throws ApplicationException {
        List<Customer> customers = customerService.getAllCustomers();
        return customers;
    }

    @PostMapping(path = "/customers", consumes = "application/json", produces = "application/json")
    public Customer createCustomer(@RequestBody Customer customer){
        return customerService.createCustomer(customer);
    }

    @DeleteMapping("/customers/{id}")
    public void deleteCustomer(@PathVariable("id") Long id){
         customerService.deleteCustomer(id);
    }
}
