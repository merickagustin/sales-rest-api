package com.salesapi.sales.service;

import com.salesapi.sales.entity.Product;
import com.salesapi.sales.entity.TxnDetail;
import com.salesapi.sales.entity.TxnHeader;
import com.salesapi.sales.interfaces.ITransactionService;
import com.salesapi.sales.repository.ProductRepository;
import com.salesapi.sales.repository.TxnDetailRepository;
import com.salesapi.sales.repository.TxnHeaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TransactionService implements ITransactionService {
    @Autowired
    private TxnHeaderRepository txnHeaderRepo;
    @Autowired
    private TxnDetailRepository txnDetailRepo;
    @Autowired
    private ProductRepository productRepo;
    @Override
    public List<TxnHeader> getTransactions() {
        return txnHeaderRepo.findAll();
    }
    @Override
    public TxnHeader getTransaction(int txnHeaderId) {
        return txnHeaderRepo.findById(txnHeaderId).get();
    }

    @Override
    public void addTransaction(TxnHeader txnheader) {

        List<TxnDetail> txnDetails = txnheader.getTxnDetails();
        List<TxnDetail> newTxnDetails = new ArrayList<TxnDetail>();
        double totalDtlAmt;
        double totalAmt = 0;

        TxnHeader newTxnHeader = TxnHeader.builder().build();
        for (TxnDetail txnDetail:txnDetails)
        {
            Product product = productRepo.findByProductId(txnDetail.getProduct().getProductId());
            totalDtlAmt = txnDetail.getTxnDtlPrice() * txnDetail.getTxnDtlQty();
            totalAmt = totalAmt + totalDtlAmt;
            TxnDetail newTxnDetail = TxnDetail.builder()
                    .txnDtlAmt(totalDtlAmt)
                    .txnDtlQty(txnDetail.getTxnDtlQty())
                    .txnDtlPrice(txnDetail.getTxnDtlPrice())
                    .txnHeader(newTxnHeader)
                    .product(product).build();

            newTxnDetails.add(newTxnDetail);
        }

        newTxnHeader.setTxnDate(txnheader.getTxnDate());
        newTxnHeader.setTxnTotalAmt(totalAmt);
        newTxnHeader.setTxnDetails(newTxnDetails);

        txnHeaderRepo.save(newTxnHeader);
    }

    @Override
    public void updateTxnDetail(TxnDetail txnDetail) {
        txnDetailRepo.save(txnDetail);
    }

    @Override
    public List<TxnHeader> getTransactionWithFilter(String startDate, String endDate, int amount) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        List<TxnHeader> txnList = new ArrayList<>();
        try {
            if((startDate != null && !startDate.trim().isEmpty()) && (endDate != null && endDate.trim().isEmpty())) {
                Date newStartDate = df.parse(startDate + " 00:00:00");
                Date newEndDate = df.parse(endDate + " 23:59:59");

                if (amount > 0) {
                    txnList = txnHeaderRepo.findTransactionsByTxnDateAndAmt(newStartDate, newEndDate, amount);
                }else{
                    txnList = txnHeaderRepo.findTransactionsByTxnDate(newStartDate, newEndDate);
                }
            } else if (amount > 0)
            {
                txnList = txnHeaderRepo.findTransactionsByAmount(amount);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return txnList;
    }

}
