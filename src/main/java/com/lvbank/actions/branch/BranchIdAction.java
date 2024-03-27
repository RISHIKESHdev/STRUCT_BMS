package com.lvbank.actions.branch;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class BranchIdAction extends ActionSupport {
    public String execute(){
        ArrayList<Integer> activeBranchIdList;
        HttpSession session = ServletActionContext.getRequest().getSession(false);
        String role=(String)session.getAttribute("role");

        BranchService service = new BranchService();

        if(role!=null && role.compareTo("Admin")==0){
            activeBranchIdList=service.getActiveBranchListService();
            session.setAttribute("branchIds", activeBranchIdList);
            return "success";
//            response.sendRedirect("./profile/entry/SignUp.jsp?signUpParam=employeeSignUp");
//            RequestDispatcher dispatcher = request.getRequestDispatcher("./profile/entry/SignUp.jsp?signUpParam=employeeSignUp");
//            dispatcher.forward(request, response);
        }
        return "error";
    }
}
