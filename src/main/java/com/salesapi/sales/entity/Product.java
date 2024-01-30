package com.salesapi.sales.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "product")
public class Product {
    @Id
    @Column(name = "product_id")
    private int productId;
    @Column(name = "product_name")
    private String productName;
    @JsonIgnore
    @OneToMany(
            mappedBy = "products"
    )
    private List<Inventory> inventory;

    @JsonIgnore
    @OneToMany(
            mappedBy = "product"
    )
    private List<TxnDetail> txnDetails;
}
