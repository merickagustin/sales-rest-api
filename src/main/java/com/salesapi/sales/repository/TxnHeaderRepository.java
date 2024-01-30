package com.salesapi.sales.repository;

import com.salesapi.sales.entity.TxnHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface TxnHeaderRepository extends JpaRepository<TxnHeader,Integer> {
    @Query("select th from TxnHeader th where th.txnDate between :startDate and :endDate")
    List<TxnHeader> findTransactionsByTxnDate(Date startDate, Date endDate);

    @Query("select th from TxnHeader th where th.txnTotalAmt >= :amount")
    List<TxnHeader> findTransactionsByAmount(int amount);

    @Query("select th from TxnHeader th where th.txnDate between :startDate and :endDate and th.txnTotalAmt >= :amount")
    List<TxnHeader> findTransactionsByTxnDateAndAmt(Date startDate, Date endDate, int amount);
}
