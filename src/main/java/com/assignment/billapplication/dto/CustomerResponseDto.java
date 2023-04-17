package com.assignment.billapplication.dto;

import com.assignment.billapplication.entity.CustomerType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponseDto {
    private String name;
    private CustomerType type;
    private Date createdAt;
}
