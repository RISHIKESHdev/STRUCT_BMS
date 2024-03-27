package com.lvbank.actions.branch;

import com.lvbank.model.bank.Branch;
import com.lvbank.model.navigate.Address;
import com.lvbank.model.navigate.GeoLocation;

import java.util.ArrayList;

public class BranchService {
    public ArrayList<Integer> getActiveBranchListService(){
        ArrayList<Integer> activeBranchList;

        BranchDataLogic dataLogic = new BranchDataLogic();
        activeBranchList=dataLogic.getActiveBranchList();

        return activeBranchList;
    }
    boolean registerBranch(Branch branch, Address address, GeoLocation geoLocation){
        boolean isBranchRegistered;

        BranchDataLogic dataLogic = new BranchDataLogic();

        if(dataLogic.insertBranchRecord(branch,address,geoLocation)){
            isBranchRegistered=true;
        }else{
            isBranchRegistered=false;
        }

        return isBranchRegistered;
    }
}
