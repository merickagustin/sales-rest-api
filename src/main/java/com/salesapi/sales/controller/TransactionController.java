package com.salesapi.sales.controller;

import com.salesapi.sales.entity.TxnDetail;
import com.salesapi.sales.entity.TxnHeader;
import com.salesapi.sales.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransactionController {
    @Autowired
    private TransactionService service;

    @GetMapping("/transactions")
    public List<TxnHeader> getTransactions()
    {
        return service.getTransactions();
    }

    @GetMapping("/transactions/{txnHeaderId}")
    public TxnHeader getTransaction(@PathVariable(name = "txnHeaderId") int txnHeaderId){
        return service.getTransaction(txnHeaderId);
    }

    @GetMapping("/transactions/{startDate}/{endDate}/{amount}")
    public List<TxnHeader> getTransactionWithFilter(@PathVariable(name = "startDate") String startDate, @PathVariable(name = "endDate") String endDate,
                                                    @PathVariable(name = "amount") int amount){
        return service.getTransactionWithFilter(startDate, endDate, amount);
    }

    @PostMapping("/transactions")
    public void addTransaction(@RequestBody TxnHeader txnheader){
        service.addTransaction(txnheader);
    }

    @PutMapping("/transaction")
    public void updateTxnDetail(@RequestBody TxnDetail txnDetail){
        service.updateTxnDetail(txnDetail);
    }

}
