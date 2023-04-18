package com.assignment.billapplication.entity;

import com.assignment.billapplication.utils.discountFactory.Discount;
import com.assignment.billapplication.utils.discountFactory.DiscountFactory;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Setter @Getter @NoArgsConstructor @AllArgsConstructor
@Entity
public class Bill {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @CreationTimestamp
        private Date createdAt;
        private double totalBeforeDis;
        private double discountAmount;
        private double totalAfterDis;
        @ManyToMany(cascade = CascadeType.ALL)
        @JoinTable(name = "products_in_bill",
                joinColumns = @JoinColumn(name = "bill_id"),
                inverseJoinColumns = @JoinColumn(name = "product_id"))
        private List<Product> product;
        @JsonIgnore
        @ManyToOne
        @JoinColumn(name = "customer_id")
        private Customer customer;

        public double findDiscount(Bill bill) {
                DiscountFactory factory = new DiscountFactory();
                Discount discount =  factory.findDiscount("Bill");
                return  discount.calculateDiscount(bill);
        }
}