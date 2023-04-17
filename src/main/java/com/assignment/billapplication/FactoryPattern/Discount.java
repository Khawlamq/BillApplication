package com.assignment.billapplication.FactoryPattern;

import com.assignment.billapplication.entity.Bill;

public interface Discount {
    double calculateDiscount(Bill bill);

}
