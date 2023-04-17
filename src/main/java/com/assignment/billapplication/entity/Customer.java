package com.assignment.billapplication.entity;

import com.assignment.billapplication.FactoryPattern.Discount;
import com.assignment.billapplication.FactoryPattern.DiscountFactory;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Customer{
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String name;
        @Enumerated(EnumType.STRING)
        private CustomerType type;
        @CreationTimestamp
        private Date createdAt;
        @JsonIgnore
        @OneToMany( mappedBy = "customer" )
        private List<Bill> Bills;

        public double findDiscount(Bill bill){
                double discounOfCustomer = 0;
                DiscountFactory factory = new DiscountFactory();
                Discount discount =  factory.findDiscount("Customer");
                discounOfCustomer =  discount.calculateDiscount(bill);
                return discounOfCustomer;
        }
}