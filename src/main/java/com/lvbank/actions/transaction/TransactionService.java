package com.lvbank.actions.transaction;

import com.lvbank.model.transaction.TransferTransaction;

public class TransactionService {
    boolean doTransferTransaction(TransferTransaction transferTransaction,double accountNumber){
        boolean isTransferRegistered=false;

        TransactionDataLogic dataLogic = new TransactionDataLogic();

        if(dataLogic.checkTransaction(transferTransaction,accountNumber) && dataLogic.updateTransactionAmount(transferTransaction,accountNumber) && dataLogic.insertTransferRecord(transferTransaction,accountNumber)){
            isTransferRegistered=true;
        }

        return isTransferRegistered;
    }
}
