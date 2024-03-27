package com.lvbank.actions.account;

import com.lvbank.CommonConstant;
import com.lvbank.actions.profile.ProfileService;
import com.lvbank.MySQLConnection;
import com.lvbank.model.account.Account;
import com.lvbank.model.account.CurrentAccount;
import com.lvbank.model.account.FixedDepositAccount;
import com.lvbank.model.account.SavingAccount;
import com.lvbank.model.account.loan.Gold;
import com.lvbank.model.account.loan.Home;
import com.lvbank.model.profile.Nominee;

import java.sql.*;
import java.util.ArrayList;

public class AccountDataLogic {
    private boolean insertAccount(Connection connection, Account account, int branch_Id){
        boolean isAccountInserted=false;

        try(PreparedStatement ps = connection.prepareStatement(AccountSQLQuery.INSERT_ACCOUNT_QUERY, Statement.RETURN_GENERATED_KEYS)){
            ps.setDouble(1, account.getCurrentBalance());
            ps.setDouble(2, account.getAvailableBalance());
            ps.setDouble(3, account.getCreditScore());
            ps.setInt(4, branch_Id);
            int rs = ps.executeUpdate();
            if(rs>0){
                ResultSet rSet = ps.getGeneratedKeys();
                rSet.next();
                account.setAccountNumber(rSet.getDouble(1));
                isAccountInserted=true;
            }
        }catch(NullPointerException | SQLException e){
            System.out.println(e.getMessage());
        }

        return isAccountInserted;
    }
    boolean insertCurrentAccount(CurrentAccount currentAccount,Nominee nominee, int branchId,double CIFNumber){
        boolean isCurrentAccountInserted=false,isNomineeInserted=false;
        double accountNumber=0;
        int rs = 0,rs1=0;

        try(Connection connection = MySQLConnection.connect()) {
            if(connection != null){
                connection.setAutoCommit(false);
                if(CIFNumber!=0 && branchId!=0 && insertAccount(connection,currentAccount,branchId)){
                    accountNumber=currentAccount.getAccountNumber();
                    try(PreparedStatement ps = connection.prepareStatement(AccountSQLQuery.INSERT_CURRENT_ACCOUNT_QUERY)){
                        ps.setDouble(1,accountNumber);
                        ps.setDouble(2,currentAccount.getOverDraftLimit());
                        rs = ps.executeUpdate();
                    }
                }
                if(rs > 0){
                    try(PreparedStatement ps = connection.prepareStatement(AccountSQLQuery.INSERT_ACCOUNT_CUSTOMER_MAP_QUERY)){
                        ps.setDouble(1,CIFNumber);
                        ps.setDouble(2,accountNumber);
                        rs1=ps.executeUpdate();
                    }
                }
                if (rs1 > 0) {
                    ProfileService profile = new ProfileService();
                    isNomineeInserted=profile.registerNominee(connection,nominee,accountNumber);
                }
                if(rs>0 && rs1>0 && isNomineeInserted){
                    connection.commit();
                    isCurrentAccountInserted=true;
                }else{
                    connection.rollback();
                }
            }
        } catch(NullPointerException | SQLException e){
            System.out.println(e.getMessage());
        }

        return isCurrentAccountInserted;
    }
    boolean insertFixedDepositAccount(FixedDepositAccount FDAccount,Nominee nominee, int branchId,double CIFNumber){
        boolean isFixedDepositAccountInserted=false,isNomineeInserted=false;
        double accountNumber=0;
        int rs = 0,rs1=1;

        try(Connection connection = MySQLConnection.connect()) {
            if(connection != null){
                connection.setAutoCommit(false);
                if(CIFNumber!=0 && branchId!=0 && insertAccount(connection,FDAccount,branchId)){
                    accountNumber=FDAccount.getAccountNumber();
                    try(PreparedStatement ps = connection.prepareStatement(AccountSQLQuery.INSERT_FD_ACCOUNT_QUERY)){
                        ps.setDouble(1,accountNumber);
                        ps.setInt(2,FDAccount.getTenure());
                        ps.setDouble(3,FDAccount.getRateOfInterest());
                        ps.setString(4,FDAccount.getMatureDateTime().format(CommonConstant.dbDateTimeFormat));
                        rs = ps.executeUpdate();
                    }
                }
                if(rs > 0){
                    try(PreparedStatement ps = connection.prepareStatement(AccountSQLQuery.INSERT_ACCOUNT_CUSTOMER_MAP_QUERY)){
                        ps.setDouble(1,CIFNumber);
                        ps.setDouble(2,accountNumber);
                        rs1=ps.executeUpdate();
                    }
                }
                if (rs1 > 0) {
                    ProfileService profile = new ProfileService();
                    isNomineeInserted=profile.registerNominee(connection,nominee,accountNumber);
                }
                if(rs>0 && rs1>0 && isNomineeInserted){
                    connection.commit();
                    isFixedDepositAccountInserted=true;
                }else{
                    connection.rollback();
                }
            }
        } catch(NullPointerException | SQLException e){
            System.out.println(e.getMessage());
        }

        return isFixedDepositAccountInserted;
    }
    boolean insertSavingAccount(SavingAccount savingAccount,Nominee nominee, int branchId,double CIFNumber){
        boolean isSavingAccountInserted=false,isNomineeInserted=false;
        double accountNumber=0;
        int rs = 0,rs1=0;

        try(Connection connection = MySQLConnection.connect()) {
            if(connection != null){
                connection.setAutoCommit(false);
                if(CIFNumber!=0 && branchId!=0 && insertAccount(connection,savingAccount,branchId)){
                    accountNumber=savingAccount.getAccountNumber();
                    try(PreparedStatement ps = connection.prepareStatement(AccountSQLQuery.INSERT_SAVING_ACCOUNT_QUERY)){
                        ps.setDouble(1,accountNumber);
                        ps.setDouble(2,savingAccount.getMinimumAccountBalance());
                        ps.setDouble(3,savingAccount.getWithdrawalLimit());
                        ps.setDouble(4,savingAccount.getRateOfInterest());
                        rs = ps.executeUpdate();
                    }
                }
                if(rs > 0){
                    try(PreparedStatement ps = connection.prepareStatement(AccountSQLQuery.INSERT_ACCOUNT_CUSTOMER_MAP_QUERY)){
                        ps.setDouble(1,CIFNumber);
                        ps.setDouble(2,accountNumber);
                        rs1=ps.executeUpdate();
                    }
                }
                if (rs1 > 0) {
                    ProfileService profile = new ProfileService();
                    isNomineeInserted=profile.registerNominee(connection,nominee,accountNumber);
                }
                if(rs>0 && rs1>0 && isNomineeInserted){
                    connection.commit();
                    isSavingAccountInserted=true;
                }else{
                    connection.rollback();
                }
            }
        } catch(NullPointerException | SQLException e){
            System.out.println(e.getMessage());
        }

        return isSavingAccountInserted;
    }
    boolean insertGoldLoan(Gold goldLoan,double accountNumber){
        int loanId = 0,rs,rs1;
        boolean isGoldLoanInserted=false;

        try(Connection connection = MySQLConnection.connect()){
            if(connection!=null){
                connection.setAutoCommit(false);
                if(accountNumber>=2450000000000000D){
                    try(PreparedStatement ps = connection.prepareStatement(AccountSQLQuery.INSERT_LOAN_QUERY, Statement.RETURN_GENERATED_KEYS)){
                        ps.setDouble(1,accountNumber);
                        ps.setDouble(2,goldLoan.getLoanAmount());
                        ps.setDouble(3,goldLoan.getInterestRate());
                        ps.setString(4,goldLoan.getLoanType().toString());
                        rs = ps.executeUpdate();
                        if(rs>0){
                            ResultSet rSet = ps.getGeneratedKeys();
                            rSet.next();
                            loanId=rSet.getInt(1);
                            goldLoan.setLoanId(loanId);
                        }
                    }
                    if(rs>0 && loanId!=0){
                        try(PreparedStatement ps = connection.prepareStatement(AccountSQLQuery.INSERT_GOLD_LOAN_QUERY)){
                            ps.setInt(1,loanId);
                            ps.setString(2,goldLoan.getGoldPurity());
                            ps.setDouble(3,goldLoan.getGoldValuePerGram());
                            ps.setDouble(4,goldLoan.getWeightInGram());
                            rs1=ps.executeUpdate();
                            if(rs1>0){
                                isGoldLoanInserted=true;
                            }
                        }
                    }
                }
                if(isGoldLoanInserted){
                    connection.commit();
                }else{
                    connection.rollback();
                }
            }
        }catch(NullPointerException | SQLException e){
            System.out.println(e.getMessage());
        }

        return isGoldLoanInserted;
    }
    boolean insertHomeLoan(Home homeLoan, double accountNumber){
        int loanId = 0,rs,rs1;
        boolean isHomeLoanInserted=false;

        try(Connection connection = MySQLConnection.connect()){
            if(connection!=null){
                connection.setAutoCommit(false);
                if(accountNumber>=2450000000000000D){
                    try(PreparedStatement ps = connection.prepareStatement(AccountSQLQuery.INSERT_LOAN_QUERY, Statement.RETURN_GENERATED_KEYS)){
                        ps.setDouble(1,accountNumber);
                        ps.setDouble(2,homeLoan.getLoanAmount());
                        ps.setDouble(3,homeLoan.getInterestRate());
                        ps.setString(4,homeLoan.getLoanType().toString());
                        rs = ps.executeUpdate();
                        if(rs>0){
                            ResultSet rSet=ps.getGeneratedKeys();
                            rSet.next();
                            loanId=rSet.getInt(1);
                            homeLoan.setLoanId(loanId);
                        }
                    }
                    if(rs>0 && loanId!=0){
                        try(PreparedStatement ps = connection.prepareStatement(AccountSQLQuery.INSERT_HOME_LOAN_QUERY)){
                            ps.setInt(1,loanId);
                            ps.setDouble(2,homeLoan.getBuildUpArea());
                            ps.setDouble(3,homeLoan.getTotalArea());
                            ps.setDouble(4,homeLoan.getTotalValue());
                            ps.setInt(5,homeLoan.getTotalNoOfFloors());
                            rs1=ps.executeUpdate();
                            if(rs1>0){
                                isHomeLoanInserted=true;
                            }
                        }
                    }
                }
                if(isHomeLoanInserted){
                    connection.commit();
                }else{
                    connection.rollback();
                }
            }
        }catch(NullPointerException | SQLException e){
            System.out.println(e.getMessage());
        }

        return isHomeLoanInserted;
    }
    ArrayList<Double> selectAccountNumbers(double customerId){
        ArrayList<Double> accountNumbers = new ArrayList<>();

        try(Connection connection = MySQLConnection.connect()){
            if(connection!=null){
                try(PreparedStatement ps = connection.prepareStatement(AccountSQLQuery.SELECT_CUSTOMER_ACCOUNT_NO)){
                    ps.setDouble(1,customerId);
                    ResultSet rs = ps.executeQuery();
                    while(rs.next()){
                        accountNumbers.add(rs.getDouble("account.account_Number"));
                    }
                }
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

        return accountNumbers;
    }
}
