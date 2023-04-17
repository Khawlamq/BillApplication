package com.assignment.billapplication.service;

import com.assignment.billapplication.entity.Bill;

import java.util.List;

public interface BillService {
    List<Bill> findAll();
    Bill findById(Long id);
    Bill save(Bill thebill);
    void deleteBill(Long id);
}