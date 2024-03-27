package com.lvbank.actions.profile;

public class ProfileSQLQuery {
    static final String SELECT_BRANCH_CUSTOMER_ID="select CIFNumber from Employee JOIN Employee_Branch_Map JOIN Branch JOIN Account JOIN Customer_Account_Map ON Employee.Employee_Id=Employee_Branch_Map.Employee_Id and Employee_Branch_Map.branch_Id=Branch.branch_Id and account.branch_Id=Branch.branch_Id and Customer_Account_Map.account_Number=account.account_Number where Employee.Employee_Id=?";
    static final String SELECT_ACTIVE_EMPLOYEE_ID="select Employee_id from employee WHERE is_Active=TRUE";
    static final String UPDATE_EMPLOYEE_DEACTIVATE_QUERY="UPDATE Employee SET is_Active=False WHERE Employee.Employee_Id=?";
}
