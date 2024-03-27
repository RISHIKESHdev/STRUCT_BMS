package com.lvbank.actions.transaction;

import com.lvbank.CommonConstant;
import com.lvbank.model.transaction.TransferTransaction;
import com.opensymphony.xwork2.ActionSupport;

public class TransferAction extends ActionSupport {
    private double accountNumber,transactionAmount,beneficiaryAccountNumber;
    private String beneficiaryIFSCCode,transactionType;

    public String execute(){
        String retVal="error";
        TransferTransaction transferTransaction;
        boolean isAmountTransfer;
        TransferTransaction.TransactionType transactionTypeEnum;

        TransactionDataLogic dataLogic=new TransactionDataLogic();

        transactionTypeEnum=TransferTransaction.inputTransactionType((transactionType.compareToIgnoreCase("NEFT")==0)?1:2);transferTransaction = new TransferTransaction(CommonConstant.currentDateTime,transactionAmount,null,beneficiaryAccountNumber,beneficiaryIFSCCode,transactionTypeEnum);
        isAmountTransfer=dataLogic.insertTransferRecord(transferTransaction,accountNumber);
        if(isAmountTransfer) retVal="success";

        return retVal;
    }
}
