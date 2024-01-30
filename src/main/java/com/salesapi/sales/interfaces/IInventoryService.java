package com.salesapi.sales.interfaces;

import com.salesapi.sales.entity.Inventory;

import java.util.List;

public interface IInventoryService {

    List<Inventory> getInventories();

    void addInventory(Inventory inventory);

    Inventory getInventory(int inventoryId);

    void updateInventory(Inventory inventory);

    void deleteInventory(int inventoryId);
}
