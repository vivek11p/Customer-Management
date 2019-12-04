package com.demo.service;

import com.demo.dao.CustomerDao;
import com.demo.exceptions.ApplicationException;
import com.demo.models.Customer;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

    @InjectMocks
    CustomerService customerService;

    @Mock
    CustomerDao customerDao;

    @Test
    public void getCustomerData_success(){
        Customer c=getCustomer("Vivek",1L);
        Optional<Customer> customerOptional = Optional.of(c);
        when(customerDao.findById(1L)).thenReturn(customerOptional);
        Customer customerOutput=customerService.getCustomerData(1L);
        Assert.assertEquals("Vivek",customerOptional.get().getFirstName());
    }

    @Test(expected = NullPointerException.class)
    public void getCustomerData_empty(){
        Optional<Customer> customerOptional = Optional.empty();
        when(customerDao.findById(1L)).thenReturn(customerOptional);
        Customer customerOutput=customerService.getCustomerData(1L);
    }

    @Test
    public void getAllCustomers_success() throws ApplicationException {
        List<Customer> customers =new ArrayList<>();
        Customer customer1 = getCustomer("Vivek",1L);
        Customer customer2 = getCustomer("Rohit",2L);
        customers.add(customer1);
        customers.add(customer2);
        when(customerDao.findAll()).thenReturn(customers);
        List<Customer> c = customerService.getAllCustomers();
        Assert.assertEquals(c.size(),2);
        verify(customerDao, times(1)).findAll();
    }

    @Test
    public void testCreateCustomer(){
        Customer cs = new Customer();
        cs.setFirstName("jtesting");
        cs.setLastName("lastname");
        cs.setEmailId("testing@gmail.com");
        cs.setPhoneNumber(9716266262L);
        when(customerDao.save(cs)).thenReturn(cs);
        customerService.createCustomer(cs);
        verify(customerDao,times(1)).save(cs);
    }

    @Test
    public void deleteCustomer_success(){
        customerService.createCustomer(getCustomer("Vivek",1L));
        customerService.deleteCustomer(1L);
        verify(customerDao,times(1)).deleteById(1L);
    }

    private Customer getCustomer(String name,Long id){
        Customer customer = new Customer();
        customer.setFirstName(name);
        customer.setId(id);
        return customer;
    }
}
