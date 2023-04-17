package com.assignment.billapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BillResponseDto {
    private Date createdAt;
    private double totalBeforeDis;
    private double discountAmount;
    private double totalAfterDis;
    private List<ProductResponseDto> products;
    private CustomerResponseDto customer;
}