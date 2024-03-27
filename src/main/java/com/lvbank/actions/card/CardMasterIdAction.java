package com.lvbank.actions.card;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.action.ServletRequestAware;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class CardMasterIdAction extends ActionSupport implements ServletRequestAware {
    private HttpServletRequest request;
    public String execute(){
        String retVal="success";

        ArrayList<Integer> creditCardMasterIds,coBrandCreditCardMasterIds,debitCardMasterIds;

        CardService service = new CardService();

        creditCardMasterIds=service.getCardMasterIds(1);
        coBrandCreditCardMasterIds=service.getCardMasterIds(2);
        debitCardMasterIds=service.getCardMasterIds(3);

        request= ServletActionContext.getRequest();

        request.setAttribute("creditCardMasterIds", creditCardMasterIds);
        request.setAttribute("coBrandCreditCardMasterIds", coBrandCreditCardMasterIds);
        request.setAttribute("debitCardMasterIds", debitCardMasterIds);

        return retVal;
    }

    @Override
    public void withServletRequest(HttpServletRequest httpServletRequest) {
        this.request=httpServletRequest;
    }
}
