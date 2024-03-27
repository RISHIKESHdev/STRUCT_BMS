package com.lvbank.model.transaction;

import java.time.LocalDateTime;

public class TransferTransaction extends Transaction{
    public enum TransactionType{NEFT,IMPS};
    private Double beneficiaryAccountNumber;
    private String beneficiaryIFSCCode;
    private TransactionType transactionType;

    public TransferTransaction(LocalDateTime transactionDateTime, double transactionAmount, double beneficiaryAccountNumber, String beneficiaryIFSCCode, TransactionType transactionType) {
        super(transactionDateTime, transactionAmount);
        this.beneficiaryAccountNumber = beneficiaryAccountNumber;
        this.beneficiaryIFSCCode = beneficiaryIFSCCode;
        this.transactionType = transactionType;
    }
    public TransferTransaction(LocalDateTime transactionDateTime, double transactionAmount, PaymentMode payModeDetail,double beneficiaryAccountNumber, String beneficiaryIFSCCode,TransactionType transactionType) {
        super(transactionDateTime, transactionAmount, payModeDetail);
        this.beneficiaryAccountNumber = beneficiaryAccountNumber;
        this.beneficiaryIFSCCode = beneficiaryIFSCCode;
        this.transactionType = transactionType;
    }

    public static TransactionType inputTransactionType(int transactionCode){

        return (transactionCode==1)?(TransactionType.NEFT):(TransactionType.IMPS);
    }
    public double getBeneficiaryAccountNumber() {
        return beneficiaryAccountNumber;
    }

    public void setBeneficiaryAccountNumber(double beneficiaryAccountNumber) {
        this.beneficiaryAccountNumber = beneficiaryAccountNumber;
    }

    public String getBeneficiaryIFSCCode() {
        return beneficiaryIFSCCode;
    }

    public void setBeneficiaryIFSCCode(String beneficiaryIFSCCode) {
        this.beneficiaryIFSCCode = beneficiaryIFSCCode;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }
}