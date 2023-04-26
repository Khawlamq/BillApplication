package com.assignment.billapplication.service;

import com.assignment.billapplication.entity.Customer;
import com.assignment.billapplication.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRep;
    @Override
    public List<Customer> findAll() {
        return customerRep.findAll();
    }
    @Override
    public Customer findByID(Long id) {
        return customerRep.findById(id).orElseThrow(() ->
                new RuntimeException("Sorry the customer with id " + id + " is not found."));
    }
    @Override
    public Customer save(Customer customer) {
        return customerRep.save(customer);
    }
    @Override
    public void deleteCustomer(Long id) {
            customerRep.deleteById(id);
    }
}
