package com.salesapi.sales.service;

import com.salesapi.sales.entity.Inventory;
import com.salesapi.sales.entity.Product;
import com.salesapi.sales.interfaces.IInventoryService;
import com.salesapi.sales.repository.InventoryRepository;
import com.salesapi.sales.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService implements IInventoryService {
    @Autowired
    private InventoryRepository inventoryRepo;
    @Autowired
    private ProductRepository productRepo;

    @Override
    public List<Inventory> getInventories(){
        List<Inventory> list = (List<Inventory>) inventoryRepo.findAll();
        return list;
    }

    @Override
    public Inventory getInventory(int inventoryId) {
        return inventoryRepo.findById(inventoryId).get();
    }

    @Override
    public void addInventory(Inventory inventory) {
        Product product = productRepo.findByProductId(inventory.getProducts().getProductId());
        if(product != null){
            Inventory newInventory = Inventory.builder().
                    price(inventory.getPrice())
                    .qty(inventory.getQty())
                    .products(product)
                    .build();
            inventoryRepo.save(newInventory);
        }
    }

    @Override
    public void updateInventory(Inventory inventory) {
        Product product = productRepo.findByProductId(inventory.getProducts().getProductId());
        Inventory curInventory = inventoryRepo.findById(inventory.getId()).get();
        if(product != null && curInventory != null){
            curInventory.setPrice(inventory.getPrice());
            curInventory.setQty(inventory.getQty());
            curInventory.setProducts(product);
            inventoryRepo.save(curInventory);
        }
    }

    @Override
    public void deleteInventory(int inventoryId) {
        inventoryRepo.deleteById(inventoryId);
    }

}
