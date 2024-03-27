package com.lvbank.actions.account;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.action.ServletResponseAware;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class AccountIdAction extends ActionSupport implements ServletResponseAware {
    private HttpServletResponse response;

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public String execute(){
        String retVal=null;
        ArrayList<Double> accountNumbers;
        double customerId;

        customerId= Double.parseDouble(ServletActionContext.getRequest().getParameter("customerId"));
        AccountService service = new AccountService();

        accountNumbers=service.getAccountNumbers(customerId);
        try{
            Gson json = new Gson();
            String customerAccountNumbers = json.toJson(accountNumbers);
            ServletActionContext.getResponse().setContentType("text/html");
            ServletActionContext.getResponse().getWriter().write(customerAccountNumbers);
            response=ServletActionContext.getResponse();
        }catch (IOException ignored){}

        return retVal;
    }
    @Override
    public void withServletResponse(HttpServletResponse httpServletResponse) {
        this.response=httpServletResponse;
    }
}
