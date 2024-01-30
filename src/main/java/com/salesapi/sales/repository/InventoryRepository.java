package com.salesapi.sales.repository;

import com.salesapi.sales.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory,Integer> {

}
