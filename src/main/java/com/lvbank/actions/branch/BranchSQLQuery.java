package com.lvbank.actions.branch;

import com.adventnet.ds.query.*;

public class BranchSQLQuery {
    public static final String GET_ACTIVE_BRANCH_ID="select branch_id from branch where is_Active=TRUE";
    static final String INSERT_BRANCH_QUERY="INSERT INTO Branch(branch_Name,mobile_Number,bank_Id,address_Id,geoLocation_Id) VALUE(?,?,?,?,?)";
    static final String UPDATE_BRANCH_IFSC_QUERY="UPDATE Branch SET IFSC_Code=? WHERE branch_Id=?";

    final SelectQuery getActiveBranchIdQuery(){
        Table branch = new Table("Branch","one");
        SelectQuery activeBranchQuery = new SelectQueryImpl(branch);

        Column branchId = new Column("one","BRANCH_ID");
        activeBranchQuery.addSelectColumn(branchId);

        Criteria criteria = new Criteria(new Column("one","IS_ACTIVE"),true,QueryConstants.EQUAL);
        activeBranchQuery.setCriteria(criteria);

        return activeBranchQuery;
    }
}
