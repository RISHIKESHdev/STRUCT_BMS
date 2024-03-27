package com.lvbank.actions.profile.entry;

import com.adventnet.ds.query.*;

import java.util.ArrayList;
import java.util.List;

public class EntrySQLQuery {
    static final String SELECT_CUSTOMER_ON_LOGIN="SELECT first_Name,middle_Name,last_Name,email_Id,gender,password,age,mobile_Number,address_Line_One,address_Line_Two,address_Line_Three,landmark,city,state,country,pinCode,CIF_Number,CKYC_Verification_Document,CKYC_Verification_Id,PAN_Number FROM Address JOIN User JOIN Customer ON User.address_Id=Address.Address_Id AND User.Id=Customer.user_Id WHERE User.email_Id=? AND User.password=?";
    static final String SELECT_CUSTOMER_ACCOUNTS_QUERY="SELECT account_Number FROM Customer_Account_Map WHERE CIFNumber=?";
    static final String SELECT_EMPLOYEE_ON_LOGIN="SELECT Employee_Id,first_Name,middle_Name,last_Name,email_Id,gender,password,age,mobile_Number,address_Line_One,address_Line_Two,address_Line_Three,landmark,city,state,country,pinCode,Employee_Id,employee_Designation,employee_CTC,year_Of_Experience,is_Active FROM Address JOIN User JOIN Employee ON User.address_Id=Address.Address_Id AND User.Id=Employee.user_Id WHERE Employee.is_Active=True and User.email_Id=? AND User.password=?";
    static final String SELECT_ADMIN_ON_LOGIN="SELECT first_Name,middle_Name,last_Name,email_Id,gender,password,age,mobile_Number,address_Line_One,address_Line_Two,address_Line_Three,landmark,city,state,country,pinCode,is_Active FROM Address JOIN User JOIN Admin ON User.address_Id=Address.Address_Id AND User.Id=Admin.user_Id WHERE Admin.is_Active=True and User.email_Id=? AND User.password=?";
    static final String INSERT_USER_QUERY="INSERT INTO User(first_Name,middle_Name,last_Name,address_Id,email_Id,gender,password,age,mobile_Number) Value(?,?,?,?,?,?,?,?,?)";
    static final String INSERT_CUSTOMER_QUERY ="INSERT INTO Customer(user_Id,CKYC_Verification_Document,CKYC_Verification_Id,PAN_Number) Value(?,?,?,?)";
    static final String INSERT_EMPLOYEE_QUERY ="INSERT INTO Employee(user_Id,employee_Designation,employee_CTC,year_Of_Experience,is_Active) Value(?,?,?,?,?)";
    static final String INSERT_ADMIN_QUERY="INSERT INTO Admin(user_Id,is_Active) Value(?,?)";
    static final String INSERT_EMPLOYEE_BRANCH_MAP="INSERT INTO Employee_Branch_Map(employee_Id,branch_Id) VALUE(?,?)";

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
    final SelectQuery getUserDetailOnLoginQuery(String tableName,String emailId,String password){
        Table user = new Table("User","one");
        Table address = new Table("Address","two");
        Table userType = new Table(tableName,"three");

        SelectQuery getUserDetailQuery = new SelectQueryImpl(user);

        List<Column> columnList = new ArrayList<>();
        columnList.add(new Column("one","FIRST_NAME"));
        columnList.add(new Column("one","MIDDLE_NAME"));
        columnList.add(new Column("one","LAST_NAME"));
        columnList.add(new Column("one","EMAIL_ID"));
        columnList.add(new Column("one","GENDER"));
        columnList.add(new Column("one","PASSWORD"));
        columnList.add(new Column("one","AGE"));
        columnList.add(new Column("one","MOBILE_NUMBER"));
        columnList.add(new Column("two","ADDRESS_LINE_ONE"));
        columnList.add(new Column("two","ADDRESS_LINE_TWO"));
        columnList.add(new Column("two","ADDRESS_LINE_THREE"));
        columnList.add(new Column("two","LANDMARK"));
        columnList.add(new Column("two","CITY"));
        columnList.add(new Column("two","STATE"));
        columnList.add(new Column("two","COUNTRY"));
        columnList.add(new Column("two","PINCODE"));
        if(tableName.compareToIgnoreCase("Customer")==0){
            columnList.add(new Column("three","CIF_NUMBER"));
            columnList.add(new Column("three","CKYC_VERIFICATION_DOCUMENT"));
            columnList.add(new Column("three","CKYC_VERIFICATION_ID"));
            columnList.add(new Column("three","PAN_NUMBER"));
        } else if (tableName.compareToIgnoreCase("Employee")==0) {
            columnList.add(new Column("three","EMPLOYEE_ID"));
            columnList.add(new Column("three","EMPLOYEE_DESIGNATION"));
            columnList.add(new Column("three","EMPLOYEE_CTC"));
            columnList.add(new Column("three","YEAR_OF_EXPERIENCE"));
            columnList.add(new Column("three","IS_ACTIVE"));
        }else{
            columnList.add(new Column("three","IS_ACTIVE"));
        }
        getUserDetailQuery.addSelectColumns(columnList);

        Join userAddressJoin = new Join("User","Address",new String[]{"ADDRESS_ID"},new String[]{"ADDRESS_ID"},Join.INNER_JOIN);
        Join userTypeJoin = new Join("User",tableName,new String[]{"ID"},new String[]{"USER_ID"},Join.INNER_JOIN);
        getUserDetailQuery.addJoin(userAddressJoin);
        getUserDetailQuery.addJoin(userTypeJoin);

        Criteria emailCriteria = new Criteria(new Column("one","EMAIL_ID"),emailId,QueryConstants.EQUAL);
        Criteria passwordCriteria = new Criteria(new Column("one","PASSWORD"),password,QueryConstants.EQUAL);
        getUserDetailQuery.setCriteria(emailCriteria);
        getUserDetailQuery.setCriteria(passwordCriteria);

        return getUserDetailQuery;
    }
}
