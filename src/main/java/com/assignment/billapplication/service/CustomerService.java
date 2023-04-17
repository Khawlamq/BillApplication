package com.assignment.billapplication.service;

import com.assignment.billapplication.entity.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> findAll();
    Customer findByID(Long id);
//    List<Bill> findBills(Long id);
    Customer save(Customer customer);
    void deleteCustomer(Long id);
}
