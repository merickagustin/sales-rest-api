package com.salesapi.sales.controller;

import com.salesapi.sales.entity.Inventory;
import com.salesapi.sales.repository.InventoryRepository;
import com.salesapi.sales.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InventoryController {
    @Autowired
    private InventoryService service;
    @GetMapping("/inventories")
    public List<Inventory> getInventories(){
        return service.getInventories();
    }

    @GetMapping("/inventories/{inventoryId}")
    public Inventory getInventory(@PathVariable int inventoryId){
        return service.getInventory(inventoryId);
    }

    @PostMapping("/inventories")
    public void addInventory(@RequestBody Inventory inventory){
        service.addInventory(inventory);
    }

    @PutMapping("/inventories")
    public void updateInventory(@RequestBody Inventory inventory){
        service.updateInventory(inventory);
    }

    @DeleteMapping("/inventories/{inventoryId}")
    public void deleteInventory(@PathVariable int inventoryId){
        service.deleteInventory(inventoryId);
    }
}
