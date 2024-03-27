package com.lvbank.actions.account;

import com.adventnet.ds.query.*;

public class AccountSQLQuery {
    static final String INSERT_ACCOUNT_QUERY="INSERT INTO Account(current_Balance,available_Balance,credit_Score,account_Inception_DateTime,branch_Id) Value(?,?,?,CURRENT_TIMESTAMP,?)";
    static final String INSERT_FD_ACCOUNT_QUERY="INSERT INTO Fixed_Deposit_Account(account_Number,tenure,rate_Of_Interest,mature_DateTime) value(?,?,?,?)";
    static final String INSERT_CURRENT_ACCOUNT_QUERY="INSERT INTO Current_Account(account_Number,over_Draft_Limit) VALUE(?,?)";
    static final String INSERT_SAVING_ACCOUNT_QUERY="INSERT INTO Saving_Account(account_Number,minimum_Account_Balance,withdrawal_Limit,rate_Of_Interest) VALUE(?,?,?,?)";
    static final String INSERT_ACCOUNT_CUSTOMER_MAP_QUERY="INSERT INTO Customer_Account_Map(CIFNumber,account_Number) VALUE(?,?)";
    static final String INSERT_LOAN_QUERY="INSERT INTO Loan(account_Number,loan_Amount,rate_Of_Interest,loan_Type) VALUE(?,?,?,?)";
    static final String INSERT_GOLD_LOAN_QUERY="INSERT INTO Gold_Loan(loan_Id,gold_Purity,gold_Value_Per_Gram,gold_Weight_In_Gram) VALUE(?,?,?,?)";
    static final String INSERT_HOME_LOAN_QUERY="INSERT INTO Home_Loan(loan_Id,build_Up_Area,total_Area,total_Value,total_No_Of_Floors) VALUE(?,?,?,?,?)";
    static final String SELECT_CUSTOMER_ACCOUNT_NO = "select account.account_Number from account JOIN Customer_Account_Map ON Customer_Account_Map.account_Number=account.account_Number where Customer_Account_Map.CIFNumber=?";
    final SelectQuery getAccountSelectQuery(double CIFNumber){
        Table account = new Table("Account","one");
        Table customerAccountMap = new Table("CustomerAccountMap","two");
        SelectQuery accountSelectQuery = new SelectQueryImpl(account);

        Column accountNumber = new Column("one","ACCOUNT_NUMBER");
        accountSelectQuery.addSelectColumn(accountNumber);

        Join accountCustomerJoin = new Join("Account","CustomerAccountMap",new String[]{"ACCOUNT_NUMBER"},new String[]{"ACCOUNT_NUMBER"},Join.INNER_JOIN);
        accountSelectQuery.addJoin(accountCustomerJoin);

        Criteria criteria = new Criteria(new Column("two","CIF_NUMBER"),CIFNumber,QueryConstants.EQUAL);
        accountSelectQuery.setCriteria(criteria);

        return accountSelectQuery;
    }
}