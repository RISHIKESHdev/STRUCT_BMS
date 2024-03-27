package com.lvbank.actions.profile.entry;

import com.lvbank.model.profile.Admin;
import com.lvbank.model.profile.Customer;
import com.lvbank.model.profile.Employee;

import java.util.ArrayList;

class EntryService {
    Customer getCustomerOnLogin(String userName, String password){
        Customer customer;

        EntryDataLogic dataLogic = new EntryDataLogic();
        customer=dataLogic.checkCredentialAndGetCustomer(userName,password);

        return customer;
    }
    ArrayList<Double> getCustomerAccountNumbers(double CIFNumber){
        ArrayList<Double> customerAccountNumbers;

        EntryDataLogic dataLogic = new EntryDataLogic();
        customerAccountNumbers=dataLogic.customerAccountNumber(CIFNumber);

        return customerAccountNumbers;
    }
    Employee getEmployeeOnLogin(String userName, String password){
        Employee employee;

        EntryDataLogic dataLogic = new EntryDataLogic();
        employee=dataLogic.checkCredentialAndGetEmployee(userName,password);

        return employee;
    }
    Admin getAdminOnLogin(String userName, String password){
        Admin admin;

        EntryDataLogic dataLogic = new EntryDataLogic();
        admin=dataLogic.checkCredentialAndGetAdmin(userName,password);

        return admin;
    }
    boolean registerCustomer(Customer customer){
        boolean isCustomerCreated;

        EntryDataLogic dataLogic = new EntryDataLogic();

        if(customer!=null && dataLogic.insertCustomer(customer)) {
            isCustomerCreated=true;
        }else{
            customer=null;
            isCustomerCreated=false;
        }

        return isCustomerCreated;
    }
    boolean registerAdmin(Admin admin){
        boolean isAdminInserted;

        EntryDataLogic dataLogic = new EntryDataLogic();

        if(admin!=null ) {
            isAdminInserted=dataLogic.insertAdmin(admin);
        }else{
            isAdminInserted=false;
        }

        return isAdminInserted;
    }
    boolean registerEmployee(Employee employee ,int branchId){
        boolean isEmployeeInserted;

        EntryDataLogic dataLogic = new EntryDataLogic();

        if(employee!=null ) {
            isEmployeeInserted= dataLogic.insertEmployee(employee, branchId);
        }else{
            isEmployeeInserted=false;
        }

        return isEmployeeInserted;
    }
}
