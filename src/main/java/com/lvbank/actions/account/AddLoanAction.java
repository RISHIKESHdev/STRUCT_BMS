package com.lvbank.actions.account;

import com.lvbank.model.account.loan.Gold;
import com.lvbank.model.account.loan.Home;
import com.lvbank.model.account.loan.Loan;
import com.opensymphony.xwork2.ActionSupport;

public class AddLoanAction extends ActionSupport {
    private String goldPurity,loanType;
    private double weightInGram,goldValuePerGram,loanAmount,interestRate,accountNumber;
    private int totalArea,builtUpArea,totalNoOfFloors,totalValue;

    public String getGoldPurity() {
        return goldPurity;
    }

    public void setGoldPurity(String goldPurity) {
        this.goldPurity = goldPurity;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public double getWeightInGram() {
        return weightInGram;
    }

    public void setWeightInGram(double weightInGram) {
        this.weightInGram = weightInGram;
    }

    public double getGoldValuePerGram() {
        return goldValuePerGram;
    }

    public void setGoldValuePerGram(double goldValuePerGram) {
        this.goldValuePerGram = goldValuePerGram;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public double getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(double accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getTotalArea() {
        return totalArea;
    }

    public void setTotalArea(int totalArea) {
        this.totalArea = totalArea;
    }

    public int getBuiltUpArea() {
        return builtUpArea;
    }

    public void setBuiltUpArea(int builtUpArea) {
        this.builtUpArea = builtUpArea;
    }

    public int getTotalNoOfFloors() {
        return totalNoOfFloors;
    }

    public void setTotalNoOfFloors(int totalNoOfFloors) {
        this.totalNoOfFloors = totalNoOfFloors;
    }

    public int getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(int totalValue) {
        this.totalValue = totalValue;
    }

    public String execute(){
        String retVal="error";
        Loan loan;
        boolean isLoanRegistered=false;

        AccountService service = new AccountService();

        if(loanType.compareTo("goldLoan")==0){

            loan = new Gold(loanAmount,interestRate, Loan.LoanType.valueOf("SECURED"),goldPurity,goldValuePerGram,weightInGram);
            isLoanRegistered= service.registerGoldLoan(loan,accountNumber);
        }else{

            loan=new Home(loanAmount,interestRate,Loan.LoanType.valueOf("SECURED"),totalArea,builtUpArea,totalNoOfFloors,totalValue);
            isLoanRegistered= service.registerHomeLoan(loan,accountNumber);
        }

        if(isLoanRegistered){
            retVal="success";
        }

        return retVal;
    }
}
