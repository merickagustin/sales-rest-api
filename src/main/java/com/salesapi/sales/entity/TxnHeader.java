package com.salesapi.sales.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "txn_header")
public class TxnHeader {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "txn_header_id")
    private int txnHeaderId;

    @Column(name = "txn_date")
    private Date txnDate;

    @Column(name = "txn_headertotalamt")
    private double txnTotalAmt;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "txnHeader"
    )
    private List<TxnDetail> txnDetails;
}
