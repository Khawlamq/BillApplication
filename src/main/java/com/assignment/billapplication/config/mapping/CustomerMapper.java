package com.assignment.billapplication.config.mapping;

import com.assignment.billapplication.dto.CustomerRequestDto;
import com.assignment.billapplication.dto.CustomerResponseDto;
import com.assignment.billapplication.entity.Customer;
import com.assignment.billapplication.entity.CustomerType;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    public CustomerResponseDto toResponse(Customer customer){
        return new CustomerResponseDto(
                customer.getName() ,
                customer.getType(),
                customer.getCreatedAt());
      }
    public Customer fromRequest(CustomerRequestDto customerRequestDto){
        return new Customer(
                null,
                customerRequestDto.getName(),
                CustomerType.valueOf(customerRequestDto.getType().toUpperCase()),
                null,
                null);
    }
}