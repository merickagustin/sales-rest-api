package com.salesapi.sales;

import com.salesapi.sales.controller.InventoryController;
import com.salesapi.sales.entity.Inventory;
import com.salesapi.sales.entity.Product;
import com.salesapi.sales.interfaces.IInventoryService;
import com.salesapi.sales.repository.InventoryRepository;
import com.salesapi.sales.service.InventoryService;
import net.bytebuddy.implementation.bytecode.Throw;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class InventoryControllerTest {
    @Autowired
    private InventoryController inventoryController;
    @MockBean
    private InventoryRepository inventoryRepository;

    private List<Inventory> inventories = new ArrayList<Inventory>();
    private Inventory expectedInventory;
    @BeforeEach
    void init(){
        Product prod1 = Product.builder()
                .productId(1)
                .productName("Bike")
                .build();

        Product prod2 = Product.builder()
                .productId(2)
                .productName("Mountain Bike")
                .build();

        Inventory inventory1 = Inventory.builder()
                .Id(1)
                .price(200_000.00)
                .qty(2)
                .products(prod1)
                .build();

        Inventory inventory2 = Inventory.builder()
                .Id(2)
                .price(250_000.00)
                .qty(2)
                .products(prod2)
                .build();

        inventories.add(inventory1);
        inventories.add(inventory2);

        //Return Inventories
        when(inventoryRepository.findAll()).thenReturn(inventories);

        //Return Inventory
        expectedInventory = inventory2;
        when(inventoryRepository.findById(2)).thenReturn(Optional.ofNullable(inventory2));
    }

    @Test
    void testGetInventories_Success(){
        assertEquals(inventories, inventoryController.getInventories());
    }

    @Test
    void testGetInventory_Success(){
        assertEquals(expectedInventory, inventoryController.getInventory(2));
    }

    @Test
    void testGetInventory_Fail(){
        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> { inventoryController.getInventory(1); });
        String expectedMessage = "No value present";

        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    void testAddInventory_Success(){
        //set id to 3.
        expectedInventory.setId(3);

        inventoryRepository.save(expectedInventory);
        inventoryController.addInventory(expectedInventory);
        verify(inventoryRepository).save(expectedInventory);
    }

    @Test
    void testUpdateInventory_Success(){
        inventoryRepository.save(expectedInventory);
        inventoryController.updateInventory(expectedInventory);
        verify(inventoryRepository).save(expectedInventory);
    }

    @Test
    void testUpdateInventory_Fail(){
        //Set Id to 5
        expectedInventory.setId(5);

        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> inventoryController.updateInventory(expectedInventory));
        String expectedMessage = "No value present";

        assertTrue(exception.getMessage().contains(expectedMessage));
    }
}