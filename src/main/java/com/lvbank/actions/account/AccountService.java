package com.lvbank.actions.account;

import com.lvbank.model.account.Account;
import com.lvbank.model.account.CurrentAccount;
import com.lvbank.model.account.FixedDepositAccount;
import com.lvbank.model.account.SavingAccount;
import com.lvbank.model.account.loan.Gold;
import com.lvbank.model.account.loan.Home;
import com.lvbank.model.account.loan.Loan;
import com.lvbank.model.profile.Nominee;

import java.util.ArrayList;

public class AccountService {
    boolean createAccount(Account account, int branchId, double CIFNumber, Nominee nominee){
        boolean isAccountCreated=false;

        AccountDataLogic dataLogic = new AccountDataLogic();

        if(account instanceof FixedDepositAccount){
            isAccountCreated=dataLogic.insertFixedDepositAccount((FixedDepositAccount) account,nominee,branchId,CIFNumber);
        } else if (account instanceof SavingAccount) {
            isAccountCreated=dataLogic.insertSavingAccount((SavingAccount) account,nominee,branchId,CIFNumber);
        }else{
            isAccountCreated=dataLogic.insertCurrentAccount((CurrentAccount) account,nominee,branchId,CIFNumber);
        }

        return isAccountCreated;
    }
    boolean registerGoldLoan(Loan goldLoan,double accountNumber){
        boolean isGoldLoanRegistered=false;

        AccountDataLogic dataLogic = new AccountDataLogic();

        if(goldLoan!=null && dataLogic.insertGoldLoan((Gold)goldLoan,accountNumber)) {
            isGoldLoanRegistered=true;
        }

        return isGoldLoanRegistered;
    }
    boolean registerHomeLoan(Loan homeLoan,double accountNumber){
        boolean isHomeLoanRegistered = false;

        AccountDataLogic dataLogic = new AccountDataLogic();

        if(homeLoan!=null && dataLogic.insertHomeLoan((Home)homeLoan,accountNumber)){
            isHomeLoanRegistered=true;
        }

        return isHomeLoanRegistered;
    }
    ArrayList<Double> getAccountNumbers(double customerId){
        ArrayList<Double> accountNumbers;

        AccountDataLogic dataLogic = new AccountDataLogic();
        accountNumbers=dataLogic.selectAccountNumbers(customerId);

        return accountNumbers;
    }
}
