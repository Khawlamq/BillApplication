package com.assignment.billapplication.dto;

import com.assignment.billapplication.entity.ProductType;
import com.assignment.billapplication.validation.ValueOfEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDto {
    @NotNull
    @NotEmpty
    private String name;
    private double price;
    @NotNull
    @NotEmpty
    @ValueOfEnum(enumClass = ProductType.class , message = "Type must be: Non_Grocery or Grocery.  ")
    private String type;

}