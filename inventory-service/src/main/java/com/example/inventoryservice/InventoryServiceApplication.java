package com.example.inventoryservice;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class InventoryServiceApplication {
	@Bean
	public ModelMapper modelMapper(){
		return  new ModelMapper();
	}


	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

}
