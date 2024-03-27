package com.lvbank.actions.profile.entry;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpSession;

public class LogoutUserAction extends ActionSupport {
    @Override
    public String execute(){
        boolean isUserLoggedOut=false;

        HttpSession session = ServletActionContext.getRequest().getSession(false);

        if(session!=null) {
            session.invalidate();
            isUserLoggedOut=true;
        }

        if(isUserLoggedOut){
            return "success";
        }

        return "error";
    }
}