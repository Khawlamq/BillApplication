package com.assignment.billapplication.controller;

import com.assignment.billapplication.config.mapping.BillMapper;
import com.assignment.billapplication.config.mapping.CustomerMapper;
import com.assignment.billapplication.dto.BillResponseDto;
import com.assignment.billapplication.dto.CustomerRequestDto;
import com.assignment.billapplication.dto.CustomerResponseDto;
import com.assignment.billapplication.entity.Customer;
import com.assignment.billapplication.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import static com.assignment.billapplication.utils.constant.BaseConstant.API;
import static com.assignment.billapplication.utils.constant.BaseConstant.ID;
import static com.assignment.billapplication.utils.constant.CustomerConstant.BILLS;
import static com.assignment.billapplication.utils.constant.CustomerConstant.CUSTOMERS;

@RestController
@RequestMapping(API + CUSTOMERS)
public class CustomerController {
        @Autowired
        private CustomerService customerService;
        @Autowired
        private CustomerMapper customerMapper;
        @Autowired
        private BillMapper billMapper;
        @GetMapping
        @ResponseBody
        public List<CustomerResponseDto> findAll(){
            return customerService.findAll().stream().map(customerMapper::toResponse).collect(Collectors.toList());
        }
        @GetMapping(ID)
        public CustomerResponseDto FindByID(@PathVariable Long id){
            return customerMapper.toResponse(customerService.findByID(id));
        }
        @GetMapping(BILLS)
        public List<BillResponseDto> findBills(@PathVariable Long id){
            return customerService.findByID(id).getBills().stream().map(billMapper::toResponse).collect(Collectors.toList());
        }
        @PostMapping
        public CustomerResponseDto save(@RequestBody @Valid CustomerRequestDto customerRequestDto){
            Customer customer = customerMapper.fromRequest(customerRequestDto);
            return customerMapper.toResponse(customerService.save(customer));
        }
        @PutMapping
        public CustomerResponseDto updateCustomer(@RequestBody Customer thecustomer){
            Customer customer = customerService.save(thecustomer);
            return customerMapper.toResponse(customer);
        }
        @DeleteMapping(ID)
        public ResponseEntity<Void> DeleteCustomer(@PathVariable Long id){
            try {
                customerService.findByID(id);
            }catch (RuntimeException ex){
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok().build();
        }
    }