package com.example.inventoryservice.service;

import com.example.inventoryservice.dto.InventoryDto;

import java.util.List;

public interface InventoryService {
    InventoryDto createInventory(InventoryDto inventoryDto);
    List<InventoryDto> getAllInventories();
    InventoryDto getInventoryById(int iid);
    InventoryDto getInventoryByPid(int pid);

}
