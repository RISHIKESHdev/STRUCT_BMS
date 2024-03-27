package com.lvbank.actions.profile.entry;

import com.lvbank.model.profile.Admin;
import com.lvbank.model.profile.Customer;
import com.lvbank.model.profile.Employee;
import com.lvbank.model.profile.User;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class LoginUserAction extends ActionSupport{
    private String hdn_loginParam,emailId,password;
    public String getHdn_loginParam() {
        return hdn_loginParam;
    }

    public void setHdn_loginParam(String hdn_loginParam) {
        this.hdn_loginParam = hdn_loginParam;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String execute() throws Exception {
        User user = null;
        boolean isUserLoggedIn=false;


        EntryService service = new EntryService();

        if(hdn_loginParam.compareTo("customerLogin")==0){
            user=service.getCustomerOnLogin(emailId,password);
        }else if(hdn_loginParam.compareTo("employeeLogin")==0){
            user=service.getEmployeeOnLogin(emailId,password);
        }else if(hdn_loginParam.compareTo("adminLogin")==0){
            user=service.getAdminOnLogin(emailId,password);
        }

        if(user!=null){
            if(user instanceof Customer){
                ArrayList<Double> customerAccountNumbers = service.getCustomerAccountNumbers(((Customer)user).getCIFNumber());
                HttpSession session = ServletActionContext.getRequest().getSession(true);
                session.setAttribute("CIFNumber",((Customer)user).getCIFNumber());
                session.setAttribute("emailId",user.getEmailId());
                session.setAttribute("firstName",user.getFirstName());
                session.setAttribute("customerAccountNumbers",customerAccountNumbers);
                session.setAttribute("role","Customer");
                isUserLoggedIn=true;
            } else if (user instanceof Employee) {
                HttpSession session = ServletActionContext.getRequest().getSession(true);
                session.setAttribute("isActive",((Employee)user).isActive());
                session.setAttribute("employeeId",((Employee)user).getEmployeeId());
                session.setAttribute("emailId",user.getEmailId());
                session.setAttribute("firstName",user.getFirstName());
                session.setAttribute("role","Employee");
                isUserLoggedIn=true;
            } else {
                HttpSession session = ServletActionContext.getRequest().getSession(true);
                session.setAttribute("isActive",((Admin)user).isActive());
                session.setAttribute("emailId",user.getEmailId());
                session.setAttribute("firstName",user.getFirstName());
                session.setAttribute("role","Admin");
                isUserLoggedIn=true;
            }
        }else{
            return "error";
        }

        return "success";

//        if(!isUserLoggedIn){
//            getServletContext().setAttribute("errMessage", "Login Failed.");
//            getServletContext().getRequestDispatcher("./Login.jsp?entryParam="+hdn_loginParam).forward();
//        }else{
//            response.sendRedirect("../../Home.jsp");
//        }
    }
}
