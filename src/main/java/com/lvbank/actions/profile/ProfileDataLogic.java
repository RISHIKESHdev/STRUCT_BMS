package com.lvbank.actions.profile;

import com.lvbank.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProfileDataLogic {
    ArrayList<Double> selectBranchCustomerId(double employeeId){
        ArrayList<Double> customerList = new ArrayList<>();

        try(Connection connection = MySQLConnection.connect()) {
            if(connection != null){
                try(PreparedStatement ps = connection.prepareStatement(ProfileSQLQuery.SELECT_BRANCH_CUSTOMER_ID)){
                    ps.setDouble(1,employeeId);
                    ResultSet rs = ps.executeQuery();
                    while(rs.next()){
                        customerList.add(rs.getDouble(1));
                    }
                }
            }
        } catch(NullPointerException | SQLException e){
            System.out.println(e.getMessage());
        }
        return  customerList;
    }
    ArrayList<Double> selectActiveEmployeeIds(){
        ArrayList<Double> employeeIds = new ArrayList<>();

        try(Connection connection = MySQLConnection.connect()) {
            if(connection != null){
                try(PreparedStatement ps = connection.prepareStatement(ProfileSQLQuery.SELECT_ACTIVE_EMPLOYEE_ID)){
                    ResultSet rs = ps.executeQuery();
                    while(rs.next()){
                        employeeIds.add(rs.getDouble(1));
                    }
                }
            }
        } catch(NullPointerException | SQLException e){
            System.out.println(e.getMessage());
        }

        return employeeIds;
    }

    boolean updateEmployeeDeactivate(double employeeId) throws SQLException {
        boolean isEmployeeDeactivated=false;

        try(Connection connection = MySQLConnection.connect()){
            if(connection!=null){
                connection.setAutoCommit(false);
                try(PreparedStatement ps = connection.prepareStatement(ProfileSQLQuery.UPDATE_EMPLOYEE_DEACTIVATE_QUERY)){
                    ps.setDouble(1,employeeId);
                    int rs =ps.executeUpdate();
                    if(rs>0){
                        connection.commit();
                        isEmployeeDeactivated=true;
                    }
                }
                if(!isEmployeeDeactivated) connection.rollback();
            }
        }

        return isEmployeeDeactivated;
    }
}
