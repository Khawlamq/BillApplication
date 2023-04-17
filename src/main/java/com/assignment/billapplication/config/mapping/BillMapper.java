package com.assignment.billapplication.config.mapping;

import com.assignment.billapplication.dto.BillRequestDto;
import com.assignment.billapplication.dto.BillResponseDto;
import com.assignment.billapplication.entity.Bill;
import com.assignment.billapplication.service.CustomerService;
import com.assignment.billapplication.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class BillMapper {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private CustomerMapper customerMapper;
    public BillResponseDto toResponse(Bill bill){
        return new BillResponseDto(
                bill.getCreatedAt(),
                bill.getTotalBeforeDis(),
                bill.getDiscountAmount(),
                bill.getTotalAfterDis(),
                bill.getProduct().stream().map(productMapper::toResponse).collect(Collectors.toList()),
                customerMapper.toResponse(bill.getCustomer()));
    }
    public Bill fromRequest(BillRequestDto billDto){
        return new Bill(
                null ,
                null,
                0,
                0,
                0,
                billDto.getProductIds().stream().map(id -> productService.findByID(id) ).collect(Collectors.toList()),
                customerService.findByID(billDto.getCustomerId()));
    }
}