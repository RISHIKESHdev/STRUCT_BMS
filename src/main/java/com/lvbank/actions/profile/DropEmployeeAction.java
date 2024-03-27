package com.lvbank.actions.profile;

import com.opensymphony.xwork2.ActionSupport;

public class DropEmployeeAction extends ActionSupport {
    private double employeeId;;

    public double getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(double employeeId) {
        this.employeeId = employeeId;
    }

    public String execute(){
        String retVal;
        boolean isEmployeeDroped;

        ProfileService service = new ProfileService();
        isEmployeeDroped=service.deActivateEmployee(employeeId);

        if(isEmployeeDroped){
            retVal="success";
        }else{
            retVal="error";
        }

        return retVal;
    }
}
