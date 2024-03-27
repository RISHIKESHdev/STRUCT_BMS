package com.lvbank.actions.transaction;

import com.lvbank.CommonConstant;
import com.lvbank.MySQLConnection;
import com.lvbank.model.transaction.Transaction;
import com.lvbank.model.transaction.TransferTransaction;

import java.sql.*;
import java.time.LocalDateTime;

public class TransactionDataLogic {
    boolean checkTransaction(TransferTransaction transferTransaction,double accountNumber){
        boolean isValid=false;
        String accountType="";

        try(Connection connection= MySQLConnection.connect()){
            if(connection!=null){
                try(CallableStatement cs = connection.prepareCall(TransactionSQLQuery.GET_ACCOUNT_TYPE)){
                    cs.registerOutParameter(2, Types.VARCHAR);
                    cs.setDouble(1,accountNumber);
                    cs.execute();

                    accountType=cs.getString("accountType").toUpperCase();
                    isValid = validateTransaction(connection,transferTransaction,accountNumber,accountType);
                }
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

        return isValid;
    }
    private boolean validateTransaction(Connection connection, TransferTransaction transaction, double accountNumber, String accountType) {
        boolean isValid=false;
        double availableBalance = 0,currentBalance,overDraftLimit,minimumAccountBalance,withdrawalLimit;
        LocalDateTime matureDateTime;
        String sqlQuery="";

        if(accountType.compareTo("CURRENT")==0){
            sqlQuery=TransactionSQLQuery.GET_CURRENT_ACCOUNT_DETAIL;
        }else if(accountType.compareTo("FD")==0){
            sqlQuery=TransactionSQLQuery.GET_FD_ACCOUNT_DETAIL;
        }else{
            sqlQuery=TransactionSQLQuery.GET_SAVE_ACCOUNT_DETAIL;
        }

        try(PreparedStatement ps = connection.prepareStatement(sqlQuery)){
            ps.setDouble(1,accountNumber);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                availableBalance=rs.getDouble("available_Balance");
                if(accountType.compareToIgnoreCase("CURRENT")==0){
                    currentBalance=rs.getDouble("current_Balance");
                    overDraftLimit=rs.getDouble("over_Draft_Limit");
                    isValid=validateCurrentTran(transaction.getTransactionAmount(), availableBalance,currentBalance,overDraftLimit);
                }else if(accountType.compareTo("FD")==0){
                    matureDateTime=rs.getTimestamp("mature_DateTime").toLocalDateTime();
                    isValid=validateFDTran(transaction.getTransactionAmount(), availableBalance,matureDateTime);
                }else{
                    minimumAccountBalance=rs.getDouble("minimum_Account_Balance");
                    withdrawalLimit=rs.getDouble("withdrawal_Limit");
                    isValid=validateSaveTran(transaction.getTransactionAmount(), availableBalance,minimumAccountBalance,withdrawalLimit);
                }
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

        return isValid;
    }
    private boolean validateCurrentTran(double transactionAmount,double availableBalance,double currentBalance,double overDraftLimit){
        boolean isValid=true;

        if(availableBalance<transactionAmount && overDraftLimit<(transactionAmount-availableBalance)){
            isValid=false;
        }

        return isValid;
    }
    private boolean validateFDTran(double transactionAmount,double availableBalance,LocalDateTime matureDateTime){
        boolean isValid=true;

        if(availableBalance<transactionAmount || CommonConstant.currentDateTime.isAfter(matureDateTime)){
            isValid=false;
        }

        return isValid;
    }
    private boolean validateSaveTran(double transactionAmount,double availableBalance,double minimumAccountBalance,double withdrawalLimit){
        boolean isValid=true;

        if(availableBalance<transactionAmount || minimumAccountBalance>(availableBalance-transactionAmount) || withdrawalLimit<transactionAmount){
            isValid=false;
        }

        return isValid;
    }

    boolean updateTransactionAmount(TransferTransaction transaction,double accountNumber){
        boolean isAmountUpdated = false,isAmountWithdrawn = false;
        try(Connection connection = MySQLConnection.connect()){
            if(connection!=null) {
                try(PreparedStatement ps = connection.prepareStatement(TransactionSQLQuery.UPDATE_ACCOUNT_WITHDRAW_QUERY)){
                    ps.setDouble(1,transaction.getTransactionAmount());
                    ps.setDouble(2,transaction.getTransactionAmount());
                    ps.setDouble(3,accountNumber);

                    int rs=ps.executeUpdate();
                    isAmountWithdrawn=rs>0;
                }
            }
            if(isAmountWithdrawn){
                try(PreparedStatement ps = connection.prepareStatement(TransactionSQLQuery.UPDATE_ACCOUNT_DEPOSIT_QUERY)){
                    ps.setDouble(1,transaction.getTransactionAmount());
                    ps.setDouble(2,transaction.getTransactionAmount());
                    ps.setDouble(3,((TransferTransaction)transaction).getBeneficiaryAccountNumber());
                    int rs=ps.executeUpdate();
                    isAmountUpdated=rs>0;
                }
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

        return isAmountUpdated;
    }
    private boolean insertTransactionRecord(Connection connection, Transaction transaction, double accountNumber){
        boolean isRecordInserted=false;
        int transactionId;

        try(PreparedStatement ps = connection.prepareStatement(TransactionSQLQuery.INSERT_TRANSACTION_QUERY, Statement.RETURN_GENERATED_KEYS)){
                ps.setDouble(1,accountNumber);
                ps.setDouble(2,transaction.getTransactionAmount());
                int rs = ps.executeUpdate();
                if(rs>0){
                    ResultSet rSet = ps.getGeneratedKeys();
                    rSet.next();
                    transactionId=rSet.getInt(1);
                    transaction.setTransactionId(transactionId);
                    isRecordInserted=true;
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }


        return isRecordInserted;
    }
    boolean insertTransferRecord(TransferTransaction transferTransaction,double accountNumber){
        boolean isRecordInserted=false;

        try(Connection connection = MySQLConnection.connect()){
            if(connection !=null){
                connection.setAutoCommit(false);
                if(insertTransactionRecord(connection,transferTransaction,accountNumber)){
                    try(PreparedStatement ps = connection.prepareStatement(TransactionSQLQuery.INSERT_TRANSFER_TRAN_QUERY)){
                        ps.setInt(1,transferTransaction.getTransactionId());
                        ps.setString(2,transferTransaction.getTransactionType().toString());
                        ps.setString(3,transferTransaction.getBeneficiaryIFSCCode());
                        ps.setDouble(4,transferTransaction.getBeneficiaryAccountNumber());
                        int rs = ps.executeUpdate();
                        isRecordInserted = (rs>0);
                    }
                }
                if(isRecordInserted ){
                    connection.commit();
                }else{
                    connection.rollback();
                }
            }
        }catch(NullPointerException | SQLException e){
            System.out.println(e.getMessage());
        }

        return isRecordInserted;
    }
}
