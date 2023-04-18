package com.assignment.billapplication.FactoryPattern;

import com.assignment.billapplication.entity.Bill;
import com.assignment.billapplication.entity.Customer;
import com.assignment.billapplication.entity.Product;
import com.assignment.billapplication.entity.ProductType;
import com.assignment.billapplication.utils.MyDateUtils;

import java.util.Date;
public class CustomerDiscount implements Discount{
    private final MyDateUtils dateUtils = new MyDateUtils();

    @Override
    public double calculateDiscount(Bill bill) {
        Customer customer = bill.getCustomer();
        double discountPercentage= 0;
        double totalOfNonGroceries = 0;
        String customerType = String.valueOf(customer.getType());


        if (customerType.equalsIgnoreCase("Normal")
                && dateUtils.getDiffYears(customer.getCreatedAt(), new Date()) >= 2 ) {
            discountPercentage = 0.05;
        } else if (customerType.equalsIgnoreCase("Affiliate")) {
            discountPercentage = 0.10;
        } else if (customerType.equalsIgnoreCase("Employee")) {
            discountPercentage = 0.30;
        }

        if(discountPercentage > 0 ){
            for (Product product : bill.getProduct()) {
                if ( ProductType.NON_GROCERY == product.getType()) {
                    totalOfNonGroceries += product.getPrice();
                }
            }
        }
        return discountPercentage * totalOfNonGroceries;
    }
}
