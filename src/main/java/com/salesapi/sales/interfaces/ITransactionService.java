package com.salesapi.sales.interfaces;

import com.salesapi.sales.entity.TxnDetail;
import com.salesapi.sales.entity.TxnHeader;

import java.util.List;

public interface ITransactionService {
    List<TxnHeader> getTransactions();

    TxnHeader getTransaction(int txnHeaderId);

    void addTransaction(TxnHeader txnheader);

    void updateTxnDetail(TxnDetail txnDetail);

    List<TxnHeader> getTransactionWithFilter(String startDate, String endDate, int amount);
}
