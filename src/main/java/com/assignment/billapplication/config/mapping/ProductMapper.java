package com.assignment.billapplication.config.mapping;

import com.assignment.billapplication.dto.ProductRequestDto;
import com.assignment.billapplication.dto.ProductResponseDto;
import com.assignment.billapplication.entity.Product;
import com.assignment.billapplication.entity.ProductType;
import com.assignment.billapplication.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    @Autowired
    private BillService billService;

    public ProductResponseDto toResponse(Product product){
        return new ProductResponseDto(
                product.getName(),
                product.getPrice(),
                product.getType());
 }
    public Product fromRequest(ProductRequestDto productDto){
        return new Product(
                null,
                productDto.getName(),
                productDto.getPrice(),
                ProductType.valueOf(productDto.getType().toUpperCase()),
                null);
    }
}