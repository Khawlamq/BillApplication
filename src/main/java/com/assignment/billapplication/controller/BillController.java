package com.assignment.billapplication.controller;

import com.assignment.billapplication.config.mapping.BillMapper;
import com.assignment.billapplication.dto.BillRequestDto;
import com.assignment.billapplication.dto.BillResponseDto;
import com.assignment.billapplication.entity.Bill;
import com.assignment.billapplication.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;
import static com.assignment.billapplication.utils.constant.BaseConstant.API;
import static com.assignment.billapplication.utils.constant.BaseConstant.ID;
import static com.assignment.billapplication.utils.constant.BillConstant.BILLS;

@RestController
@RequestMapping(API + BILLS)
public class BillController {
    @Autowired
    private BillService billService;
    @Autowired
    private BillMapper billMapper;
    @GetMapping
    @ResponseBody
    public List<BillResponseDto> findAll(){
        return billService.findAll().stream().map(billMapper::toResponse).collect(Collectors.toList());
    }
    @GetMapping(ID)
    public BillResponseDto getBillById(@PathVariable("id") Long billId){
        Bill bill = billService.findById(billId);
        return billMapper.toResponse(bill);
    }
    @PostMapping
    public BillResponseDto addBill(@RequestBody BillRequestDto billRequestDto){
        Bill bill = billMapper.fromRequest(billRequestDto);
        return billMapper.toResponse(billService.save(bill));
    }
    @PutMapping
    public BillResponseDto updateBill(@RequestBody Bill thebill){
        Bill updatedBill = billService.save(thebill);
        return billMapper.toResponse(updatedBill);
    }
    @DeleteMapping(ID)
    public ResponseEntity<Void> deleteBill(@PathVariable Long id){
        try {
            billService.findById(id);
        }catch (RuntimeException ex){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }
}