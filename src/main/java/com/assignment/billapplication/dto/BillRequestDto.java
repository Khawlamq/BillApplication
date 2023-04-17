package com.assignment.billapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BillRequestDto {
    @NotEmpty
    @NotNull
    private List<Long> productIds;
    @NotEmpty
    @NotNull
    private Long customerId;
}