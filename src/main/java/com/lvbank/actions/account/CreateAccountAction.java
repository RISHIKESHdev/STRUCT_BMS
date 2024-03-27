package com.lvbank.actions.account;

import com.lvbank.CommonConstant;
import com.lvbank.model.account.Account;
import com.lvbank.model.account.CurrentAccount;
import com.lvbank.model.account.FixedDepositAccount;
import com.lvbank.model.account.SavingAccount;
import com.lvbank.model.profile.Nominee;
import com.opensymphony.xwork2.ActionSupport;

import java.time.LocalDateTime;

public class CreateAccountAction extends ActionSupport {
    private String accountType,firstName,middleName,lastName,emailId,gender,CKYCVerificationDocument,CKYCVerificationId,mobileNumber;
    private double currentBalance,availableBalance,CIFNumber,withdrawalLimit,rateOfInterest;
    private int age,branchId,tenure;

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCKYCVerificationDocument() {
        return CKYCVerificationDocument;
    }

    public void setCKYCVerificationDocument(String CKYCVerificationDocument) {
        this.CKYCVerificationDocument = CKYCVerificationDocument;
    }

    public String getCKYCVerificationId() {
        return CKYCVerificationId;
    }

    public void setCKYCVerificationId(String CKYCVerificationId) {
        this.CKYCVerificationId = CKYCVerificationId;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(double currentBalance) {
        this.currentBalance = currentBalance;
    }

    public double getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(double availableBalance) {
        this.availableBalance = availableBalance;
    }

    public double getCIFNumber() {
        return CIFNumber;
    }

    public void setCIFNumber(double CIFNumber) {
        this.CIFNumber = CIFNumber;
    }

    public double getWithdrawalLimit() {
        return withdrawalLimit;
    }

    public void setWithdrawalLimit(double withdrawalLimit) {
        this.withdrawalLimit = withdrawalLimit;
    }

    public double getRateOfInterest() {
        return rateOfInterest;
    }

    public void setRateOfInterest(double rateOfInterest) {
        this.rateOfInterest = rateOfInterest;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    public int getTenure() {
        return tenure;
    }

    public void setTenure(int tenure) {
        this.tenure = tenure;
    }

    public String execute(){
        String retVal="error";
        boolean isAccountCreated=false;
        double creditScore;


        Account account;
        Nominee nominee = null;
        LocalDateTime accountInceptionDateTime;

        availableBalance=currentBalance;
        creditScore= CommonConstant.INITIAL_CREDIT_SCORE;
        accountInceptionDateTime= CommonConstant.currentDateTime;

        AccountService service = new AccountService();

        if(accountType.compareTo("Current Account")==0){
            double overDraftLimit;

            overDraftLimit=CommonConstant.INITIAL_OVER_DRAFT_LIMIT;

            account=new CurrentAccount(currentBalance,availableBalance,creditScore,accountInceptionDateTime,overDraftLimit);
        }else if(accountType.compareTo("Saving Account")==0){
            double minimumAccountBalance;

            minimumAccountBalance=CommonConstant.MIN_SAVING_ACCOUNT_BALANCE;

            account=new SavingAccount(currentBalance,availableBalance,creditScore,accountInceptionDateTime,minimumAccountBalance,withdrawalLimit,rateOfInterest);
        }else{
            LocalDateTime matureDateTime;

            matureDateTime=CommonConstant.currentDateTime.plusYears(tenure);

            account=new FixedDepositAccount(currentBalance,availableBalance,creditScore,accountInceptionDateTime,tenure,matureDateTime,rateOfInterest);
        }
        nominee = new Nominee(firstName,middleName,lastName,emailId,gender,age,mobileNumber,CKYCVerificationDocument,CKYCVerificationId);

        isAccountCreated=service.createAccount(account,branchId,CIFNumber,nominee);

        if(isAccountCreated){
            retVal="success";
        }

        return retVal;
    }
}
