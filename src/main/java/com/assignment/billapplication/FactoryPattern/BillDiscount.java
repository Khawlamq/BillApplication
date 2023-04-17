package com.assignment.billapplication.FactoryPattern;

import com.assignment.billapplication.entity.Bill;
public class BillDiscount implements Discount{
    @Override
    public double calculateDiscount(Bill bill) {
        double discount = 0;
        double total = bill.getTotalBeforeDis();

        while(total >= 100){
                discount += 5;
                total -= 100;
        }
        return discount ;
    }
}
