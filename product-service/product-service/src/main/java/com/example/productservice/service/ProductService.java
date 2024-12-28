package com.example.productservice.service;

import com.example.productservice.dto.ProductDto;
import com.example.productservice.dto.ProductResponseDto;
import com.example.productservice.entity.Product;

import java.util.List;

public interface ProductService {
    ProductDto createProduct(ProductDto productDto);
    //ProductResponseDto getProductById(Integer id);
    List<ProductDto> getAllProducts();
    ProductResponseDto getProductById(Integer id);






}
