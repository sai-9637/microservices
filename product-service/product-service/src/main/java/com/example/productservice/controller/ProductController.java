package com.example.productservice.controller;

import com.example.productservice.dto.ProductDto;
import com.example.productservice.dto.ProductResponseDto;
import com.example.productservice.entity.Product;
import com.example.productservice.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class ProductController {
    private ProductService productService;
    @PostMapping("/addProduct")
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto){
        ProductDto savedProduct=productService.createProduct(productDto);
        return  new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }
    @GetMapping("/*/{id}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable("id") Integer id){
       ProductResponseDto productDto= productService.getProductById(id);
       return  new ResponseEntity<>(productDto,HttpStatus.OK);
    }

    @GetMapping("/getAllProducts")
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        List<ProductDto> products=productService.getAllProducts();
        return  new ResponseEntity<>(products,HttpStatus.OK);
    }
}
