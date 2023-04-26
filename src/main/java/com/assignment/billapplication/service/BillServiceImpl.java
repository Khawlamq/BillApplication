package com.assignment.billapplication.service;

import com.assignment.billapplication.entity.Bill;
import com.assignment.billapplication.entity.Product;
import com.assignment.billapplication.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BillServiceImpl implements BillService{
    @Autowired
    private BillRepository billRep;
    @Override
    public List<Bill> findAll() {
        return billRep.findAll();
    }
    @Override
    public Bill findById(Long id) {
       return billRep.findById(id).orElseThrow(()->
               new RuntimeException("Sorry the bill with id "+ id + "is not found. "));
    }
    @Override
    public Bill save(Bill bill) {
        double total = bill.getProduct().stream().mapToDouble(Product::getPrice).sum();
        bill.setTotalBeforeDis(total);
        bill.setTotalAfterDis( total - (bill.findDiscount(bill) + bill.getCustomer().findDiscount(bill)) );
        bill.setDiscountAmount( total - bill.getTotalAfterDis());
        return billRep.save(bill);
    }
    @Override
    public void deleteBill(Long id) {
        billRep.deleteById(id);
    }
}