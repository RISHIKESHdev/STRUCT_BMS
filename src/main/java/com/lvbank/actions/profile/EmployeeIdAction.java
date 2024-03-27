package com.lvbank.actions.profile;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.action.ServletRequestAware;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class EmployeeIdAction extends ActionSupport implements ServletRequestAware {
    private HttpServletRequest request;

    public String execute(){
        ArrayList<Double> employeeIds;
        String retVal;
        HttpSession session = ServletActionContext.getRequest().getSession(false);
        String role = (String)session.getAttribute("role");

        ProfileService service = new ProfileService();
        employeeIds = service.getActiveEmployeeIds();
        if(role!=null && role.compareTo("Admin")==0){
            ServletActionContext.getRequest().setAttribute("employeeIds", employeeIds);
            request=ServletActionContext.getRequest();
        }

        retVal="success";

        return retVal;
    }

    @Override
    public void withServletRequest(HttpServletRequest httpServletRequest) {
        this.request=httpServletRequest;
    }
}
