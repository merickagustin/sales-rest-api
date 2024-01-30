package com.salesapi.sales.repository;

import com.salesapi.sales.entity.TxnDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TxnDetailRepository extends JpaRepository<TxnDetail, Integer> {
}
