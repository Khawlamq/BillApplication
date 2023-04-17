package com.assignment.billapplication.controller;

import com.assignment.billapplication.config.mapping.ProductMapper;
import com.assignment.billapplication.dto.ProductRequestDto;
import com.assignment.billapplication.dto.ProductResponseDto;
import com.assignment.billapplication.entity.Product;
import com.assignment.billapplication.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductMapper productMapper;

    @GetMapping
    public List<ProductResponseDto> findAll(){
        return productService.findAll().stream().map(productMapper::toResponse).collect(Collectors.toList());
    }
    @GetMapping("/{id}")
    public ProductResponseDto findByID(@PathVariable Long id){
        return productMapper.toResponse(productService.findByID(id));
    }
    @PostMapping
    public ProductResponseDto save(@RequestBody @Valid ProductRequestDto productRequestDto){
        Product product = productMapper.fromRequest(productRequestDto);
        return productMapper.toResponse(productService.save(product));
    }
    @PutMapping
    public ProductResponseDto updateProduct(@RequestBody @Valid ProductRequestDto productRequestDto){
        Product product = productMapper.fromRequest(productRequestDto);
        return productMapper.toResponse(productService.save(product));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        try {
            productService.deleteProduct(id);
        }catch (RuntimeException ex){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }
}