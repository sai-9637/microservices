package com.example.inventoryservice.controller;

import com.example.inventoryservice.dto.InventoryDto;
import com.example.inventoryservice.service.InventoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
@AllArgsConstructor

public class InventoryController {
    private InventoryService inventoryService;
     @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/addInventory")

    public ResponseEntity<InventoryDto> createInventory(@RequestBody InventoryDto inventoryDto){
        InventoryDto inventoryDto1=inventoryService.createInventory(inventoryDto);
        return  new ResponseEntity<>(inventoryDto1, HttpStatus.CREATED);

    }
    @GetMapping("/getAllInventories")
    public  ResponseEntity<List<InventoryDto>> getAllInventories(){
       List<InventoryDto> inventoryDtos= inventoryService.getAllInventories();
       return  new ResponseEntity<>(inventoryDtos,HttpStatus.OK);

    }

    @GetMapping("/getInventoryById/{id}")
    public ResponseEntity<InventoryDto>  getInventoryById(@PathVariable("id") Integer id){
        InventoryDto inventoryDto=inventoryService.getInventoryById(id);
        return  new ResponseEntity<>(inventoryDto,HttpStatus.OK);
    }
    @GetMapping("/getInventoryByPid/{pid}")
    public ResponseEntity<InventoryDto> getInventoryByPid(@PathVariable("pid") Integer pid){
        InventoryDto inventoryDto=inventoryService.getInventoryById(pid);
        return  new ResponseEntity<>(inventoryDto,HttpStatus.OK);
    }


}
