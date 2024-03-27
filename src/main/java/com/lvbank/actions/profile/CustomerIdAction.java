package com.lvbank.actions.profile;

import com.lvbank.actions.branch.BranchService;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.action.ServletRequestAware;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class CustomerIdAction extends ActionSupport implements ServletRequestAware {
    private HttpServletRequest request;

    @Override
    public void withServletRequest(HttpServletRequest httpServletRequest)  {
        this.request=httpServletRequest;
    }
    public String execute(){
        String retVal;

        ArrayList<Double> branchCustomerIds;
        ArrayList<Integer> branchIds;
        HttpSession session = ServletActionContext.getRequest().getSession(false);
        double employeeId;
        String role = (String)session.getAttribute("role");

        ProfileService service = new ProfileService();

        if(role!=null && role.compareTo("Customer")==0){
            branchCustomerIds=new ArrayList<>();
            branchCustomerIds.add((Double)session.getAttribute("CIFNumber"));
        }else{
            employeeId=(Double)session.getAttribute("employeeId");
            branchCustomerIds=service.getBranchCustomerIds(employeeId);
        }
        branchIds=new BranchService().getActiveBranchListService();
        ServletActionContext.getRequest().setAttribute("branchCustomerIds", branchCustomerIds);
        request=ServletActionContext.getRequest();
        session.setAttribute("branchIds", branchIds);

        String requestURL = request.getRequestURL().toString();
        if(requestURL.endsWith("updateBranch")){
            retVal="success";
        }else{
            retVal="success";
        }

        return retVal;
    }
}
