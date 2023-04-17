package com.assignment.billapplication.dto;

import com.assignment.billapplication.entity.CustomerType;
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
public class CustomerRequestDto {
    @NotEmpty
    @NotNull
    private String name;
    @NotNull
    @NotEmpty
    @ValueOfEnum(enumClass = CustomerType.class , message = "Type must be: Employee, Affiliate, or Normal.  ")
    private String type;
}
