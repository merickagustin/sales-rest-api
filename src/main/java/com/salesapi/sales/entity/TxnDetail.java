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
@Table(name = "txn_detail")
public class TxnDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "txn_dtl_id")
    private int txnDtlId;

    @Column(name = "txn_dtlprice")
    private double txnDtlPrice;

    @Column(name = "txn_dtlqty")
    private int txnDtlQty;

    @Column(name = "txn_dtlamt")
    private double txnDtlAmt;

    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "txn_dtlproduct_id",
            referencedColumnName = "product_id"
    )
    private Product product;

    @JsonIgnore
    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "txn_header_id",
            referencedColumnName = "txn_header_id"
    )
    private TxnHeader txnHeader;

}
