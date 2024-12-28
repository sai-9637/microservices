package com.example.productservice.externalservice;

import com.example.productservice.dto.InventoryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@FeignClient(url="http://localhost:8081",value="inventory-service")
public interface InventoryServiceFeignClient {
    @GetMapping("inventory/getInventoryByPid/{pid}")
    public InventoryDto getInventoryByPid(@PathVariable("pid") Integer pid);





}
