package com.demo.service;

import com.demo.dao.CustomerDao;
import com.demo.exceptions.ApplicationException;
import com.demo.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerDao customerDao;

    public Customer getCustomerData(Long id) throws NullPointerException{
        Optional<Customer> customer = customerDao.findById(id);
        if(customer.isPresent()){
            return customer.get();
        }else {
            throw new NullPointerException();
        }
    }

    public List<Customer> getAllCustomers() throws ApplicationException {
        try{
            List<Customer> customers = customerDao.findAll();
            return customers;
        }catch (Exception e){
            throw new ApplicationException("No data found");
        }

    }

    public Customer createCustomer(Customer customer){
        return customerDao.save(customer);
    }

    public void deleteCustomer(Long id){
        customerDao.deleteById(id);
    }
}
