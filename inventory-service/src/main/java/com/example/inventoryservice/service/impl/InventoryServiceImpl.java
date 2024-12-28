package com.example.inventoryservice.service.impl;

import com.example.inventoryservice.dto.InventoryDto;
import com.example.inventoryservice.entity.Inventory;
import com.example.inventoryservice.exception.ResourceNotFoundException;
import com.example.inventoryservice.repository.InventoryRepo;
import com.example.inventoryservice.service.InventoryService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class InventoryServiceImpl implements InventoryService {
    private ModelMapper mapper;
    private InventoryRepo inventoryRepo;


    @Override
    public InventoryDto createInventory(InventoryDto inventoryDto) {
        //dto to entity
        Inventory inventory=mapToEntity(inventoryDto);
      return  mapToDto(inventoryRepo.save(inventory));

    }

    @Override
    public List<InventoryDto> getAllInventories() {

            List<Inventory> inventorys=inventoryRepo.findAll();
            return  inventorys.stream().map(inventory -> mapToDto(inventory)).collect(Collectors.toList());


    }

    @Override
    public InventoryDto getInventoryById(int iid) {
        Inventory inventory=inventoryRepo.findById(iid).
                orElseThrow(()->new ResourceNotFoundException("inventory","id",iid));

        return mapToDto(inventory);

    }

    @Override
    public InventoryDto getInventoryByPid(int pid) {
        Inventory inventory=inventoryRepo.findById(pid).
                orElseThrow(()->new ResourceNotFoundException("inventory","id",pid));

        return mapToDto(inventory);

    }


    //convert entity to dto
    private InventoryDto mapToDto(Inventory inventory){
        InventoryDto inventoryDto =mapper.map(inventory,InventoryDto.class);
        return inventoryDto;
    }

    //convert dto to entity
    private  Inventory mapToEntity(InventoryDto inventoryDto){
        Inventory inventory=mapper.map(inventoryDto,Inventory.class);
        return  inventory;
    }

}
