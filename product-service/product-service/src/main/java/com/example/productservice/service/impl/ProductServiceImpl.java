package com.example.productservice.service.impl;

import com.example.productservice.dto.InventoryDto;
import com.example.productservice.dto.ProductDto;
import com.example.productservice.dto.ProductResponseDto;
import com.example.productservice.entity.Product;
import com.example.productservice.exception.ResourceNotFoundException;
import com.example.productservice.externalservice.InventoryServiceFeignClient;
import com.example.productservice.repository.ProductRepository;
import com.example.productservice.service.ProductService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceImpl  implements ProductService {
    private static final Logger LOGGER= LoggerFactory.getLogger(ProductServiceImpl.class);

    private ProductRepository productRepository;
    private ModelMapper mapper;
    private InventoryServiceFeignClient feignClient;
    @Override
    public ProductDto createProduct(ProductDto  productDto) {
        LOGGER.info("creation of product");
        //convert dto to entity
        Product product=mapToEntity(productDto);

        Product newProduct=productRepository.save(product);
        //entity to dto
        return mapToDto(newProduct);

    }

    @Override
     @CircuitBreaker(name ="${spring.application.name}",fallbackMethod = "getDefaultInventory")
//    @Retry(name ="${spring.application.name}",fallbackMethod = "getDefaultInventory")
    public ProductResponseDto getProductById(Integer id) {
        LOGGER.info("inside getProductById method ");
        Product product=productRepository.findById(id).
                orElseThrow(()->new ResourceNotFoundException("Product","id",id));

        ProductResponseDto productDto=mapper.map(product,ProductResponseDto.class);
        productDto.setInventoryDto(feignClient.getInventoryByPid(id));

        return productDto;
    }

    @Override
    public List<ProductDto> getAllProducts() {
        LOGGER.info("get all products");
        List<Product> products= productRepository.findAll();
      return  products.stream().map(product -> mapToDto(product)).collect(Collectors.toList());

    }

    public ProductResponseDto getDefaultInventory(Integer id,Exception exception){
        LOGGER.info("inside getDefaultInventory method ");
        Product product=productRepository.findById(id).
                orElseThrow(()->new ResourceNotFoundException("Product","id",id));
        InventoryDto inventoryDto=new InventoryDto();
        inventoryDto.setIid(1);
        inventoryDto.setPid(1);
        inventoryDto.setAvailableCount(1500);
        ProductResponseDto productDto=mapper.map(product,ProductResponseDto.class);
        productDto.setInventoryDto(inventoryDto);

        return productDto;

    }

    //convert entity to dto
    private ProductDto mapToDto(Product product){
        ProductDto productDto=mapper.map(product,ProductDto.class);
        return  productDto;
    }

    //convert dto to entity
    private  Product mapToEntity(ProductDto productDto){
        Product product=mapper.map(productDto,Product.class);
        return  product;
    }
}
