package com.salesapi.sales.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "inventory")
public class Inventory {
    @Id
    @Column(name="id")
    private int Id;
    private @Column(name="price")
    Double price;
    @Column(name="qty")
    private int qty;

    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "productId",
            referencedColumnName = "product_id"
    )
    private Product products;

}
