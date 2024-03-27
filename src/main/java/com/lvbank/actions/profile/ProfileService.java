package com.lvbank.actions.profile;

import com.lvbank.model.profile.Nominee;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProfileService {
    ArrayList<Double> getBranchCustomerIds(double employeeId){
        ArrayList<Double> branchCustomerId;

        ProfileDataLogic dataLogic = new ProfileDataLogic();
        branchCustomerId=dataLogic.selectBranchCustomerId(employeeId);

        return branchCustomerId;
    }
    public boolean registerNominee(Connection connection, Nominee nominee, double accountNumber){
        return true;
    }

    ArrayList<Double> getActiveEmployeeIds(){
        ArrayList<Double> employeeIds;

        ProfileDataLogic dataLogic = new ProfileDataLogic();
        employeeIds=dataLogic.selectActiveEmployeeIds();

        return employeeIds;
    }
    boolean deActivateEmployee(double employeeId) {
        boolean isEmployeeDeleted=false;
        ProfileDataLogic dataLogic = new ProfileDataLogic();

        try{
            if(dataLogic.updateEmployeeDeactivate(employeeId)){
                isEmployeeDeleted=true;
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return isEmployeeDeleted;
    }
}
