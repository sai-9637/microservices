package com.example.productservice.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class InventoryDto {
    private  int iid;
    private  int pid;
    private int availableCount;

}
