package com.lvbank.actions.transaction;

public class TransactionSQLQuery {
    public static final String GET_ACCOUNT_TYPE="CALL Get_Account_Type(?,?)";
    public static final String GET_CURRENT_ACCOUNT_DETAIL="SELECT current_Balance,available_Balance,over_Draft_Limit FROM Account JOIN Current_Account ON Account.account_Number=Current_Account.account_Number WHERE Account.account_Number=?";
    public static final String GET_FD_ACCOUNT_DETAIL="SELECT available_Balance,mature_DateTime FROM Account JOIN Fixed_Deposit_Account ON Account.account_Number=Fixed_Deposit_Account.account_Number WHERE Account.account_Number=?";
    public static final String GET_SAVE_ACCOUNT_DETAIL="SELECT available_Balance,minimum_Account_Balance,withdrawal_Limit FROM Account JOIN Saving_Account ON Account.account_Number=Saving_Account.account_Number WHERE Account.account_Number=?";
    public static final String UPDATE_ACCOUNT_DEPOSIT_QUERY="UPDATE Account SET available_Balance=available_Balance+?,current_Balance=current_Balance+? WHERE account_Number=?";
    public static final String UPDATE_ACCOUNT_WITHDRAW_QUERY="UPDATE Account SET available_Balance=available_Balance-?,current_Balance=current_Balance-? WHERE account_Number=?";
    public static final String INSERT_TRANSACTION_QUERY="INSERT INTO Transaction(account_Number,transaction_DateTime,transaction_Amount) VALUE(?,CURRENT_TIMESTAMP,?)";
    public static final String INSERT_TRANSFER_TRAN_QUERY="INSERT INTO Transfer_Transaction(transaction_Id,transaction_Type,beneficiary_IFSC_Code,beneficiary_Account_Number) VALUE(?,?,?,?)";
}
