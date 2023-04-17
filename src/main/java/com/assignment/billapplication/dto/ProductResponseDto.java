package com.assignment.billapplication.dto;

import com.assignment.billapplication.entity.ProductType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDto {
    private String name;
    private double price;
    private ProductType type;

}
