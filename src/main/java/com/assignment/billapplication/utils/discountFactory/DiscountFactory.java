package com.assignment.billapplication.FactoryPattern;

public class DiscountFactory {
   public Discount findDiscount(String type){
       if(type == null)
           return null;

       if(type.equalsIgnoreCase("Customer"))
                return new CustomerDiscount();
       else if(type.equalsIgnoreCase("Bill"))
                return new BillDiscount();

       return null;
   }
}
