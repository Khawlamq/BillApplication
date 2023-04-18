package com.assignment.billapplication.utils.discountFactory;

import com.assignment.billapplication.entity.Bill;

public interface Discount {
    double calculateDiscount(Bill bill);

}
