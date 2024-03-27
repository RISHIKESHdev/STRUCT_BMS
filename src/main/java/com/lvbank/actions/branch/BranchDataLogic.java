package com.lvbank.actions.branch;

import com.lvbank.CommonConstant;
import com.lvbank.actions.navigate.NavigateService;
import com.lvbank.MySQLConnection;
import com.lvbank.model.bank.Branch;
import com.lvbank.model.navigate.Address;
import com.lvbank.model.navigate.GeoLocation;

import java.sql.*;
import java.util.ArrayList;

public class BranchDataLogic {
    ArrayList<Integer> getActiveBranchList(){
        ArrayList<Integer> activeBranchList = new ArrayList<>();

        try(Connection connection = MySQLConnection.connect()){
            try(PreparedStatement ps = connection.prepareStatement(BranchSQLQuery.GET_ACTIVE_BRANCH_ID)){
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    activeBranchList.add(rs.getInt(1));
                }
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return activeBranchList;
    }
    boolean insertBranchRecord(Branch branch, Address address, GeoLocation geoLocation){
        int addressId,geoLocationId,branchId = 0,is,us=0;
        boolean isBranchInserted=false;
        String IFSCCode="";

        NavigateService navigate = new NavigateService();

        try(Connection connection = MySQLConnection.connect()) {
            if(connection != null){
                connection.setAutoCommit(false);
                addressId=navigate.getIdOnInsertAddressRecord(connection,address);
                geoLocationId=navigate.getIdOnInsertGeoLocationRecord(connection,geoLocation);
                try(PreparedStatement ps = connection.prepareStatement(BranchSQLQuery.INSERT_BRANCH_QUERY, Statement.RETURN_GENERATED_KEYS)){
                    ps.setString(1,branch.getBranchName());
                    ps.setLong(2,branch.getMobileNumber());
                    ps.setInt(3, CommonConstant.BANK_IDENTIFICATION_NUMBER);
                    ps.setInt(4,addressId);
                    ps.setInt(5,geoLocationId);
                    is = ps.executeUpdate();
                    if(is>0){
                        ResultSet rSet = ps.getGeneratedKeys();
                        rSet.next();
                        branchId = rSet.getInt(1);
                        IFSCCode= CommonConstant.BANK_CODE +String.format("%1$" + 6 + "s", branchId).replace(" ", "0");
                        branch.setIFSCCode(IFSCCode);
                    }
                }
                if(is>0){
                    try(PreparedStatement ps = connection.prepareStatement(BranchSQLQuery.UPDATE_BRANCH_IFSC_QUERY)){
                        ps.setString(1,IFSCCode);
                        ps.setInt(2,branchId);
                        us = ps.executeUpdate();
                    }
                }
                if(is==1 && us==1){
                    connection.commit();
                    isBranchInserted=true;
                }else{
                    connection.rollback();
                }
            }
        } catch(NullPointerException | SQLException e){
            System.out.println(e.getMessage());
        }

        return isBranchInserted;
    }
}
